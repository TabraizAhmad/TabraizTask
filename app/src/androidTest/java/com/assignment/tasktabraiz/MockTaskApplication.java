package com.assignment.tasktabraiz;

import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationComponent;
import com.assignment.tasktabraiz.mockDaggerDependencies.component.DaggerMockApplicationComponent;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

public class MockTaskApplication extends TaskApplication {

    private TaskApplicationComponent daggerApplicationComponent;

    @Override
    public void createComponent() {
        daggerApplicationComponent = DaggerMockApplicationComponent.builder()
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
