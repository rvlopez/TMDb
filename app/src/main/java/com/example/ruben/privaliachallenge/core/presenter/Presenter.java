package com.example.ruben.privaliachallenge.core.presenter;

import com.example.ruben.privaliachallenge.core.view.IView;

public abstract class Presenter<V extends IView> {

    protected V view;

    public void start() {
        if (view == null) {
            throw new IllegalArgumentException("Presenter's view can not be null.");
        }
    }

    public abstract void stop();

    public void setView(V view) {
        this.view = view;
    }
}

