package com.example.ruben.privaliachallenge.app.movies.usecase;

import com.example.ruben.privaliachallenge.entity.ResponseEntity;

import rx.Observable;
import rx.Subscriber;

public interface PopularMoviesUseCase {

    void execute(int page, Subscriber<ResponseEntity> subscriber);

    Observable<ResponseEntity> buildObservable(int page);
}
