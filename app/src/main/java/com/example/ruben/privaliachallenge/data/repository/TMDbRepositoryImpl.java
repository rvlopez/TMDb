package com.example.ruben.privaliachallenge.data.repository;

import com.example.ruben.privaliachallenge.data.datasource.TMDbDataSource;
import com.example.ruben.privaliachallenge.entity.ResponseEntity;

import javax.inject.Inject;

import rx.Observable;

public class TMDbRepositoryImpl implements TMDbRepository {

    private final TMDbDataSource tmdbDataSource;

    @Inject
    public TMDbRepositoryImpl(TMDbDataSource tmdbDataSource) {
        if (tmdbDataSource == null) {
            throw new IllegalArgumentException("TMDbRepository parameters can't be null");
        }
        this.tmdbDataSource = tmdbDataSource;
    }


    @Override
    public Observable<ResponseEntity> getPopularMovies(int page) {
        return tmdbDataSource.getPopularMovies(page);
    }

    @Override
    public Observable<ResponseEntity> searchMovie(String movie, int page) {
        return tmdbDataSource.searchMovie(movie, page);
    }
}
