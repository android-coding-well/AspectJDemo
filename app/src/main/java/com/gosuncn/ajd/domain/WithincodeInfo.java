package com.gosuncn.ajd.domain;

import android.util.Log;

/**
 * Created by hwj on 2017/5/17.
 */

public class WithincodeInfo {
    private static final String TAG = "WithincodeInfo";

    public String test1(){
        return "hello";
    }

    public String test3(){
        return test1();
    }

    public String test4(){
        String s=test1();
        Log.i(TAG, "test1(): "+s);
        return s;
    }
}
