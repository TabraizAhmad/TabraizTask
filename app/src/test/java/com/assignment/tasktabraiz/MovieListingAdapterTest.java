package com.assignment.tasktabraiz;

import com.assignment.tasktabraiz.moviedetail.adapter.MovieListingAdapter;
import com.assignment.tasktabraiz.moviedetail.model.MovieData;
import com.assignment.tasktabraiz.util.MockModelsUtil;
import com.squareup.picasso.Picasso;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class MovieListingAdapterTest {

    @Mock
    Picasso picasso;
    @Before
    public void beforeTest() {
    }

    @Test
    public void itemCount() {
        int itemsCount = 3;
        MovieListingAdapter movieListingAdapter = MockModelsUtil.getMovieListingAdapterWithItemsCount(itemsCount,picasso);
        Assert.assertEquals(itemsCount, movieListingAdapter.getItemCount());
    }

    @Test
    public void getFirstItemId() {
        int idMockMovie = 123;
        ArrayList<MovieData> movies = new ArrayList<>();
        MovieData movie = MockModelsUtil.createMockMovieyWithId(idMockMovie);
        movies.add(movie);
        MovieListingAdapter movieListingAdapter = new MovieListingAdapter(picasso);
        movieListingAdapter.setItems(movies);
        Assert.assertEquals(idMockMovie, movieListingAdapter.getItemId(0));
    }

    @Test
    public void addItem() {
        MovieListingAdapter movieListingAdapter = MockModelsUtil.getMovieListingAdapterWithItemsCount(0,picasso);
        MovieData movie = Mockito.mock(MovieData.class);
        movieListingAdapter.add(movie);
        Assert.assertEquals(1, movieListingAdapter.getItemCount());
    }

    @Test
    public void setItems() {
        int setItemsCount = 5;
        MovieListingAdapter movieListingAdapter = MockModelsUtil.getMovieListingAdapterWithItemsCount(setItemsCount,picasso);
        Assert.assertEquals(setItemsCount, movieListingAdapter.getItemCount());
    }


    @Test
    public void itemsNotSet() {
        MovieListingAdapter movieListingAdapter = new MovieListingAdapter(picasso);
        movieListingAdapter.setItems(null);
        Assert.assertEquals(0, movieListingAdapter.getItemCount());
        Assert.assertEquals(-1, movieListingAdapter.getItemId(0));
    }


}
