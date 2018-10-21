package com.assignment.tasktabraiz.di.moviedetailDI.module;

import com.assignment.tasktabraiz.di.moviedetailDI.scope.FilterFragmentScope;
import com.assignment.tasktabraiz.moviedetail.model.FilterModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class FilterFragmentModule {

    @Provides
    @FilterFragmentScope
    public SimpleDateFormat simpleDateFormat() {
        String myFormat = "yyyy-MM-dd";
        return new SimpleDateFormat(myFormat, Locale.US);

    }
    @Provides
    @FilterFragmentScope
    public FilterModel filterModel(){
        return  new FilterModel();
    }

    @Provides
    @Inject
    public Calendar calendar(){
        return  Calendar.getInstance();
    }

}