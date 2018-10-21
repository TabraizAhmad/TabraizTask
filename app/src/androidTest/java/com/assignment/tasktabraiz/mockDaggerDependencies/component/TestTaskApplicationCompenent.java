package com.assignment.tasktabraiz.mockDaggerDependencies.component;

import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationCompenent;
import com.assignment.tasktabraiz.di.applicationDI.module.PicassoModule;
import com.assignment.tasktabraiz.mockDaggerDependencies.module.MockMoviesRepositoryModule;
import com.assignment.tasktabraiz.mockDaggerDependencies.scope.TestTaskApplicationScope;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.Component;

@TestTaskApplicationScope
@Component(modules = {MockMoviesRepositoryModule.class} )
public class TestTaskApplicationCompenent implements TaskApplicationCompenent {

    @Override
    public void injectTaskApplicationCompenent(TaskApplication taskApplication) {

    }

    @Override
    public Picasso getPicasso() {
        return Picasso.get();
    }


}
