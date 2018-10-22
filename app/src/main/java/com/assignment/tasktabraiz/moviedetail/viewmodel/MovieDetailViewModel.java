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

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = ((TaskApplication)(getApplication())).getMoviesRepository();
        picasso = ((TaskApplication)(getApplication())).getPicasso();
    }

    public LiveData<MovieDetail> fetchMovieDetail(Integer movieId) {
        return moviesRepository.getMovieDetail(movieId);
    }

    public Picasso getPicasso() {
        return picasso;
    }

}
