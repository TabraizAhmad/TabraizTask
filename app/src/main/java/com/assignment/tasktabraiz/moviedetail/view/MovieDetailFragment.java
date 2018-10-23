package com.assignment.tasktabraiz.moviedetail.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.application.TaskApplication;
import com.assignment.tasktabraiz.base.view.BaseFragment;
import com.assignment.tasktabraiz.databinding.FragmentMovieDetailBinding;
import com.assignment.tasktabraiz.di.moviedetailDI.component.DaggerMovieDetailFragmentComponent;
import com.assignment.tasktabraiz.di.moviedetailDI.component.MovieDetailFragmentComponent;
import com.assignment.tasktabraiz.moviedetail.databindingdefaults.DefaultDataBindingComponent;
import com.assignment.tasktabraiz.moviedetail.model.MovieDetail;
import com.assignment.tasktabraiz.moviedetail.viewmodel.MovieDetailViewModel;
import com.assignment.tasktabraiz.network.util.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import javax.inject.Inject;

public class MovieDetailFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_MOVIE_ID = "movieId";
    private Integer movieId;

    private MovieDetailViewModel movieDetailViewModel;
    private RelativeLayout movieDetailLayout;
    private MovieDetail movieDetail;
    private ViewDataBinding fragmentMovieDetailBinding;

    @Inject
    Picasso picasso;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance(Integer movieId) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            movieId = bundle.getInt(ARG_MOVIE_ID);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        MovieDetailFragmentComponent component = DaggerMovieDetailFragmentComponent.builder()
                .taskApplicationComponent((
                        (TaskApplication) Objects.requireNonNull(getActivity()).getApplication())
                        .getDaggerApplicationComponent())
                .build();
        component.injectMovieDetailFragment(this);
        fragmentMovieDetailBinding = DataBindingUtil.inflate(
                inflater,R.layout.fragment_movie_detail,
                container,false,
                new DefaultDataBindingComponent(picasso));
        View view = fragmentMovieDetailBinding.getRoot();
        initView(view);
        return view;
    }

    private void initView(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        offlineContainer = view.findViewById(R.id.layoutOffline);
        movieDetailLayout = view.findViewById(R.id.movieDetailLayout);
        view.findViewById(R.id.btnTryAgain).setOnClickListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        Context context = getContext();
        if (context == null) return;
        if (NetworkUtils.isNetworkAvailable(context)) {
            showHideOfflineLayout(false);
            loadMovieDetail();
        } else {
            showHideOfflineLayout(true);
            hideView(progressBar);
        }
    }


    private void loadMovieDetail() {
        movieDetailViewModel.fetchMovieDetail(movieId).observe(this,response ->{
            if (response != null) {
                movieDetail = response;
                movieDetailLayout.setVisibility(View.VISIBLE);
                hideView(progressBar);
                    ((FragmentMovieDetailBinding) fragmentMovieDetailBinding).setViewModel(movieDetail);
            }else {
                showHideOfflineLayout(true);
            }
        });
    }

    private void showHideOfflineLayout(boolean isOffline) {
        offlineContainer.setVisibility(isOffline ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(isOffline ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTryAgain:
                loadDataFromApi();
                break;
        }
    }
}
