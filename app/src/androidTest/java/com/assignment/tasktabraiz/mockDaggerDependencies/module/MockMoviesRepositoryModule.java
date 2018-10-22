package com.assignment.tasktabraiz.mockDaggerDependencies.module;

import com.assignment.tasktabraiz.mockDaggerDependencies.scope.TestTaskApplicationScope;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.assignment.tasktabraiz.network.WebService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.assignment.tasktabraiz.HomeActivityTest.BASE_URL;


@Module
public class MockMoviesRepositoryModule {

    @Provides
    @TestTaskApplicationScope
    public MoviesRepository moviesRepository(WebService webService){

        return new MoviesRepository(webService);
    }

    @Provides
    public WebService webService(Retrofit retrofit) {
        return retrofit.create(WebService.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public OkHttpClient okHttpClient(){

        return new OkHttpClient.Builder()
                .build();
    }

    @Provides
    @TestTaskApplicationScope
    public Picasso picasso(){
        return Picasso.get();
    }
}