package com.example.ruben.privaliachallenge;

import android.app.Application;

import com.example.ruben.privaliachallenge.app.di.component.ApplicationComponent;
import com.example.ruben.privaliachallenge.app.di.component.DaggerApplicationComponent;
import com.example.ruben.privaliachallenge.app.di.module.ApplicationModule;
import com.example.ruben.privaliachallenge.core.di.HasComponent;

public class TMDbApplication extends Application implements HasComponent<ApplicationComponent> {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();

    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
    }

    public void setComponent(ApplicationComponent component) {
        this.applicationComponent = component;
    }

    @Override
    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
