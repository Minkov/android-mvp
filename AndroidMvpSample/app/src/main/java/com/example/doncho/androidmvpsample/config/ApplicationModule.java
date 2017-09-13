package com.example.doncho.androidmvpsample.config;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by doncho on 9/12/17.
 */

@Module
public abstract class ApplicationModule {
    @Binds
    abstract Context bindContext(Application application);
}
