package com.assignment.tasktabraiz.moviedetail.view;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.base.view.BaseFragment;
import com.assignment.tasktabraiz.databinding.FragmentFilterBinding;
import com.assignment.tasktabraiz.di.moviedetailDI.component.DaggerFilterFragmentComponent;
import com.assignment.tasktabraiz.di.moviedetailDI.component.FilterFragmentComponent;
import com.assignment.tasktabraiz.moviedetail.model.FilterModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import javax.inject.Inject;

public class FilterFragment extends BaseFragment implements View.OnClickListener {

    private static final String ARG_BEFORE = "beforeDate";
    private static final String ARG_AFTER = "afterDate";

    private String releaseBeforeDate;
    private String releaseAfterDate;

    @Inject
    Calendar dateCalendar;

    @Inject
    FilterModel filterModel;

    @Inject
    SimpleDateFormat sdf;

    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance(String releaseBefore, String releaseAfter) {
        FilterFragment fragment = new FilterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BEFORE, releaseBefore);
        args.putString(ARG_AFTER, releaseAfter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FilterFragmentComponent component = DaggerFilterFragmentComponent.builder().build();
        component.injectFilterFragmentComponent(this);
        Bundle bundle = getArguments();
        if (bundle != null) {
            setValuesFromBundle(bundle);
        }

        FragmentFilterBinding fragmentFilterBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_filter,
                container, false);

        fragmentFilterBinding.setViewModel(filterModel);
        View view = fragmentFilterBinding.getRoot();
        initFragmentView(view);
        return view;
    }

    private void setValuesFromBundle(Bundle bundle) {
        releaseBeforeDate = bundle.getString(ARG_BEFORE);
        releaseAfterDate = bundle.getString(ARG_AFTER);
        filterModel.setReleaseBeforeDate(releaseBeforeDate);
        filterModel.setReleaseAfterDate(releaseAfterDate);
    }

    private void initFragmentView(View view) {
        view.findViewById(R.id.etReleaseDateLTE).setOnClickListener(this);
        view.findViewById(R.id.etReleaseDateGTE).setOnClickListener(this);
        view.findViewById(R.id.btnApply).setOnClickListener(this);
        view.findViewById(R.id.btnClearFilter).setOnClickListener(this);

    }

    private final DatePickerDialog.OnDateSetListener onBeforeDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    dateCalendar.set(Calendar.YEAR, year);
                    dateCalendar.set(Calendar.MONTH, monthOfYear);
                    dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    filterModel.setReleaseBeforeDate(sdf.format(dateCalendar.getTime()));

                }
            };


    private DatePickerDialog.OnDateSetListener onAfterDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    dateCalendar.set(Calendar.YEAR, year);
                    dateCalendar.set(Calendar.MONTH, monthOfYear);
                    dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    filterModel.setReleaseAfterDate(sdf.format(dateCalendar.getTime()));
                }
            };

    private void showBeforeDateDialog() {
        new DatePickerDialog(Objects.requireNonNull(getContext()),
                onBeforeDateSetListener, dateCalendar
                .get(Calendar.YEAR), dateCalendar.get(Calendar.MONTH),
                dateCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showAfterDateDialog() {
        new DatePickerDialog(Objects.requireNonNull(getContext()),
                onAfterDateSetListener, dateCalendar
                .get(Calendar.YEAR), dateCalendar.get(Calendar.MONTH),
                dateCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etReleaseDateLTE:
                showBeforeDateDialog();
                break;
            case R.id.etReleaseDateGTE:
                showAfterDateDialog();
                break;
            case R.id.btnApply:
                openListingFragment();
                break;
            case R.id.btnClearFilter:
                filterModel.setReleaseBeforeDate("");
                filterModel.setReleaseAfterDate("");
                openListingFragment();
                break;
            default:
                break;
        }
    }

    private void openListingFragment() {
        MovieListingFragment movieListingFragment = MovieListingFragment.newInstance(
                filterModel.getReleaseBeforeDate(), filterModel.getReleaseAfterDate());
        fragmentTransition(movieListingFragment);
    }
}
