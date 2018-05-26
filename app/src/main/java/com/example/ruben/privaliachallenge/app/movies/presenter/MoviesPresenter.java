package com.example.ruben.privaliachallenge.app.movies.presenter;

import com.example.ruben.privaliachallenge.app.movies.usecase.PopularMoviesUseCaseImpl;
import com.example.ruben.privaliachallenge.app.movies.usecase.SearchMovieUseCaseImpl;
import com.example.ruben.privaliachallenge.app.movies.view.MoviesActivityView;
import com.example.ruben.privaliachallenge.core.presenter.Presenter;
import com.example.ruben.privaliachallenge.entity.MovieEntity;
import com.example.ruben.privaliachallenge.entity.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class MoviesPresenter extends Presenter<MoviesActivityView> {

    private PopularMoviesUseCaseImpl popularMoviesUseCaseImpl;
    private SearchMovieUseCaseImpl searchMovieUseCaseImpl;

    @Inject
    public MoviesPresenter(PopularMoviesUseCaseImpl popularMoviesUseCaseImpl, SearchMovieUseCaseImpl searchMovieUseCaseImpl) {
        this.popularMoviesUseCaseImpl = popularMoviesUseCaseImpl;
        this.searchMovieUseCaseImpl = searchMovieUseCaseImpl;
    }

    public void loadPopularMovies(int page) {
        view.setSearchMode(false);
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
                view.showNoResults();
            }

            @Override
            public void onNext(ResponseEntity responseEntity) {
                movieList.addAll(responseEntity.getResults());
            }
        });
    }

    public void searchMovie(String movie, int page) {
        view.setSearchMode(true);
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
                    if (page < 2) {
                        view.showSearchMoviesData(movieList);
                    } else {
                        view.showMoviesData(movieList);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                view.hideProgress();
                view.showNoResults();
            }

            @Override
            public void onNext(ResponseEntity responseEntity) {
                movieList.addAll(responseEntity.getResults());
            }
        });
    }

    @Override
    public void stop() {
        popularMoviesUseCaseImpl.unsubscribe();
        searchMovieUseCaseImpl.unsubscribe();
    }
}
