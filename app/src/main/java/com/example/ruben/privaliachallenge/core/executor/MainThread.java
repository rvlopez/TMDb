package com.example.ruben.privaliachallenge.core.executor;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

public class MainThread {

    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
