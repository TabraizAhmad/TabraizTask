package com.assignment.tasktabraiz;

import android.arch.lifecycle.LiveData;

import com.assignment.tasktabraiz.base.model.BaseResponse;
import com.assignment.tasktabraiz.moviedetail.model.MovieData;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.assignment.tasktabraiz.util.MockModelsUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class MoviesRepositoryTest {

    MoviesRepository moviesRepository;

    @Before
    public void setUp() {
        moviesRepository = mock(MoviesRepository.class);
    }


    @Test
    public void shouldGetMovies() {
        final List<Integer> movieIdList = MockModelsUtil.createMockMovieIdList(2);
        LiveData<BaseResponse<List<MovieData>>> moviesData = MockModelsUtil.createMockMoviesWithIdResponse(movieIdList);
        when(moviesRepository.discoverMovies(1,"","")).thenReturn(moviesData);
        LiveData<BaseResponse<List<MovieData>>> baseResponseLiveData = moviesRepository.discoverMovies(1, "", "");

        baseResponseLiveData.observeForever(listBaseResponse -> Assert.assertEquals((int)listBaseResponse.getTotalResults(),123));
    }
}
