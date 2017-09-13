package com.example.doncho.androidmvpsample.superheroes;

import com.example.doncho.androidmvpsample.config.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by doncho on 9/12/17.
 */

@Module
public abstract class SuperheroesModule {
    @ActivityScoped
    @Binds
    abstract SuperheroesContract.Presenter superheroesPresenter(SuperheroesPresenter presenter);
}
