package com.assignment.tasktabraiz.movielisting.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.base.model.BaseResponse;
import com.assignment.tasktabraiz.base.view.BaseFragment;
import com.assignment.tasktabraiz.movielisting.adapter.MovieListingAdapter;
import com.assignment.tasktabraiz.movielisting.model.MovieData;
import com.assignment.tasktabraiz.movielisting.viewmodel.MoviesViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListingFragment extends BaseFragment {

    RecyclerView moviesListRecyclerView;

    private MovieListingAdapter movieListingAdapter;

    private MoviesViewModel moviesViewModel;

    public MovieListingFragment() {
        // Required empty public constructor
    }

    public static MovieListingFragment newInstance() {
        MovieListingFragment fragment = new MovieListingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie_listing, container, false);
        moviesListRecyclerView = view.findViewById(R.id.moviesList);
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
        doApiCallFetchStories();
    }

    private void doApiCallFetchStories() {
        Call<BaseResponse <List<MovieData>>> movielistCall = moviesViewModel.getMovies();

        movielistCall.enqueue(new Callback<BaseResponse<List<MovieData>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<MovieData>>> call, Response<BaseResponse<List<MovieData>>> response) {
                movieListingAdapter.setItems( response.body().getResults() );
            }

            @Override
            public void onFailure(Call<BaseResponse<List<MovieData>>> call, Throwable t) {

            }
        });
    }

    private void initRecyclerView() {

        movieListingAdapter = new MovieListingAdapter( moviesViewModel.getPicasso() );
        List<MovieData> movieDataArrayList = new ArrayList<>();
        moviesListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        moviesListRecyclerView.setHasFixedSize(true);
        movieListingAdapter.setItems(movieDataArrayList);
        moviesListRecyclerView.setAdapter(movieListingAdapter);
    }


}
