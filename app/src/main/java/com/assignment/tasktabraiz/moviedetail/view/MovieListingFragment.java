package com.assignment.tasktabraiz.moviedetail.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.base.view.BaseFragment;
import com.assignment.tasktabraiz.moviedetail.adapter.MovieListingAdapter;
import com.assignment.tasktabraiz.moviedetail.listener.PaginationScrollListener;
import com.assignment.tasktabraiz.moviedetail.model.MovieData;
import com.assignment.tasktabraiz.moviedetail.viewmodel.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieListingFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView moviesListRecyclerView;

    private Button btnFilter;
    private Button btnClearFilter;

    private MovieListingAdapter movieListingAdapter;

    private int TOTAL_PAGES = 1;
    private int currentPage = PAGE_START;

    private static final int PAGE_START = 1;

    private MoviesViewModel moviesViewModel;

    private boolean isLastPage =false;
    private boolean isLoading = false;

    private String lteReleaseDate = "";
    private String gteReleaseDate = "";

    public static final String ARG_BEFORE = "beforeDate";
    public static final String ARG_AFTER = "afterDate";

    public MovieListingFragment() {
        // Required empty public constructor
    }

    public static MovieListingFragment newInstance(String resleaseBefore,String releaseAfter) {
        MovieListingFragment fragment = new MovieListingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BEFORE, resleaseBefore);
        args.putString(ARG_AFTER, releaseAfter);
        fragment.setArguments(args);
        return fragment;
    }
    public static MovieListingFragment newInstance() {
        return new MovieListingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie_listing, container, false);
        moviesListRecyclerView = view.findViewById(R.id.moviesList);
        btnFilter = view.findViewById(R.id.btnFilter);
        btnClearFilter = view.findViewById(R.id.btnClearFilter);
        btnFilter.setOnClickListener(this);
        btnClearFilter.setOnClickListener(this);
        if (getArguments() != null) {
            lteReleaseDate = getArguments().getString(ARG_BEFORE);
            gteReleaseDate = getArguments().getString(ARG_AFTER);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        initRecyclerView();
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        Context context = getContext();
        if (context == null) return;
        loadNextPage();
    }


    private void loadNextPage() {
        moviesViewModel.discoverMovies(currentPage,lteReleaseDate,gteReleaseDate)
                .observe(this, response -> {
                    if (response != null) {
                        List<MovieData> movieDataList = response.getResults();
                        movieListingAdapter.removeLoadingFooter();
                        isLoading = false;

                        movieListingAdapter.addAll( movieDataList );
                        TOTAL_PAGES = response.getTotalPages();

                        if (currentPage <= TOTAL_PAGES && movieDataList.size() > 1)
                            movieListingAdapter.addLoadingFooter();
                        else isLastPage = true;
                    }
                });
    }

    private void initRecyclerView() {
            movieListingAdapter = new MovieListingAdapter( moviesViewModel.getPicasso() );
            List<MovieData> movieDataArrayList = new ArrayList<>();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            moviesListRecyclerView.setLayoutManager(linearLayoutManager);
            moviesListRecyclerView.setItemAnimator(new DefaultItemAnimator());
            movieListingAdapter.setItems(movieDataArrayList);
            moviesListRecyclerView.setAdapter(movieListingAdapter);
            moviesListRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
                @Override
                protected void loadMoreItems() {
                    isLoading = true;
                    currentPage += 1;
                    loadNextPage();
                }

                @Override
                public int getTotalPageCount() {
                    return TOTAL_PAGES;
                }

                @Override
                public boolean isLastPage() {
                    return isLastPage;
                }

                @Override
                public boolean isLoading() {
                    return isLoading;
                }
            });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFilter:
                openFilterFragment();
                break;
            case R.id.btnClearFilter:
                if(lteReleaseDate.length() > 0 || gteReleaseDate.length() > 0){
                    lteReleaseDate="";
                    gteReleaseDate="";
                    movieListingAdapter.clearAll();
                    currentPage = 1;
                    initRecyclerView();
                    loadDataFromApi();
                }

        }

    }


    private void openFilterFragment() {

        FilterFragment filterFragment = FilterFragment.newInstance(lteReleaseDate,gteReleaseDate);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, filterFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
