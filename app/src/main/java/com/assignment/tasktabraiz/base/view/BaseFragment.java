package com.assignment.tasktabraiz.base.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.assignment.tasktabraiz.R;


public class BaseFragment extends Fragment {

    protected LinearLayout offlineContainer;
    protected ProgressBar progressBar;

    public void fragmentTrasition(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    protected void hideView(View view) {
        view.setVisibility(View.GONE);
    }

}
