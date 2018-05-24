package com.example.ruben.privaliachallenge.core.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class RootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeInjector();

        int layoutResourceId = getLayoutResourceId();
        if (layoutResourceId > 0) {
            setContentView(layoutResourceId);
        }

        ButterKnife.bind(this);
        initializePresenter();
    }

    @LayoutRes
    protected abstract int getLayoutResourceId();

    protected abstract void initializeInjector();

    protected abstract void initializePresenter();
}
