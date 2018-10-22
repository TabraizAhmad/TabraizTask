package com.assignment.tasktabraiz.util;


import android.arch.lifecycle.MutableLiveData;

import com.assignment.tasktabraiz.base.model.BaseResponse;
import com.assignment.tasktabraiz.moviedetail.adapter.MovieListingAdapter;
import com.assignment.tasktabraiz.moviedetail.model.MovieData;
import com.squareup.picasso.Picasso;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockModelsUtil {

    public static MovieData createMockMovie() {
        MovieData movie = new MovieData();
        movie.setId(new Random().nextInt());
        movie.setTitle("Title by Tabraiz");
        movie.setPosterPath("");
        movie.setOverview("Mocking sampling");
        movie.setPosterPath("/lNkDYKmrVem1J0aAfCnQlJOCKnT.jpg");
        return movie;
    }

    public static MovieData createMockMovieyWithId(int id) {
        MovieData movie = createMockMovie();
        movie.setId(id);
        return movie;
    }


    public static List<MovieData> createMockMoviesWithId(List<Integer> id) {
        List<MovieData> movies = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            MovieData movie = createMockMovie();
            movie.setId(id.get(i));
            movies.add(movie);
        }

        return movies;
    }

    public static MutableLiveData<BaseResponse<List<MovieData>>> createMockMoviesWithIdResponse(List<Integer> ids) {
        List<MovieData> movies = createMockMoviesWithId(ids);
        MutableLiveData<BaseResponse<List<MovieData>>> data = new MutableLiveData<>();
        BaseResponse<List<MovieData>> baseResponse = new BaseResponse<>();
        baseResponse.setPage(1);
        baseResponse.setTotalPages(12);
        baseResponse.setTotalResults(123);
        baseResponse.setResults(movies);
        data.setValue(baseResponse);
        return data;
    }

    public static List<Integer> createMockMovieIdList(int count) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ids.add(i);
        }
        return ids;
    }


    private static ArrayList<MovieData> createListOfMovies(int count) {
        ArrayList<MovieData> movieData = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            movieData.add(Mockito.mock(MovieData.class));
        }
        return movieData;
    }

    public static MovieListingAdapter getMovieListingAdapterWithItemsCount(int count,Picasso picasso){
        MovieListingAdapter movieListingAdapter = new MovieListingAdapter(picasso);
        movieListingAdapter.setItems(createListOfMovies(count));
        return movieListingAdapter;
    }
}