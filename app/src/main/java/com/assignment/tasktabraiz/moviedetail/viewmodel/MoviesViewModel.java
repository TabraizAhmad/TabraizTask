package com.assignment.tasktabraiz.moviedetail.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.base.model.BaseResponse;
import com.assignment.tasktabraiz.moviedetail.model.MovieData;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviesViewModel extends AndroidViewModel {

    private MoviesRepository moviesRepository;

    private LiveData<BaseResponse<List<MovieData>>> moviesList;


    public MoviesViewModel(@NonNull Application application) {
        super(application);
        moviesRepository = TaskApplication.get(this.getApplication()).getMoviesRepository();

    }


    public LiveData<BaseResponse<List<MovieData>>> discoverMovies(Integer pageNum, String lteReleaseDate,String gteReleaseDate) {
        moviesList = moviesRepository.discoverMovies(pageNum,lteReleaseDate,gteReleaseDate);
        return moviesList;
    }

    public LiveData<BaseResponse<List<MovieData>>> getMoviesList() {
        return moviesList;
    }

}
