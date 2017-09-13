package com.example.doncho.androidmvpsample.utils;

import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by doncho on 9/12/17.
 */

public class DataParser {

    private final Gson mGson;

    @Inject
    public DataParser() {
        mGson = new Gson();
    }

    public <T> T parse(String string, Class<T> klass) {
        return mGson.fromJson(string, klass);
    }
}
