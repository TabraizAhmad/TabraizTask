package com.assignment.tasktabraiz.mockDaggerDependencies.module;

import android.content.Context;

import com.assignment.tasktabraiz.mockDaggerDependencies.qualifier.ApplicationContextQualifier;
import com.assignment.tasktabraiz.mockDaggerDependencies.scope.TestTaskApplicationScope;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = { ContextModule.class, NetworkModule.class})
public class PicassoModule {

    @Provides
    @TestTaskApplicationScope
    public Picasso picasso(@ApplicationContextQualifier Context context,
                           OkHttp3Downloader okHttp3Downloader){

        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    @TestTaskApplicationScope
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }

}
