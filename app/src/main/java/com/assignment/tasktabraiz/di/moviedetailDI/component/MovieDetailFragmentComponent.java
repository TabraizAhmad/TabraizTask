package com.assignment.tasktabraiz.di.moviedetailDI.component;

import com.assignment.tasktabraiz.di.applicationDI.component.TaskApplicationCompenent;
import com.assignment.tasktabraiz.di.moviedetailDI.scope.MovieListingFragmentScope;
import com.assignment.tasktabraiz.moviedetail.view.MovieDetailFragment;
import com.assignment.tasktabraiz.moviedetail.view.MovieListingFragment;

import dagger.Component;


@MovieListingFragmentScope
@Component(modules = {},dependencies = {TaskApplicationCompenent.class})
public interface MovieDetailFragmentComponent {

    void injectMovieDetailFragment(MovieDetailFragment movieListingFragment);
}
