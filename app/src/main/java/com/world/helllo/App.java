package com.world.helllo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Charles on 2018/5/1.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
