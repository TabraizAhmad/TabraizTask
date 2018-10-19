package com.assignment.tasktabraiz.network;


import com.assignment.tasktabraiz.base.model.BaseResponse;
import com.assignment.tasktabraiz.movielisting.model.MovieData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    @GET("discover/movie")
    Call<BaseResponse<List<MovieData> >> discoverMovies(@Query("page") Integer pageNumber);
}
