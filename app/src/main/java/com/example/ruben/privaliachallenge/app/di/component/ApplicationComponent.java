package com.example.ruben.privaliachallenge.app.di.component;

import com.example.ruben.privaliachallenge.TMDbApplication;
import com.example.ruben.privaliachallenge.app.di.module.ApplicationModule;
import com.example.ruben.privaliachallenge.app.movies.di.component.MoviesComponent;
import com.example.ruben.privaliachallenge.app.movies.di.module.MoviesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(TMDbApplication application);

    // Added application sub-component
    MoviesComponent moviesComponent(MoviesModule module);

}
