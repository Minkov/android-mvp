package com.example.doncho.androidmvpsample.data;

import com.example.doncho.androidmvpsample.BaseRepository;
import com.example.doncho.androidmvpsample.data.models.Superhero;
import com.example.doncho.androidmvpsample.utils.HttpRequester;
import com.example.doncho.androidmvpsample.utils.DataParser;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by doncho on 9/12/17.
 */

public class SuperheroesRepository implements BaseRepository<Superhero> {

    private final HttpRequester mHttpRequester;
    private final DataParser mDataParser;
    private final String mBaseUrl;

    @Inject
    public SuperheroesRepository(HttpRequester httpRequester, DataParser dataParser) {
        mHttpRequester = httpRequester;
        mDataParser = dataParser;
        mBaseUrl = "http://192.168.168.44:8080/superheroes/";
    }

    @Override
    public Observable<Superhero[]> getAll() {
        return mHttpRequester.get(mBaseUrl)
                .map(new Function<String, Superhero[]>() {
                    @Override
                    public Superhero[] apply(@NonNull String resultAsString) throws Exception {
                        Superhero[] superheroes = mDataParser.parse(resultAsString, Superhero[].class);
                        return superheroes;
                    }
                });
    }

    @Override
    public Observable<Superhero> getById(String id) {
        return mHttpRequester.get(mBaseUrl + id + "/")
                .map(new Function<String, Superhero>() {
                    @Override
                    public Superhero apply(@NonNull String json) throws Exception {
                        Superhero superhero = mDataParser.parse(json, Superhero.class);
                        return superhero;
                    }
                });
    }

    @Override
    public Observable<Superhero> create(Superhero obj) {
        return null;
    }
}
