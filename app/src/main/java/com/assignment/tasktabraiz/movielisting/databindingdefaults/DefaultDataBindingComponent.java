package com.assignment.tasktabraiz.movielisting.databindingdefaults;

import com.squareup.picasso.Picasso;

public class DefaultDataBindingComponent implements android.databinding.DataBindingComponent {

    Picasso picasso;

    public DefaultDataBindingComponent(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public ImageViewBindingInterface getImageViewBindingInterface() {
        return new PicassoImageViewBinding(picasso);
    }
}