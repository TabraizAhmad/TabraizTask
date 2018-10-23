package com.assignment.tasktabraiz.di.moviedetailDI.component;

import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationComponent;
import com.assignment.tasktabraiz.di.moviedetailDI.scope.MovieListingFragmentScope;
import com.assignment.tasktabraiz.moviedetail.view.MovieDetailFragment;

import dagger.Component;


@MovieListingFragmentScope
@Component(modules = {},dependencies = {TaskApplicationComponent.class})
public interface MovieDetailFragmentComponent {

    void injectMovieDetailFragment(MovieDetailFragment movieListingFragment);
}
