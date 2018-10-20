package com.assignment.tasktabraiz.di.applicationDI.module;

import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.assignment.tasktabraiz.network.RetrofitHelper;
import com.assignment.tasktabraiz.network.WebService;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesRepositoryModule {
    @Provides
    public MoviesRepository moviesRepository(WebService webService){

        return new MoviesRepository(webService);
    }

    @Provides
    public WebService webService(RetrofitHelper retrofitHelper) {
        return retrofitHelper.getWebService();
    }

    @Provides
    public RetrofitHelper retrofitHelper() {
        return new RetrofitHelper();
    }
}
