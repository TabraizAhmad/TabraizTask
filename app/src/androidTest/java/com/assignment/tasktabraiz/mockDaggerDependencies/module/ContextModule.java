package com.assignment.tasktabraiz.mockDaggerDependencies.module;

import android.content.Context;

import com.assignment.tasktabraiz.mockDaggerDependencies.qualifier.ApplicationContextQualifier;
import com.assignment.tasktabraiz.mockDaggerDependencies.scope.TestTaskApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @TestTaskApplicationScope
    @ApplicationContextQualifier
    public Context context() {
        return this.context;
    }
}