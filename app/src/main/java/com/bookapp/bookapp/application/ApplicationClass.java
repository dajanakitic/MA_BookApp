package com.bookapp.bookapp.application;

import android.app.Application;

import com.bookapp.bookapp.local.localDB.RealmDB;

public class ApplicationClass extends Application {

    private static ApplicationClass instance;

    public static ApplicationClass getInstance(){
        if (instance == null) {
            instance = new ApplicationClass();
        }
        return instance;
    }

    private ApplicationClass() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RealmDB.initRealm(this);
    }
}
