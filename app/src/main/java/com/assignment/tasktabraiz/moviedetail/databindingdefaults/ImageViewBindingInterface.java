package com.assignment.tasktabraiz.moviedetail.databindingdefaults;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public interface ImageViewBindingInterface {
    @BindingAdapter({"app:posterPath"})
    void loadImage(ImageView view, String url);
}