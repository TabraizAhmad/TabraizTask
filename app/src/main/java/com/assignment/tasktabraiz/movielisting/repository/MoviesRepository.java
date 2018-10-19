package com.assignment.tasktabraiz.movielisting.repository;



import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.assignment.tasktabraiz.base.model.BaseResponse;
import com.assignment.tasktabraiz.movielisting.model.MovieData;
import com.assignment.tasktabraiz.network.WebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesRepository {

    private WebService webService;

    public MoviesRepository(WebService webService) {

        this.webService = webService;
    }

    public LiveData<BaseResponse< List<MovieData> >> discoverMovies(Integer pageNumber) {
        final MutableLiveData<BaseResponse<List<MovieData>>> data = new MutableLiveData<>();
        Call<BaseResponse <List<MovieData>>> movielistCall = webService.discoverMovies(pageNumber);
        movielistCall.enqueue(new Callback<BaseResponse<List<MovieData>>>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<List<MovieData>>> call, @NonNull Response<BaseResponse<List<MovieData>>> response) {
                data.setValue(response.body());

            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<List<MovieData>>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
