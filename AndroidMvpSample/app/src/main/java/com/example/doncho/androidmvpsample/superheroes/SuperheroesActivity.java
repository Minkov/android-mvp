package com.example.doncho.androidmvpsample.superheroes;

import android.os.Bundle;

import com.example.doncho.androidmvpsample.R;
import com.example.doncho.androidmvpsample.utils.ActivityUtils;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class SuperheroesActivity extends DaggerAppCompatActivity {

    @Inject
    SuperheroesPresenter mPresenter;

    @Inject
    Lazy<SuperheroesFragment> mViewProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superheroes);

        SuperheroesFragment fragment =
                (SuperheroesFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.frame_content);

        if (fragment == null) {
            fragment = mViewProvider.get();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.frame_content);
        }
    }
}
