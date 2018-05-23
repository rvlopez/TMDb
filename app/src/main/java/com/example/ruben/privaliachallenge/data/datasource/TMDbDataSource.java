package com.example.ruben.privaliachallenge.data.datasource;


import com.example.ruben.privaliachallenge.data.datasource.mapper.TMDbDtoMapper;
import com.example.ruben.privaliachallenge.entity.ResponseEntity;

import javax.inject.Inject;

import rx.Observable;

public class TMDbDataSource {

    private final TMDbApi tmdbApi;

    private final TMDbDtoMapper tmdbDtoMapper;

    @Inject
    public TMDbDataSource(TMDbApi tmdbApi, TMDbDtoMapper tmdbDtoMapper) {
        this.tmdbApi = tmdbApi;
        this.tmdbDtoMapper = tmdbDtoMapper;
    }

    public Observable<ResponseEntity> getPopularMovies(int page) {
        return tmdbApi.getPopularMovies(page).map(tmdbDtoMapper::toEntity);
    }

    public Observable<ResponseEntity> searchMovie(String movie, int page) {
        return tmdbApi.searchMovie(movie, page).map(tmdbDtoMapper::toEntity);
    }
}
