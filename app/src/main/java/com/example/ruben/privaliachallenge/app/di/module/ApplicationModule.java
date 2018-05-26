package com.example.ruben.privaliachallenge.app.di.module;

import android.content.Context;

import com.example.ruben.privaliachallenge.app.TMDbConstants;
import com.example.ruben.privaliachallenge.core.executor.MainThread;
import com.example.ruben.privaliachallenge.data.ServiceFactory;
import com.example.ruben.privaliachallenge.data.datasource.TMDbApi;
import com.example.ruben.privaliachallenge.data.datasource.TMDbDataSource;
import com.example.ruben.privaliachallenge.data.repository.TMDbRepository;
import com.example.ruben.privaliachallenge.data.repository.TMDbRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Context applicationContext;

    public ApplicationModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    public Context provideApplicationContext() {
        return applicationContext;
    }

    @Provides
    public MainThread provideMainThread() {
        return new MainThread();
    }

    @Provides
    public TMDbApi provideTMDbApi() {
        return ServiceFactory.createRetrofitService(TMDbApi.class, TMDbConstants.BASE_URL);
    }

    @Provides
    public TMDbRepository provideTMDbRepository(TMDbDataSource tmdbDataSource) {
        return new TMDbRepositoryImpl(tmdbDataSource);
    }
}
