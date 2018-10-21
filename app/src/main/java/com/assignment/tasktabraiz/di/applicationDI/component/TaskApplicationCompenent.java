package com.assignment.tasktabraiz.di.applicationDI.component;

import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.di.applicationDI.module.MoviesRepositoryModule;
import com.assignment.tasktabraiz.di.applicationDI.module.PicassoModule;
import com.assignment.tasktabraiz.di.applicationDI.scope.TaskApplicationScope;
import com.assignment.tasktabraiz.moviedetail.repository.MoviesRepository;
import com.assignment.tasktabraiz.moviedetail.view.FilterFragment;
import com.squareup.picasso.Picasso;

import dagger.Component;
@TaskApplicationScope
@Component(modules = {MoviesRepositoryModule.class, PicassoModule.class} )
public interface TaskApplicationCompenent  {
    void injectTaskApplicationCompenent(TaskApplication taskApplication);
    Picasso getPicasso();
}
