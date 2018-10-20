package com.assignment.tasktabraiz.moviedetail.view;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.base.view.BaseFragment;
import com.assignment.tasktabraiz.databinding.FragmentFilterBinding;
import com.assignment.tasktabraiz.moviedetail.model.FilterModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class FilterFragment extends BaseFragment implements View.OnClickListener {

    public static final String ARG_BEFORE = "beforeDate";
    public static final String ARG_AFTER = "afterDate";

    EditText etReleaseDateLTE;
    EditText etReleaseDateGTE;

    Button btnApply;
    String releaseBeforeDate;
    String releaseAfterDate;
    Calendar dateCalendar;
    private FilterModel filterModel;
    String myFormat = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance(String resleaseBefore, String releaseAfter) {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BEFORE, resleaseBefore);
        args.putString(ARG_AFTER, releaseAfter);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateCalendar = Calendar.getInstance();

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        filterModel = new FilterModel();
        if (getArguments() != null) {
            releaseBeforeDate = getArguments().getString(ARG_BEFORE);
            releaseAfterDate = getArguments().getString(ARG_AFTER);
            filterModel.setReleaseBeforeDate(releaseBeforeDate);
            filterModel.setReleaseAfterDate(releaseAfterDate);
        }

        FragmentFilterBinding fragmentFilterBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_filter,container,false);
        fragmentFilterBinding.setViewModel(filterModel);
        View view = fragmentFilterBinding.getRoot();

        etReleaseDateLTE = view.findViewById(R.id.etReleaseDateLTE);
        etReleaseDateGTE = view.findViewById(R.id.etReleaseDateGTE);
        btnApply = view.findViewById(R.id.btnApply);

        etReleaseDateLTE.setOnClickListener(this);
        etReleaseDateGTE.setOnClickListener(this);
        btnApply.setOnClickListener(this);
        return view;
    }

    final DatePickerDialog.OnDateSetListener onBeforeDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            dateCalendar.set(Calendar.YEAR, year);
            dateCalendar.set(Calendar.MONTH, monthOfYear);
            dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            filterModel.setReleaseBeforeDate(sdf.format(dateCalendar.getTime()));

        }

    };


    DatePickerDialog.OnDateSetListener onAfterDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            dateCalendar.set(Calendar.YEAR, year);
            dateCalendar.set(Calendar.MONTH, monthOfYear);
            dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            filterModel.setReleaseAfterDate(sdf.format(dateCalendar.getTime()));

        }

    };
    void showBeforeDateDialog(){
        new DatePickerDialog(Objects.requireNonNull(getContext()), onBeforeDateSetListener, dateCalendar
                .get(Calendar.YEAR), dateCalendar.get(Calendar.MONTH),
                dateCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    void showAfterDateDialog(){
        new DatePickerDialog(Objects.requireNonNull(getContext()), onAfterDateSetListener, dateCalendar
                .get(Calendar.YEAR), dateCalendar.get(Calendar.MONTH),
                dateCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.etReleaseDateLTE:
                showBeforeDateDialog();
                break;
            case R.id.etReleaseDateGTE:
                showAfterDateDialog();
                break;
            case R.id.btnApply:
                openListingFragment();
        }
    }

    private void openListingFragment() {

        MovieListingFragment movieListingFragment = MovieListingFragment.newInstance(filterModel.getReleaseBeforeDate(), filterModel.getReleaseAfterDate());
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, movieListingFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


}