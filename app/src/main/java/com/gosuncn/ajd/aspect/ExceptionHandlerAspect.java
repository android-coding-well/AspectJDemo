package com.gosuncn.ajd.aspect;

import android.util.Log;
import android.widget.Toast;

import com.gosuncn.ajd.MyApplication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 异常处理
 * 方式有两种：第一种是发送异常捕获到并提示用户即可，程序不闪退
 * 第二种是直接闪退，在闪退前可以做日志记录等的相关操作
 * Created by hwj on 2017/5/17.
 */

@Aspect
public class ExceptionHandlerAspect {
    private static final String TAG = "ExceptionHandlerAspect";
    private static final String POINTCUT_EXCEPTION ="execution(* *(..))";//拦截所有异常

    @Pointcut(POINTCUT_EXCEPTION)
    public void handleException() {}

    /**
     * 产生异常不退出
     * @param joinPoint
     * @throws Throwable
     */
    @Around("handleException()")
    public void handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            Log.e(TAG,"产生异常了...");
            Toast.makeText(MyApplication.getInstance(), "很抱歉，应用遇到未知错误无法正常使用，我们正在鞭打工程师，也在紧急修复，请谅解。", Toast.LENGTH_SHORT).show();
            //ToDo: do something to handle exception

            //throw throwable;
        }
    }

    /**
     * 产生异常会闪退
     * @param exception
     */
    @AfterThrowing(pointcut = POINTCUT_EXCEPTION, throwing = "exception")
    public void catchExceptionMethod(Exception exception) {
        Log.e(TAG, "catchException: " + exception.getMessage());
        // TODO: 2017/5/17 do something,such as logging

    }
}
