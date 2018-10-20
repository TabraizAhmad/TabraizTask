package com.assignment.tasktabraiz.base.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.assignment.tasktabraiz.R;


public class BaseFragment extends Fragment {

    public void fragmentTrasition(Fragment fragment){
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
