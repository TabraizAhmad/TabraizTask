package com.assignment.tasktabraiz.moviedetail.view;

import android.os.Bundle;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.base.view.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TaskApplication)getApplication()).createComponent();
        setContentView(R.layout.activity_home);
        if(savedInstanceState == null)  {
            replaceFragment(R.id.fragment_container, MovieListingFragment.newInstance(),
                    false);
        }
    }
}
