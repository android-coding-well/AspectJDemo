package com.gosuncn.ajd.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.ConstructorSignature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by hwj on 2017/5/17.
 */
@Aspect
public class DemoAspect {
    private static final String TAG = "DemoAspect";
    //1 jointpoint
    public  static final String POINT_METHOD ="execution(@com.gosuncn.ajd.jointpoint.DemoJointPoint * *(..))";//自定义注解在方法上
    public static final String POINT_CONSTRUCTOR ="execution(@com.gosuncn.ajd.jointpoint.DemoJointPoint *.new(..))";//自定义注解在构造函数上


    //2 pointcut
    @Pointcut(POINT_METHOD)
    public void method(){}
    @Pointcut(POINT_CONSTRUCTOR)
    public void constructor(){}


    //3 advice
    /**
     * 在@DemoJointPoint注解的普通方法上织入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("method()")
    public Object weaveMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Log.i(TAG,"weaveMethod:className="+className+",methodName="+methodName);
        // TODO: 2017/5/17 do something whatever you want before method
        joinPoint.proceed();//执行原来方法里的内容
        // TODO: 2017/5/17 do something whatever you want after method
        return "aspect advice change the return result of your method.";//改变原先方法的返回值
    }

    /**
     * 在@DemoJointPoint注解的构造方法上织入
     * @param joinPoint
     * @throws Throwable
     */
    @Around("constructor()")
    public void weaveConstructor(ProceedingJoinPoint joinPoint) throws Throwable {
        ConstructorSignature methodSignature = (ConstructorSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Log.i(TAG,"weaveConstructor: className="+className+",methodName="+methodName);
        // TODO: 2017/5/17 do something whatever you want before method
        joinPoint.proceed();//执行原来方法里的内容
        // TODO: 2017/5/17 do something whatever you want after method
    }

}
