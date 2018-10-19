package com.assignment.tasktabraiz.application;

import android.app.Application;
import android.content.Context;

import com.assignment.tasktabraiz.network.DataManager;
import com.assignment.tasktabraiz.network.RetrofitHelper;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

public class TaskApplication extends Application {
    private DataManager dataManager;
    private Picasso picasso;
    private RetrofitHelper retrofitHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .build();
        retrofitHelper = new RetrofitHelper();
        dataManager = new DataManager(retrofitHelper.getWebService());
    }

    public static TaskApplication get(Context context){
        return (TaskApplication) context.getApplicationContext();
    }

    public Picasso getPicasso() {
        return picasso;
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
