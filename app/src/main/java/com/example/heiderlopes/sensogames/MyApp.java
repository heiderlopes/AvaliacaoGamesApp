package com.example.heiderlopes.sensogames;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

    }
}
