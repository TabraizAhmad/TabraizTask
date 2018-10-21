package com.assignment.tasktabraiz.moviedetail.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.base.view.BaseFragment;
import com.assignment.tasktabraiz.di.moviedetailDI.component.DaggerMovieListingFragmentComponent;
import com.assignment.tasktabraiz.di.moviedetailDI.component.MovieListingFragmentComponent;
import com.assignment.tasktabraiz.moviedetail.adapter.MovieListingAdapter;
import com.assignment.tasktabraiz.moviedetail.listener.PaginationScrollListener;
import com.assignment.tasktabraiz.moviedetail.listener.RecyclerItemClickListener;
import com.assignment.tasktabraiz.moviedetail.model.MovieData;
import com.assignment.tasktabraiz.moviedetail.viewmodel.MoviesViewModel;
import com.assignment.tasktabraiz.network.util.NetworkUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

public class MovieListingFragment extends BaseFragment implements View.OnClickListener{

    private RecyclerView moviesListRecyclerView;

    @Inject
    MovieListingAdapter movieListingAdapter;

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
        progressBar = view.findViewById(R.id.progressBar);
        offlineContainer = view.findViewById(R.id.layoutOffline);

        view.findViewById(R.id.btnFilter).setOnClickListener(this);
        view.findViewById(R.id.btnTryAgain).setOnClickListener(this);

        currentPage = PAGE_START;
        if (getArguments() != null) {
            lteReleaseDate = getArguments().getString(ARG_BEFORE);
            gteReleaseDate = getArguments().getString(ARG_AFTER);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MovieListingFragmentComponent component = DaggerMovieListingFragmentComponent.builder()
                .taskApplicationCompenent(TaskApplication.get(
                        Objects.requireNonNull(
                                getActivity())).getDaggerApplicationCompenent())
                .build();
        component.injectMovieListingFragment(this);
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        initRecyclerView();
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        Context context = getContext();
        if (context == null) return;
        if (NetworkUtils.isNetworkAvailable(context)) {
            showHideOfflineLayout(false);
            loadNextPage();
        } else {
            showHideOfflineLayout(true);
            hideView(progressBar);
        }
    }


    private void showHideOfflineLayout(boolean isOffline) {
        offlineContainer.setVisibility(isOffline ? View.VISIBLE : View.GONE);
        moviesListRecyclerView.setVisibility(isOffline ? View.GONE : View.VISIBLE);
        progressBar.setVisibility(isOffline ? View.GONE : View.VISIBLE);
    }


    private void loadNextPage() {
        moviesViewModel.discoverMovies(currentPage,lteReleaseDate,gteReleaseDate)
                .observe(this, response -> {
                    if (response != null) {
                        List<MovieData> movieDataList = response.getResults();

                        movieListingAdapter.removeLoadingFooter();
                        isLoading = false;
                        hideView(progressBar);

                        movieListingAdapter.addAll( movieDataList );
                        TOTAL_PAGES = response.getTotalPages();

                        if (currentPage <= TOTAL_PAGES && movieDataList.size() > 1)
                            movieListingAdapter.addLoadingFooter();
                        else isLastPage = true;
                    }else{
                        showHideOfflineLayout(true);
                    }
                });
    }

    private void initRecyclerView() {
            List<MovieData> movieDataArrayList = new ArrayList<>();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            moviesListRecyclerView.setLayoutManager(linearLayoutManager);
            moviesListRecyclerView.setItemAnimator(new DefaultItemAnimator());
            movieListingAdapter.setItems(movieDataArrayList);
            moviesListRecyclerView.setAdapter(movieListingAdapter);
            moviesListRecyclerView.addOnScrollListener(
                    new PaginationScrollListener(linearLayoutManager) {
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

            moviesListRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                    getActivity(), moviesListRecyclerView, new
                    RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if(movieDataArrayList.get(position) != null){
                        Integer movieId = movieDataArrayList.get(position).getId();
                        openMovieDetailFragment(movieId);
                    }
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            }
            ));
    }

    private void openMovieDetailFragment(int movieId) {
        MovieDetailFragment fragment = MovieDetailFragment.newInstance(movieId);
        fragmentTrasition(fragment);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFilter:
                openFilterFragment();
                break;
            case R.id.btnTryAgain:
                loadDataFromApi();
                break;
        }

    }

    private void openFilterFragment() {
        FilterFragment filterFragment = FilterFragment.newInstance(lteReleaseDate,gteReleaseDate);
        fragmentTrasition(filterFragment);
    }
}
