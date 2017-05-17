package com.gosuncn.ajd.domain;

/**
 * Created by hwj on 2017/5/17.
 */

public class TestInfo {


    public String test1() {
        return "hello";
    }

    public void test2(String a, int b, Object c) {
    }

    public String test3() {
        return test1();
    }

    public String test4() {
        return test1();
    }
}
