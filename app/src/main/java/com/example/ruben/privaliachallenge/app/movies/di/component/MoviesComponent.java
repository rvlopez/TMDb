package com.example.ruben.privaliachallenge.app.movies.di.component;

import com.example.ruben.privaliachallenge.app.movies.di.module.MoviesModule;
import com.example.ruben.privaliachallenge.app.movies.view.MoviesActivity;

import dagger.Subcomponent;

@Subcomponent(modules = MoviesModule.class)
public interface MoviesComponent {

    void inject(MoviesActivity moviesActivity);

}
