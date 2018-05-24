package com.example.ruben.privaliachallenge.app.movies.usecase;

import com.example.ruben.privaliachallenge.entity.ResponseEntity;

import rx.Observable;
import rx.Subscriber;

public interface SearchMovieUseCase {

    void execute(String movie, int page, Subscriber<ResponseEntity> subscriber);

    Observable<ResponseEntity> buildObservable(String movie, int page);
}
