package com.example.ruben.privaliachallenge.data.datasource;


import com.example.ruben.privaliachallenge.data.datasource.mapper.TMDbDtoMapper;
import com.example.ruben.privaliachallenge.entity.ResponseEntity;

import rx.Observable;

public class TMDbDataSource {

    private final TMDbApi tmdbApi;

    private final TMDbDtoMapper tmdbDtoMapper;

    public TMDbDataSource(TMDbApi tmdbApi, TMDbDtoMapper tmdbDtoMapper) {
        this.tmdbApi = tmdbApi;
        this.tmdbDtoMapper = tmdbDtoMapper;
    }

    public Observable<ResponseEntity> getPopularMovies(int page) {
        return tmdbApi.getPopularMovies(page).map(tmdbDtoMapper::toEntity);
    }


}
