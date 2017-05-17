package com.gosuncn.ajd.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面信息详细介绍
 * Created by hwj on 2017/5/17.
 */
@Aspect
public class TestAspect {

    private String TAG = "TestAspect";

    //1 jointpoint（execution，call，withincode（在WithincodeAspect介绍））
    // 第一个『*』表示返回值，『*』表示返回值为任意类型
    // ():表示方法没有任何参数
    // (..):表示匹配接受任意个参数的方法
    // (..,java.lang.String):表示匹配接受java.lang.String类型的参数结束，且其前边可以接受有任意个参数的方法
    // (java.lang.String,..):表示匹配接受java.lang.String类型的参数开始，且其后边可以接受任意个参数的方法
    // (*,java.lang.String):表示匹配接受java.lang.String类型的参数结束，且其前边接受有一个任意类型参数的方法
    private static final String POINT_EXECMETHOD = "execution(* com.gosuncn.ajd.domain.TestInfo.*(..))";//方法执行的时候
    private static final String POINT_CALLMETHOD = "call(* com.gosuncn.ajd.domain.TestInfo.*(..))";//方法调用的时候

    //2 pointcut
    @Pointcut(POINT_EXECMETHOD)
    public void methodExec() {
    }

    @Pointcut(POINT_CALLMETHOD)
    public void methodCall() {
    }


    //3 advice（Before  Around  After  AfterThrowing）
    /**
     * 方法调用之前执行
     *
     * @param joinPoint
     */
    @Before("methodCall()")
    public void beforeCall(JoinPoint joinPoint) {
        Log.i(TAG, "Before call>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Log.i(TAG, "target=" + joinPoint.getTarget().toString());//方法所在类，如com.gosuncn.ajd.MainActivity@6427438
        Log.i(TAG, "kind=" + joinPoint.getKind());//类型，如kind=method-call
        Log.i(TAG, "Args=" + joinPoint.getArgs().length);//方法参数个数
        Log.i(TAG, "Signature=" + joinPoint.getSignature().getName());//方法信息，如Signature=test
        Log.i(TAG, "SourceLocation=" + joinPoint.getSourceLocation().getFileName());//方法所在类名称，如MainActivity.java
        Log.i(TAG, "LongString=" + joinPoint.toLongString());//完整的方法签名，如call(public void com.gosuncn.ajd.MainActivity.setContentView(int))
        Log.i(TAG, "Before call<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

    }

    /**
     * 方法体开始执行时（刚进入该方法实体时）
     * 替换返回值
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("methodExec()")
    public Object aroundWeaverPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i(TAG, "Around call:" + joinPoint.toLongString());
        // TODO: 2017/5/17 do something whatever you want before method
        joinPoint.proceed();//调用原方法的执行
        // TODO: 2017/5/17 do something whatever you want after method
        String result = "aspect advice change the return result of your method.";
        return result;//替换原方法的返回值
    }

    /**
     * 方法体开始执行时（刚进入该方法实体时）
     * 不替换返回值
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    /*@Around("methodExec()")
    public void aroundWeaverPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.i(TAG, "Around call:" + joinPoint.toLongString());
        joinPoint.proceed();
    }*/

    /**
     * 方法调用之后执行（无论正常返回还是出现异常都会执行）
     *
     * @param joinPoint
     */
    @After("methodCall()")
    public void afterCall(JoinPoint joinPoint) {
        Log.i(TAG, "After call:" + joinPoint.toLongString());

    }


}
