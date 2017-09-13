package com.example.doncho.androidmvpsample.superherodetails;

import com.example.doncho.androidmvpsample.BasePresenter;
import com.example.doncho.androidmvpsample.BaseView;
import com.example.doncho.androidmvpsample.data.models.Superhero;

/**
 * Created by doncho on 9/12/17.
 */

public class SuperheroDetailsContract {
    interface View extends BaseView<Presenter> {

        void showLoadingUi();
        void hideLoadingUi();
        void updateSuperhero(Superhero superhero);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
