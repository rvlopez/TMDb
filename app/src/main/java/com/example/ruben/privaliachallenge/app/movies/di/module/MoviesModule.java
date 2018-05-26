package com.example.ruben.privaliachallenge.app.movies.di.module;

import com.example.ruben.privaliachallenge.app.movies.presenter.MoviesPresenter;
import com.example.ruben.privaliachallenge.app.movies.usecase.PopularMoviesUseCase;
import com.example.ruben.privaliachallenge.app.movies.usecase.PopularMoviesUseCaseImpl;
import com.example.ruben.privaliachallenge.app.movies.usecase.SearchMovieUseCase;
import com.example.ruben.privaliachallenge.app.movies.usecase.SearchMovieUseCaseImpl;
import com.example.ruben.privaliachallenge.core.executor.MainThread;
import com.example.ruben.privaliachallenge.data.repository.TMDbRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesModule {

    @Provides
    public MoviesPresenter provideMoviePresenter(PopularMoviesUseCaseImpl popularMoviesUseCaseImpl, SearchMovieUseCaseImpl searchMovieUseCaseImpl) {
        return  new MoviesPresenter(popularMoviesUseCaseImpl, searchMovieUseCaseImpl);
    }

    @Provides
    public PopularMoviesUseCase providePopularMoviesUseCase(MainThread mainThread, TMDbRepository tmdbRepository) {
        return new PopularMoviesUseCaseImpl(mainThread, tmdbRepository);
    }

    @Provides
    public SearchMovieUseCase provideSearchMovieUseCase(MainThread mainThread, TMDbRepository tmdbRepository) {
        return new SearchMovieUseCaseImpl(mainThread, tmdbRepository);
    }
}
