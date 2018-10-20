package com.assignment.tasktabraiz.moviedetail.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.moviedetail.model.MovieDetail;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;


public class MovieDetailViewModel extends AndroidViewModel {

    private MoviesRepository moviesRepository;
    private Picasso picasso;

    private LiveData<MovieDetail> movieDetail;


    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = TaskApplication.get(this.getApplication()).getMoviesRepository();
        picasso = TaskApplication.get(this.getApplication()).getPicasso();
    }


    public LiveData<MovieDetail> fetchMovieDetail(Integer movieId) {
        movieDetail = moviesRepository.getMovieDetail(movieId);
        return movieDetail;
    }

    public LiveData<MovieDetail> getMovieDetail() {
        return movieDetail;
    }

    public Picasso getPicasso() {
        return picasso;
    }

}
