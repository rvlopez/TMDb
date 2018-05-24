package com.example.ruben.privaliachallenge.core.usecase;

import com.example.ruben.privaliachallenge.data.repository.TMDbRepository;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

public abstract class UseCase {

    protected Subscription subscription = Subscriptions.empty();
    protected final TMDbRepository tmdbRepository;

    public UseCase(TMDbRepository tmdbRepository) {
        if (tmdbRepository == null) {
            throw new IllegalArgumentException("TMDbRepository must have valid parameters");
        }
        this.tmdbRepository = tmdbRepository;
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
