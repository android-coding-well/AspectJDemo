package com.gosuncn.ajd.domain;

import com.gosuncn.ajd.jointpoint.DemoJointPoint;

/**
 * 用于测试注解@DemoJointPoint
 * Created by hwj on 2017/5/17.
 */

public class DemoInfo {

    private String message = "this is a message";

    @DemoJointPoint
    public DemoInfo() {
    }

    @DemoJointPoint
    public String getMessageWithAspect() {
        return message;
    }

    public String getMessage() {
        return message;
    }

}
