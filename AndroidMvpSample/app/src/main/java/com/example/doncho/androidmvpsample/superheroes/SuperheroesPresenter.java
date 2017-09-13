package com.example.doncho.androidmvpsample.superheroes;

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

public class SuperheroesPresenter implements SuperheroesContract.Presenter {

    private final SuperheroesRepository mRepository;
    @Nullable
    private SuperheroesContract.View mView;
    private Superhero[] mSuperheroes;

    @Inject
    public SuperheroesPresenter(SuperheroesRepository repository) {
        mRepository = repository;
    }

    private void loadSuperheroes(boolean showLoadingUI) {
        if (showLoadingUI) {
            if (mView != null) {
                mView.showLoadingUi();
//                mView.setLoadingUIState(true);
            }
        }

        if(mSuperheroes != null && mSuperheroes.length > 0) {
            mView.updateSuperheroes(mSuperheroes);
            mView.hideLoadingUi();
            return;
        }

        mRepository.getAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Superhero[]>() {
                    @Override
                    public void accept(Superhero[] superheros) throws Exception {
                        mView.updateSuperheroes(superheros);
                        mSuperheroes = superheros;
                        mView.hideLoadingUi();
//                        mView.setLoadingUIState(false);
                    }
                });
    }

    @Override
    public SuperheroesContract.Presenter setView(SuperheroesContract.View view) {
        mView = view;
        return this;
    }

    @Override
    public SuperheroesContract.Presenter subscribe() {
        loadSuperheroes(true);
        return this;
    }

    @Override
    public SuperheroesContract.Presenter unsubscribe() {
        mView = null;
        return this;
    }

    @Override
    public SuperheroesContract.Presenter openDetails(Superhero superhero) {
        if (mView != null) {
            mView.showSuperhero(superhero);
        }

        return this;
    }
}
