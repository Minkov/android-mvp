package com.example.doncho.androidmvpsample.superherodetails;

import com.example.doncho.androidmvpsample.config.ActivityScoped;
import com.example.doncho.androidmvpsample.config.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

import static com.example.doncho.androidmvpsample.superherodetails.SuperheroDetailsActivity.EXTRA_SUPERHERO_ID;

/**
 * Created by doncho on 9/12/17.
 */

@Module
public abstract class SuperheroDetailsModule {
    @ActivityScoped
    @Binds
    abstract SuperheroDetailsContract.Presenter superheroDetailsPresenter(SuperheroDetailsPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract SuperheroDetailsFragment superheroDetailsFragment();

    @Provides
    @ActivityScoped
    static String provideSuperheroId(SuperheroDetailsActivity activity) {
        return activity.getIntent().getStringExtra(EXTRA_SUPERHERO_ID);
    }
}
