package com.assignment.tasktabraiz.moviedetail.databindingdefaults;

import android.widget.ImageView;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.common.Constants;
import com.squareup.picasso.Picasso;

public class PicassoImageViewBinding implements ImageViewBindingInterface {

    private Picasso picasso;

    PicassoImageViewBinding(Picasso picasso) {
        this.picasso = picasso;
    }
    @Override
    public void loadImage(ImageView view, String posterPath ) {
        picasso.load(Constants.POSTER_IMAGE_BASE_PATH + posterPath ).placeholder(R.drawable.placeholder_movie)
                .into(view);
    }
}
