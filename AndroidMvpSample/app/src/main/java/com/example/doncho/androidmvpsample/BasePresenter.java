package com.example.doncho.androidmvpsample;

/**
 * Created by doncho on 9/12/17.
 */

public interface BasePresenter<T> {
    BasePresenter<T> setView(T view);
    BasePresenter<T> subscribe();
    BasePresenter<T> unsubscribe();
}
