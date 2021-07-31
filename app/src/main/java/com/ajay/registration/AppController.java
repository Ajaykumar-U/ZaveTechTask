package com.ajay.registration;

import android.app.Application;
import android.content.Context;

public class AppController extends Application {

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
