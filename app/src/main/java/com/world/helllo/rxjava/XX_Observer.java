package com.world.helllo.rxjava;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Charles on 2018/4/26.
 */

public class XX_Observer implements Observer<String> {
    private static final String TAG = XX_Observer.class.getName();

    @Override
    public void onSubscribe(Disposable d) {
        Log.i(TAG, "onSubscribe: " + d.toString());
    }

    @Override
    public void onNext(String s) {
        Log.i(TAG, "onNext: " + s);
    }

    @Override
    public void onError(Throwable e) {
        Log.i(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.i(TAG, "onComplete");
    }
}
