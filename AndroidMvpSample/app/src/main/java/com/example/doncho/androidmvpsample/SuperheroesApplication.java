package com.example.doncho.androidmvpsample;

import com.example.doncho.androidmvpsample.config.AppComponent;
import com.example.doncho.androidmvpsample.config.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by doncho on 9/12/17.
 */

public class SuperheroesApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        return appComponent;
    }
}
