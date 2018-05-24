package com.example.ruben.privaliachallenge.app.movies.presenter;

import com.example.ruben.privaliachallenge.app.movies.usecase.PopularMoviesUseCaseImpl;
import com.example.ruben.privaliachallenge.app.movies.usecase.SearchMovieUseCaseImpl;
import com.example.ruben.privaliachallenge.app.movies.view.MovieView;
import com.example.ruben.privaliachallenge.core.presenter.Presenter;
import com.example.ruben.privaliachallenge.entity.MovieEntity;
import com.example.ruben.privaliachallenge.entity.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class MoviePresenter extends Presenter<MovieView> {

    private PopularMoviesUseCaseImpl popularMoviesUseCaseImpl;
    private SearchMovieUseCaseImpl searchMovieUseCaseImpl;

    @Inject
    public MoviePresenter(PopularMoviesUseCaseImpl popularMoviesUseCaseImpl, SearchMovieUseCaseImpl searchMovieUseCaseImpl) {
        this.popularMoviesUseCaseImpl = popularMoviesUseCaseImpl;
        this.searchMovieUseCaseImpl = searchMovieUseCaseImpl;
    }

    public void loadPopularMovies(int page) {
        view.showProgress();

        popularMoviesUseCaseImpl.execute(page, new Subscriber<ResponseEntity>() {

            List<MovieEntity> movieList = new ArrayList<>();

            @Override
            public void onCompleted() {
                view.hideProgress();

                if (movieList.isEmpty()) {
                    view.showNoResults();
                } else {
                    view.hideNoResults();
                    view.showMoviesData(movieList);
                }
            }

            @Override
            public void onError(Throwable e) {
                view.hideProgress();
            }

            @Override
            public void onNext(ResponseEntity responseEntity) {
                movieList.addAll(responseEntity.getResults());
            }
        });
    }

    public void searchMovie(String movie, int page) {
        view.showProgress();

        searchMovieUseCaseImpl.execute(movie, page, new Subscriber<ResponseEntity>() {

            List<MovieEntity> movieList = new ArrayList<>();

            @Override
            public void onCompleted() {
                view.hideProgress();

                if (movieList.isEmpty()) {
                    view.showNoResults();
                } else {
                    view.hideNoResults();
                    view.showMoviesData(movieList);
                }
            }

            @Override
            public void onError(Throwable e) {
                view.hideProgress();
            }

            @Override
            public void onNext(ResponseEntity responseEntity) {
                movieList.addAll(responseEntity.getResults());
            }
        });
    }

    @Override
    protected void initialize() {

    }

    @Override
    public void stop() {
        popularMoviesUseCaseImpl.unsubscribe();
        searchMovieUseCaseImpl.unsubscribe();
    }
}
