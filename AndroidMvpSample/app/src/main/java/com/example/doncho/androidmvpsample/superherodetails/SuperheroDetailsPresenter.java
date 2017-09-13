package com.example.doncho.androidmvpsample.superherodetails;

import com.example.doncho.androidmvpsample.BasePresenter;
import com.example.doncho.androidmvpsample.data.SuperheroesRepository;
import com.example.doncho.androidmvpsample.data.models.Superhero;

import javax.annotation.Nullable;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by doncho on 9/12/17.
 */

public class SuperheroDetailsPresenter implements SuperheroDetailsContract.Presenter {
    private final SuperheroesRepository mRepository;
    private SuperheroDetailsContract.View mView;

    @Nullable
    private String mSuperheroId;

    @Inject
    public SuperheroDetailsPresenter(@Nullable String superheroId, SuperheroesRepository repository) {
        mSuperheroId = superheroId;
        mRepository = repository;
    }

    @Override
    public BasePresenter<SuperheroDetailsContract.View> setView(SuperheroDetailsContract.View view) {
        mView = view;
        return this;
    }

    @Override
    public BasePresenter<SuperheroDetailsContract.View> subscribe() {
        if (mSuperheroId == null) {
            throw new IllegalArgumentException("Invalid ID");
        }

        loadSuperhero(mSuperheroId, true);

        return this;
    }

    @Override
    public BasePresenter<SuperheroDetailsContract.View> unsubscribe() {
        mView = null;
        return this;
    }

    private void loadSuperhero(String superheroId, boolean showLoadingUI) {
        if (showLoadingUI) {
            if (mView != null) {
                mView.showLoadingUi();
            }
        }

        mRepository.getById(superheroId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Superhero>() {
                    @Override
                    public void accept(Superhero superhero) throws Exception {
                        mView.updateSuperhero(superhero);
                        mView.hideLoadingUi();
//                        mView.setLoadingUIState(false);
                    }
                });
    }
}
