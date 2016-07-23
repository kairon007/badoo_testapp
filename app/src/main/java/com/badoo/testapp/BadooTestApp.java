package com.badoo.testapp;

import android.app.Application;

import timber.log.Timber;

/**
 * Mostly to start logging and error reporting at application startup
 */
public class BadooTestApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }


}
