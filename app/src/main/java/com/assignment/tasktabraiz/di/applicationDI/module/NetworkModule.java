package com.assignment.tasktabraiz.di.applicationDI.module;

import com.assignment.tasktabraiz.di.applicationDI.scope.TaskApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {

    @Provides
    @TaskApplicationScope
    public OkHttpClient okHttpClient(){

        return new OkHttpClient.Builder()
                .build();
    }
}
