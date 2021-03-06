package com.assignment.tasktabraiz.di.applicationDI.module;

import com.assignment.tasktabraiz.di.applicationDI.scope.TaskApplicationScope;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.assignment.tasktabraiz.network.WebService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.assignment.tasktabraiz.common.Constants.BASE_URL;

@Module(includes = NetworkModule.class)
public class MoviesRepositoryModule {
    @Provides
    public MoviesRepository moviesRepository(WebService webService){

        return new MoviesRepository(webService);
    }

    @Provides
    public WebService webService(Retrofit retrofit) {
        return retrofit.create(WebService.class);
    }

    @Provides
    @TaskApplicationScope
    public Retrofit retrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}