package com.example.ruben.privaliachallenge.app.movies.view;

import com.example.ruben.privaliachallenge.core.view.IView;
import com.example.ruben.privaliachallenge.entity.MovieEntity;

import java.util.List;

public interface MovieView extends IView {

    void showMoviesData(List<MovieEntity> movieList);

    void showProgress();

    void hideProgress();

    void showNoResults();

    void hideNoResults();

}
