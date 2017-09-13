package com.example.doncho.androidmvpsample.superherodetails;

import android.os.Bundle;

import com.example.doncho.androidmvpsample.R;
import com.example.doncho.androidmvpsample.utils.ActivityUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SuperheroDetailsActivity extends DaggerAppCompatActivity {
    public static final String EXTRA_SUPERHERO_ID = "EXTRA_SUPERHERO_ID";

    @Inject
    SuperheroDetailsFragment mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_details);

        SuperheroDetailsFragment view = (SuperheroDetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frame_content);

        if (view == null) {
            view = mView;

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), view, R.id.frame_content);
        }
    }
}
