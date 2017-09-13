package com.example.doncho.androidmvpsample.utils;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by doncho on 9/12/17.
 */

public class HttpRequester {

    private final OkHttpClient mOkHttpClient;

    @Inject
    public HttpRequester(){
        mOkHttpClient = new OkHttpClient();
    }
    public Observable<String> get(final String url) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Request req = new Request.Builder()
                        .url(url)
                        .build();

                Response res = mOkHttpClient.newCall(req).execute();

                String result = res.body().string();
                e.onNext(result);
                e.onComplete();
            }
        });
    }
}
