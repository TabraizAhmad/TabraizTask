package com.assignment.tasktabraiz.movielisting.viewmodel.databindingdefaults;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public interface ImageViewBindingInterface {
    @BindingAdapter({"app:posterPath"})
    void loadImage(ImageView view, String url);
}