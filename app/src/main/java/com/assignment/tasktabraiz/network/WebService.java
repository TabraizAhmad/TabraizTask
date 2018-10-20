package com.assignment.tasktabraiz.network;


import com.assignment.tasktabraiz.base.model.BaseResponse;
import com.assignment.tasktabraiz.moviedetail.model.MovieData;
import com.assignment.tasktabraiz.moviedetail.model.MovieDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {

    @GET("discover/movie")
    Call<BaseResponse<List<MovieData> >> discoverMovies(@Query("page") Integer pageNumber,@Query("primary_release_date.lte") String lteReleaseDate,@Query("primary_release_date.gte") String gteReleaseDate);
    /*https://api.themoviedb.org/3/movie/446021?api_key=2407c5b64dcc861154b74d67cab3450c*/
    @GET("movie/{movieId}")
    Call<MovieDetail> getMovieDetail(@Path("movieId") Integer movieId);

}
