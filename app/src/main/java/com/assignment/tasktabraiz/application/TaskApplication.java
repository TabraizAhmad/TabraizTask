package com.assignment.tasktabraiz.application;

import android.app.Application;
import android.content.Context;

import com.assignment.tasktabraiz.di.applicationDI.component.DaggerTaskApplicationCompenent;
import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationCompenent;
import com.assignment.tasktabraiz.di.applicationDI.module.ContextModule;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

public class TaskApplication extends Application {

    private TaskApplicationCompenent daggerApplicationCompenent;

    public void createComponent() {
        daggerApplicationCompenent = DaggerTaskApplicationCompenent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static TaskApplication get(Context context) {
        return (TaskApplication) context.getApplicationContext();
    }

    public Picasso getPicasso() {
        return daggerApplicationCompenent.getPicasso();
    }

    public MoviesRepository getMoviesRepository() {
        return daggerApplicationCompenent.getMoviesRepository();
    }

    public TaskApplicationCompenent getDaggerApplicationCompenent() {
        return daggerApplicationCompenent;
    }
}
