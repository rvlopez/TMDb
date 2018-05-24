package com.example.ruben.privaliachallenge.app.movies.di.module;

import com.example.ruben.privaliachallenge.app.movies.presenter.MoviePresenter;
import com.example.ruben.privaliachallenge.app.movies.usecase.PopularMoviesUseCase;
import com.example.ruben.privaliachallenge.app.movies.usecase.PopularMoviesUseCaseImpl;
import com.example.ruben.privaliachallenge.app.movies.usecase.SearchMovieUseCase;
import com.example.ruben.privaliachallenge.app.movies.usecase.SearchMovieUseCaseImpl;
import com.example.ruben.privaliachallenge.data.repository.TMDbRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    public MoviePresenter provideMoviePresenter(PopularMoviesUseCaseImpl popularMoviesUseCaseImpl, SearchMovieUseCaseImpl searchMovieUseCaseImpl) {
        return  new MoviePresenter(popularMoviesUseCaseImpl, searchMovieUseCaseImpl);
    }

    @Provides
    public PopularMoviesUseCase providePopularMoviesUseCase(TMDbRepository tmdbRepository) {
        return new PopularMoviesUseCaseImpl(tmdbRepository);
    }

    @Provides
    public SearchMovieUseCase provideSearchMovieUseCase(TMDbRepository tmdbRepository) {
        return new SearchMovieUseCaseImpl(tmdbRepository);
    }
}
