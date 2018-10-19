package com.assignment.tasktabraiz.movielisting.view;

import android.os.Bundle;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.base.view.BaseActivity;

public class MovieListingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_listing);
        if(savedInstanceState == null)  {

            replaceFragment(R.id.fragment_container, MovieListingFragment.newInstance(),
                    false);
        }
    }
}
