package com.assignment.tasktabraiz;

import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationCompenent;
import com.assignment.tasktabraiz.mockDaggerDependencies.component.DaggerMockApplicationCompenent;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

public class MockTaskApplication extends TaskApplication {

    private TaskApplicationCompenent daggerApplicationCompenent;

    @Override
    public void createComponent() {
        daggerApplicationCompenent = DaggerMockApplicationCompenent.builder()
                .build();
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
