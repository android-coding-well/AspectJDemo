package com.gosuncn.ajd;

import android.app.Application;
import android.content.Context;

/**
 * Created by hwj on 2017/5/17.
 */

public class MyApplication extends Application {

    public static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance() {
        return instance;
    }

}
