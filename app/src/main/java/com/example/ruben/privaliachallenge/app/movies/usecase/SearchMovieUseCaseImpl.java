package com.example.ruben.privaliachallenge.app.movies.usecase;

import com.example.ruben.privaliachallenge.core.executor.MainThread;
import com.example.ruben.privaliachallenge.core.usecase.UseCase;
import com.example.ruben.privaliachallenge.data.repository.TMDbRepository;
import com.example.ruben.privaliachallenge.entity.ResponseEntity;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchMovieUseCaseImpl extends UseCase implements SearchMovieUseCase {

    @Inject
    public SearchMovieUseCaseImpl(MainThread mainThread, TMDbRepository tmdbRepository) {
       super(mainThread, tmdbRepository);
    }

    @Override
    public void execute(String movie, int page, Subscriber<ResponseEntity> subscriber) {
        subscription = buildObservable(movie, page)
                .observeOn(mainThread.getScheduler())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    @Override
    public Observable<ResponseEntity> buildObservable(String movie, int page) {
        return tmdbRepository.searchMovie(movie, page);
    }
}
