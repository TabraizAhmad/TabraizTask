package com.assignment.tasktabraiz.moviedetail.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.assignment.tasktabraiz.BR;


public class FilterModel extends BaseObservable {
    private String releaseBeforeDate;
    private String releaseAfterDate;

    @Bindable
    public String getReleaseBeforeDate() {
        return releaseBeforeDate;
    }

    public void setReleaseBeforeDate(String releaseBeforeDate) {
        this.releaseBeforeDate = releaseBeforeDate;
        notifyPropertyChanged(BR.releaseBeforeDate);
    }

    @Bindable
    public String getReleaseAfterDate() {
        return releaseAfterDate;
    }

    public void setReleaseAfterDate(String releaseAfterDate) {
        this.releaseAfterDate = releaseAfterDate;
        notifyPropertyChanged(BR.releaseAfterDate);

    }
}
