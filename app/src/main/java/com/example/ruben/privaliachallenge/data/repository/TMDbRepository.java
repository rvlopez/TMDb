package com.example.ruben.privaliachallenge.data.repository;

import com.example.ruben.privaliachallenge.entity.ResponseEntity;

import rx.Observable;

public interface TMDbRepository {

    Observable<ResponseEntity> getPopularMovies(int page);

    Observable<ResponseEntity> searchMovie(String movie, int page);
}
