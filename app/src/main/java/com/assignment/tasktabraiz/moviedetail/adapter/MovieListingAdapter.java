package com.assignment.tasktabraiz.moviedetail.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.tasktabraiz.R;
import com.assignment.tasktabraiz.databinding.RowMovieBinding;
import com.assignment.tasktabraiz.moviedetail.databindingdefaults.DefaultDataBindingComponent;
import com.assignment.tasktabraiz.moviedetail.model.MovieData;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class MovieListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieData> movieDataList;
    private Picasso picasso;

    private boolean isLoadingAdded = false;

    private final int NO_MOVIE = 0;
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    @Inject
    public MovieListingAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType){
            case ITEM:
                RowMovieBinding rowMovieBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.row_movie,
                        parent,
                        false, new DefaultDataBindingComponent(picasso));
                viewHolder = new BindingHolder(rowMovieBinding);
                break;
            case LOADING:
            default:
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View viewLoading = inflater.inflate(R.layout.row_progress, parent, false);
                viewHolder = new LoadingVH(viewLoading);
                break;
        }
        return viewHolder;
    }

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

    @Override
    public long getItemId(int position) {
        if (movieDataList != null && movieDataList.size() >= position) {
            return movieDataList.get(position).getId();
        } else {
            return -1;
        }
    }


    public void setItems(List<MovieData> movieDataArrayList) {
        movieDataList = movieDataArrayList;
        notifyDataSetChanged();
    }

    public void add(MovieData movieData) {
        movieDataList.add(movieData);
        notifyItemChanged(movieDataList.size() -1);
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new MovieData());
    }

    public void addAll(List<MovieData> moveResults) {
        for (MovieData data : moveResults) {
            add(data);
        }
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movieDataList.size() - 1;
        if(position > 0){
            MovieData movieData = movieDataList.get(position);
            if (movieData != null) {
                movieDataList.remove(position);
                notifyItemRemoved(position);
            }
        }

    }


    class BindingHolder extends RecyclerView.ViewHolder {
        private RowMovieBinding binding;

        BindingHolder(RowMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    protected class LoadingVH extends RecyclerView.ViewHolder{
        LoadingVH(View itemView) {
            super(itemView);
        }
    }


}
