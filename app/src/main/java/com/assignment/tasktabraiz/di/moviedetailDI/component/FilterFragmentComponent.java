package com.assignment.tasktabraiz.di.moviedetailDI.component;

import com.assignment.tasktabraiz.di.moviedetailDI.module.FilterFragmentModule;
import com.assignment.tasktabraiz.di.moviedetailDI.scope.FilterFragmentScope;
import com.assignment.tasktabraiz.moviedetail.view.FilterFragment;

import dagger.Component;


@FilterFragmentScope
@Component(modules = {FilterFragmentModule.class})
public interface FilterFragmentComponent {

    void injectFilterFragmentComponent(FilterFragment filterFragment);
}
