package com.example.ruben.privaliachallenge.app.movies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ruben.privaliachallenge.R;
import com.example.ruben.privaliachallenge.app.TMDbConstants;
import com.example.ruben.privaliachallenge.entity.MovieEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<MovieEntity> movieList = new ArrayList<>();

    @Inject
    public MoviesAdapter() {
    }

    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.MoviesViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        MovieEntity movieEntity = movieList.get(position);

        Glide.with(context)
                .load(TMDbConstants.IMAGE_BASE_URL + movieEntity.getPosterPath())
                .into(holder.movieImage);
        holder.movieTitle.setText(movieEntity.getTitle());
        holder.movieYear.setText(movieEntity.getReleaseDate());
        holder.movieDescription.setText(movieEntity.getOverview());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovies(List<MovieEntity> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_image) ImageView movieImage;
        @BindView(R.id.movie_title) TextView movieTitle;
        @BindView(R.id.movie_year) TextView movieYear;
        @BindView(R.id.movie_description) TextView movieDescription;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
