package com.assignment.tasktabraiz;

import android.content.Context;

import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.di.applicationDI.component.DaggerTaskApplicationCompenent;
import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationCompenent;
import com.assignment.tasktabraiz.di.applicationDI.module.ContextModule;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class MockTaskApplication extends TaskApplication {
    @Inject
    MoviesRepository moviesRepository;

    Picasso picasso;

    private TaskApplicationCompenent daggerApplicationCompenent;


    @Override
    public void onCreate() {
        super.onCreate();

        daggerApplicationCompenent = DaggerTaskApplicationCompenent.builder()
                .contextModule(new ContextModule(this))
                .build();

        daggerApplicationCompenent.injectTaskApplicationCompenent(this);

        picasso = daggerApplicationCompenent.getPicasso();
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
