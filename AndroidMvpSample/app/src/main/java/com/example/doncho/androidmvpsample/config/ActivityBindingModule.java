package com.example.doncho.androidmvpsample.config;

import com.example.doncho.androidmvpsample.superherodetails.SuperheroDetailsActivity;
import com.example.doncho.androidmvpsample.superherodetails.SuperheroDetailsModule;
import com.example.doncho.androidmvpsample.superheroes.SuperheroesActivity;
import com.example.doncho.androidmvpsample.superheroes.SuperheroesModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by doncho on 9/12/17.
 */

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = SuperheroesModule.class)
    abstract SuperheroesActivity superheroesActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = SuperheroDetailsModule.class)
    abstract SuperheroDetailsActivity superheroDetailsActivity();
}
