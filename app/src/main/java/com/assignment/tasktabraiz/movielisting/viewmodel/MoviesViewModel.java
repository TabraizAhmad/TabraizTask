package com.assignment.tasktabraiz.movielisting.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.base.model.BaseResponse;
import com.assignment.tasktabraiz.movielisting.model.MovieData;
import com.assignment.tasktabraiz.network.DataManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;


public class MoviesViewModel extends AndroidViewModel {

    private DataManager dataManager;
    private Picasso picasso;



    public MoviesViewModel(@NonNull Application application) {
        super(application);
        dataManager = TaskApplication.get(this.getApplication()).getDataManager();
        picasso = TaskApplication.get(this.getApplication()).getPicasso();
    }


    public Call<BaseResponse< List<MovieData> >> getMovies(int pageNum) {
        return dataManager.discoverMovies(pageNum);
    }

    public Picasso getPicasso() {
        return picasso;
    }

}
