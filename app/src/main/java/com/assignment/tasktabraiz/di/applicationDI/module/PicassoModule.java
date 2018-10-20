package com.assignment.tasktabraiz.di.applicationDI.module;

import android.content.Context;

import com.assignment.tasktabraiz.di.applicationDI.qualifier.ApplicationContextQualifier;
import com.assignment.tasktabraiz.di.applicationDI.scope.TaskApplicationScope;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = { ContextModule.class, NetworkModule.class})
public class PicassoModule {

    @Provides
    @TaskApplicationScope
    public Picasso picasso(@ApplicationContextQualifier Context context,
                           OkHttp3Downloader okHttp3Downloader){

        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    @TaskApplicationScope
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }

}
