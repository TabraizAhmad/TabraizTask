package com.assignment.tasktabraiz.network;



import com.assignment.tasktabraiz.base.model.BaseResponse;
import com.assignment.tasktabraiz.movielisting.model.MovieData;

import java.util.List;

import retrofit2.Call;


public class DataManager {

    private WebService webService;

    public DataManager(WebService webService) {

        this.webService = webService;
    }

    public Call<BaseResponse< List<MovieData> >> discoverMovies(Integer pageNumber) {
        return webService.discoverMovies(pageNumber);
    }
}
