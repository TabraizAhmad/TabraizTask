package com.assignment.tasktabraiz.movielisting.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.base.model.BaseResponse;
import com.assignment.tasktabraiz.movielisting.model.MovieData;
import com.assignment.tasktabraiz.movielisting.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviesViewModel extends AndroidViewModel {

    private MoviesRepository moviesRepository;
    private Picasso picasso;

    private LiveData<BaseResponse<List<MovieData>>> moviesList;


    public MoviesViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = TaskApplication.get(this.getApplication()).getMoviesRepository();
        picasso = TaskApplication.get(this.getApplication()).getPicasso();
    }


    public LiveData<BaseResponse<List<MovieData>>> discoverMovies(int pageNum) {
        moviesList = moviesRepository.discoverMovies(pageNum);
        return moviesList;
    }

    public LiveData<BaseResponse<List<MovieData>>> getMoviesList() {
        return moviesList;
    }

    public Picasso getPicasso() {
        return picasso;
    }

}
