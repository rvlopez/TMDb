package com.example.ruben.privaliachallenge.app.movies.usecase;

import com.example.ruben.privaliachallenge.core.usecase.UseCase;
import com.example.ruben.privaliachallenge.data.repository.TMDbRepository;
import com.example.ruben.privaliachallenge.entity.ResponseEntity;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PopularMoviesUseCaseImpl extends UseCase implements PopularMoviesUseCase {

    @Inject
    public PopularMoviesUseCaseImpl(TMDbRepository tmdbRepository) {
        super(tmdbRepository);
    }

    @Override
    public void execute(int page, Subscriber<ResponseEntity> subscriber) {
        subscription = buildObservable(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    @Override
    public Observable<ResponseEntity> buildObservable(int page) {
        return tmdbRepository.getPopularMovies(page);
    }
}
