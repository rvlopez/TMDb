package com.example.ruben.privaliachallenge.app.movies.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ruben.privaliachallenge.R;
import com.example.ruben.privaliachallenge.TMDbApplication;
import com.example.ruben.privaliachallenge.app.movies.adapter.MoviesAdapter;
import com.example.ruben.privaliachallenge.app.movies.di.component.MoviesComponent;
import com.example.ruben.privaliachallenge.app.movies.di.module.MoviesModule;
import com.example.ruben.privaliachallenge.app.movies.presenter.MoviesPresenter;
import com.example.ruben.privaliachallenge.core.di.HasComponent;
import com.example.ruben.privaliachallenge.core.view.RootActivity;
import com.example.ruben.privaliachallenge.entity.MovieEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MoviesActivity extends RootActivity implements MoviesActivityView, HasComponent<MoviesComponent> {

    private MoviesComponent moviesComponent;

    @Inject private MoviesPresenter moviesPresenter;

    @Inject private MoviesAdapter moviesAdapter;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.moviesRecyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeRecycler();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_movies;
    }

    @Override
    protected void initializeInjector() {
        moviesComponent = ((TMDbApplication) getApplication())
                .getComponent().moviesComponent(new MoviesModule());
        moviesComponent.inject(this);
    }

    @Override
    protected void initializePresenter() {
        moviesPresenter.setView(this);
    }

    private void initializeRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void showMoviesData(List<MovieEntity> movieList) {
        moviesAdapter.setMovies(movieList);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoResults() {

    }

    @Override
    public void hideNoResults() {

    }

    @Override
    public MoviesComponent getComponent() {
        return moviesComponent;
    }
}
