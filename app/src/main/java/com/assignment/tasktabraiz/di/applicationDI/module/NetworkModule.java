package com.assignment.tasktabraiz.di.applicationDI.module;

import com.assignment.tasktabraiz.BuildConfig;
import com.assignment.tasktabraiz.di.applicationDI.scope.TaskApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Module
public class NetworkModule {

    @Provides
    @TaskApplicationScope
    public OkHttpClient okHttpClient(Interceptor interceptor){

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    public Interceptor interceptor(){
        return chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();
            HttpUrl newUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                    .build();

            Request.Builder requestBuilder = original.newBuilder()
                    .url(newUrl);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };

    }
}
