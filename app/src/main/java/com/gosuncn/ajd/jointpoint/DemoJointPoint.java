package com.gosuncn.ajd.jointpoint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 连接点注解，用于构造方法和普通方法上
 * Created by hwj on 2017/5/17.
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR,ElementType.METHOD})
public @interface DemoJointPoint {
}
