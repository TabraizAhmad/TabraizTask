package com.assignment.tasktabraiz.moviedetail.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.databinding.FragmentFilterBinding;
import com.assignment.tasktabraiz.databinding.FragmentMovieDetailBinding;
import com.assignment.tasktabraiz.moviedetail.databindingdefaults.DefaultDataBindingComponent;
import com.assignment.tasktabraiz.moviedetail.model.MovieData;
import com.assignment.tasktabraiz.moviedetail.model.MovieDetail;
import com.assignment.tasktabraiz.moviedetail.viewmodel.MovieDetailViewModel;
import com.assignment.tasktabraiz.moviedetail.viewmodel.MoviesViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieDetailFragment extends Fragment {
    private static final String ARG_MOVIE_ID = "movieId";
    private Integer movieId;

    private MovieDetailViewModel movieDetailViewModel;

    private MovieDetail movieDetail;
    private ViewDataBinding fragmentMovieDetailBinding;
    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance(Integer movieId) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieId = getArguments().getInt(ARG_MOVIE_ID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);

        fragmentMovieDetailBinding = DataBindingUtil.inflate(
                inflater,R.layout.fragment_movie_detail,
                container,false,
                new DefaultDataBindingComponent(movieDetailViewModel.getPicasso()));
        return fragmentMovieDetailBinding.getRoot();
        // Inflate the layout for this fragment
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        Context context = getContext();
        if (context == null) return;
        loadMovieDetail();
    }


    private void loadMovieDetail() {
        movieDetailViewModel.fetchMovieDetail(movieId).observe(this,response ->{
            if (response != null) {
                movieDetail = response;
                ((FragmentMovieDetailBinding) fragmentMovieDetailBinding).setViewModel(movieDetail);
            }
        });
    }

}
