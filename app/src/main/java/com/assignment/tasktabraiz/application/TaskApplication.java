package com.assignment.tasktabraiz.application;

import android.app.Application;
import android.content.Context;

import com.assignment.tasktabraiz.di.applicationDI.component.DaggerTaskApplicationComponent;
import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationComponent;
import com.assignment.tasktabraiz.di.applicationDI.module.ContextModule;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

public class TaskApplication extends Application {

    private TaskApplicationComponent daggerApplicationComponent;

    public void createComponent() {
        daggerApplicationComponent = DaggerTaskApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public Picasso getPicasso() {
        return daggerApplicationComponent.getPicasso();
    }

    public MoviesRepository getMoviesRepository() {
        return daggerApplicationComponent.getMoviesRepository();
    }

    public TaskApplicationComponent getDaggerApplicationComponent() {
        return daggerApplicationComponent;
    }
}
