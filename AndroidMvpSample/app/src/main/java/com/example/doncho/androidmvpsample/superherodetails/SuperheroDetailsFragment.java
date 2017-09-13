package com.example.doncho.androidmvpsample.superherodetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doncho.androidmvpsample.BaseFragment;
import com.example.doncho.androidmvpsample.R;
import com.example.doncho.androidmvpsample.data.models.Superhero;

import javax.inject.Inject;

public class SuperheroDetailsFragment extends BaseFragment implements SuperheroDetailsContract.View {
    @Inject
    SuperheroDetailsContract.Presenter mPresenter;

    private TextView mNameView;
    private TextView mSecretIdentityView;

    @Inject
    public SuperheroDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_superhero_details, container, false);

        mNameView = root.findViewById(R.id.tv_name);
        mSecretIdentityView = root.findViewById(R.id.tv_secretIdentity);

        setLoadingContainerId(R.id.container_loading);
        setContentContainerId(R.id.container_content);

        return root;
    }

    @Override
    public void onResume() {
        mPresenter.setView(this)
                .subscribe();

        super.onResume();
    }

    @Override
    public void onDestroy() {
        mPresenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    public void updateSuperhero(Superhero superhero) {
        mNameView.setText(superhero.getName());
        mSecretIdentityView.setText(superhero.getSecretIdentity());
    }
}
