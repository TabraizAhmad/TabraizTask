package com.assignment.tasktabraiz.di.applicationDI.module;

import android.content.Context;

import com.assignment.tasktabraiz.di.applicationDI.qualifier.ApplicationContextQualifier;
import com.assignment.tasktabraiz.di.applicationDI.scope.TaskApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private final Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @TaskApplicationScope
    @ApplicationContextQualifier
    public Context context() {
        return this.context;
    }
}