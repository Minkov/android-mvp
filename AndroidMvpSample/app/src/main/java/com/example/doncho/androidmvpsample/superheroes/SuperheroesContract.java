package com.example.doncho.androidmvpsample.superheroes;

import com.example.doncho.androidmvpsample.BasePresenter;
import com.example.doncho.androidmvpsample.BaseView;
import com.example.doncho.androidmvpsample.data.models.Superhero;

/**
 * Created by doncho on 9/12/17.
 */

public interface SuperheroesContract {

    interface View extends BaseView<Presenter> {

//        void setLoadingUIState(boolean state);
        void showLoadingUi();
        void hideLoadingUi();

        void updateSuperheroes(Superhero[] superheros);

        void showSuperhero(Superhero superhero);
    }

    interface Presenter extends BasePresenter<View> {

        Presenter openDetails(Superhero superhero);
    }
}
