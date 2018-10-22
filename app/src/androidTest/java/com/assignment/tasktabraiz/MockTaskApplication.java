package com.assignment.tasktabraiz;

import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationCompenent;
import com.assignment.tasktabraiz.mockDaggerDependencies.component.DaggerMockApplicationCompenent;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

public class MockTaskApplication extends TaskApplication {

    private MoviesRepository moviesRepository;
    private Picasso picasso;

    private TaskApplicationCompenent daggerApplicationCompenent;

    @Override
    public void createComponent() {
        daggerApplicationCompenent = DaggerMockApplicationCompenent.builder()
                .build();
        picasso = daggerApplicationCompenent.getPicasso();
        moviesRepository = daggerApplicationCompenent.getMoviesRepository();
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
