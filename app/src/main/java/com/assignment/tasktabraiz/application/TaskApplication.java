package com.assignment.tasktabraiz.application;

import android.app.Application;
import android.content.Context;

import com.assignment.tasktabraiz.di.applicationDI.component.DaggerTaskApplicationCompenent;
import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationCompenent;
import com.assignment.tasktabraiz.di.applicationDI.module.ContextModule;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

public class TaskApplication extends Application {

    private MoviesRepository moviesRepository;

    private Picasso picasso;
    private TaskApplicationCompenent daggerApplicationCompenent;


    @Override
    public void onCreate() {
        super.onCreate();

        daggerApplicationCompenent = DaggerTaskApplicationCompenent.builder()
                .contextModule(new ContextModule(this))
                .build();
        picasso = daggerApplicationCompenent.getPicasso();
        moviesRepository = daggerApplicationCompenent.getMoviesRepository();
    }

    public static TaskApplication get(Context context){
        return (TaskApplication) context.getApplicationContext();
    }

    public Picasso getPicasso() {
        return picasso;
    }

    public MoviesRepository getMoviesRepository() {
        return moviesRepository;
    }


    public TaskApplicationCompenent getDaggerApplicationCompenent() {
        return daggerApplicationCompenent;
    }
}
