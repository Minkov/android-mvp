package com.example.doncho.androidmvpsample;

import io.reactivex.Observable;

/**
 * Created by doncho on 9/12/17.
 */

public interface BaseRepository<T> {
    Observable<T[]> getAll();
    Observable<T> getById(String id);
    Observable<T> create(T obj);
}
