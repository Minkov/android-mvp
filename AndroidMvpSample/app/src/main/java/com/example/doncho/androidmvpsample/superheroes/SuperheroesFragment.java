package com.example.doncho.androidmvpsample.superheroes;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doncho.androidmvpsample.BaseFragment;
import com.example.doncho.androidmvpsample.R;
import com.example.doncho.androidmvpsample.data.models.Superhero;
import com.example.doncho.androidmvpsample.superherodetails.SuperheroDetailsActivity;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

public class SuperheroesFragment extends BaseFragment implements SuperheroesContract.View {
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    @Inject
    SuperheroesContract.Presenter mPresenter;

    private ListView mLvSuperheroes;
    private SuperheroesAdapter mSuperheroesAdapter;

    private SuperheroesAdapter.OnSuperheroesItemClickListener mItemListener = new SuperheroesAdapter.OnSuperheroesItemClickListener() {
        @Override
        public void onClick(Superhero superhero) {
            mPresenter.openDetails(superhero);
        }
    };

    @Inject
    public SuperheroesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_superheroes, container, false);

        mLvSuperheroes = root.findViewById(R.id.lv_superheroes);
        mSuperheroesAdapter = new SuperheroesAdapter(getContext(), mItemListener);
        mLvSuperheroes.setAdapter(mSuperheroesAdapter);

        setLoadingContainerId(R.id.container_loading);
        setContentContainerId(R.id.container_content);

        return root;
    }

    void getLocation() {
        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);


        SettingsClient client = LocationServices.getSettingsClient(getActivity());
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

            }
        });

        task.addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                int statusCode = ((ApiException) e).getStatusCode();
                switch (statusCode) {
                    case CommonStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(getActivity(),
                                    REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException sendEx) {

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
        FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    Toast.makeText(getActivity(), "" + location.getLatitude(), Toast.LENGTH_SHORT)
                            .show();
                }
            }
        };

        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);

    }

    void printLocation(Location location) {
        Toast.makeText(getActivity(), location.getLatitude() + "", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setView(this);
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void updateSuperheroes(Superhero[] superheros) {
        mSuperheroesAdapter.clear();
        mSuperheroesAdapter.addAll(superheros);
        getLocation();
    }

    @Override
    public void showSuperhero(Superhero superhero) {
        Intent intent = new Intent(getContext(), SuperheroDetailsActivity.class);
        intent.putExtra(SuperheroDetailsActivity.EXTRA_SUPERHERO_ID, superhero.getId());
        startActivity(intent);
    }
}
