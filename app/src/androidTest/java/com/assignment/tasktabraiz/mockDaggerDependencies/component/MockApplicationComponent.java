package com.assignment.tasktabraiz.mockDaggerDependencies.component;

import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationComponent;
import com.assignment.tasktabraiz.mockDaggerDependencies.module.MockMoviesRepositoryModule;
import com.assignment.tasktabraiz.mockDaggerDependencies.scope.TestTaskApplicationScope;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.squareup.picasso.Picasso;

import dagger.Component;

@TestTaskApplicationScope
@Component(modules = {MockMoviesRepositoryModule.class} )
public interface MockApplicationComponent extends TaskApplicationComponent {

    @Override
    MoviesRepository getMoviesRepository();

    @Override
    Picasso getPicasso();


}
