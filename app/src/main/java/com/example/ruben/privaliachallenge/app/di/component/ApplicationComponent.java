package com.example.ruben.privaliachallenge.app.di.component;

import com.example.ruben.privaliachallenge.TMDbApplication;
import com.example.ruben.privaliachallenge.app.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(TMDbApplication application);

    // Added application sub-component


}
