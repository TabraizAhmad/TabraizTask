package com.assignment.tasktabraiz.movielisting.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.databinding.RowMovieBinding;
import com.assignment.tasktabraiz.movielisting.model.MovieData;
import com.assignment.tasktabraiz.movielisting.viewmodel.databindingdefaults.DefaultDataBindingComponent;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieData> movieDataList;
    private final int NO_MOVIE = 0;
    private static Picasso picasso;

    private boolean isLoadingAdded = false;
    private static final int ITEM = 0;
    private static final int LOADING = 1;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RowMovieBinding rowMovieBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_movie,
                parent,
                false, new DefaultDataBindingComponent(picasso));
        return new BindingHolder(rowMovieBinding);    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case  ITEM:
                RowMovieBinding rowMovieBinding = ( (BindingHolder) holder).binding;
                rowMovieBinding.setViewModel(movieDataList.get(position));
            case LOADING:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return movieDataList==null ? NO_MOVIE : movieDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == movieDataList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }
    public void setItems(List<MovieData> movieDataArrayList) {
        movieDataList = movieDataArrayList;
        notifyDataSetChanged();
    }

    public void addMovie(MovieData movieData) {
        movieDataList.add(movieData);
        notifyItemChanged(movieDataList.size() -1);
    }


    class BindingHolder extends RecyclerView.ViewHolder {
        private RowMovieBinding binding;

        BindingHolder(RowMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public MovieListingAdapter(Picasso picasso) {
        MovieListingAdapter.picasso = picasso;
    }

}
