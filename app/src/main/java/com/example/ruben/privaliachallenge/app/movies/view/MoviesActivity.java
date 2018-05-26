package com.example.ruben.privaliachallenge.app.movies.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
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

    @Inject public MoviesPresenter moviesPresenter;
    @Inject public MoviesAdapter moviesAdapter;

    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.moviesRecyclerView) RecyclerView recyclerView;
    @BindView(R.id.show_no_results) LinearLayout showNoResults;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.searchView) SearchView searchView;

    private MoviesComponent moviesComponent;
    private boolean isSearchMode;
    private String currentQuery;
    private int popularMoviesPage = 1;
    private int searchMoviePage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeSearchView();
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
    protected void initializeToolbar() {
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initializePresenter() {
        moviesPresenter.setView(this);
        moviesPresenter.loadPopularMovies(1);
    }

    private void initializeRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    if (isSearchMode) {
                        moviesPresenter.searchMovie(currentQuery, searchMoviePage += 1);
                    } else {
                        moviesPresenter.loadPopularMovies(popularMoviesPage += 1);
                    }
                }
            }
        });
    }

    private void initializeSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchMoviePage = 1;
                currentQuery = query;
                if (query.length() == 0) {
                    moviesAdapter.clearMovies();
                    moviesPresenter.loadPopularMovies(popularMoviesPage);
                } else {
                    moviesPresenter.searchMovie(query, searchMoviePage);
                }
                return false;
            }
        });
    }

    @Override
    public void showMoviesData(List<MovieEntity> movieList) {
        moviesAdapter.setMovies(movieList);
    }

    @Override
    public void showSearchMoviesData(List<MovieEntity> movieList) {
        moviesAdapter.replaceMovies(movieList);
    }

    public void setSearchMode(boolean searchMode) {
        isSearchMode = searchMode;
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
        recyclerView.setVisibility(View.GONE);
        showNoResults.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoResults() {
        recyclerView.setVisibility(View.VISIBLE);
        showNoResults.setVisibility(View.GONE);
    }

    @Override
    public MoviesComponent getComponent() {
        return moviesComponent;
    }
}
