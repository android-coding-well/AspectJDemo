package com.gosuncn.ajd.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 通常来进行一些切入点条件的过滤，作更加精确的切入控制
 * Created by hwj on 2017/5/17.
 */
@Aspect
public class WithincodeAspect {
    private static final String TAG = "WithincodeAspect";
    public static final String POINT_TEST1="call(* com.gosuncn.ajd.domain.WithincodeInfo.test1(..))";
    public static final String POINT_TEST4="withincode(* com.gosuncn.ajd.domain.WithincodeInfo.test4(..))";

    @Pointcut(POINT_TEST1)
    public void invokeTest1(){};
    @Pointcut(POINT_TEST4)
    public void invokeTest4(){};

    //组合使用，即在test4中调用了test1才会切入
    @Pointcut("invokeTest1() && invokeTest4()")
    public void invokeOnlyTest4(){};


    /**
     * 在test4中调用了test1,test1会执行下面的织入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("invokeOnlyTest4()")
    public Object aroundInvokeOnlyTest4(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i(TAG, "aroundInvokeOnlyTest4: "+joinPoint.toLongString());//call(public java.lang.String com.gosuncn.ajd.domain.WithincodeInfo.test1())
        return "the message has changed";
    }
}
