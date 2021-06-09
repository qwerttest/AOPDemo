package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.HashMap;

/**
 * @author wangjian
 * @since 2016-06-30
 */
@Aspect
public class MonitorAspect {
    private static final String POINTCUT_METHOD = "execution(@com.example.aop.MonitorTrace * *(..))";

    private static final String POINTCUT_CONSTRUCTOR = "execution(@com.example.aop.MonitorTrace *.new(..))";

    private HashMap<String, Long> mDurations = new HashMap<>();

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithMonitorAspect() {
    }

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedMonitorAspect() {
    }

    @After("methodAnnotatedWithMonitorAspect() || constructorAnnotatedMonitorAspect()")
    public void afterJoinPoint(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DurationTrace trace = methodSignature.getMethod().getAnnotation(DurationTrace.class);
        final String en = trace.event();
        if (mDurations.containsKey(en)) {
            System.out.println("AOP=" + en + " Monitor=" + (System.currentTimeMillis() - mDurations.get(en)));
            mDurations.remove(en);
        }
    }

    @Before("methodAnnotatedWithMonitorAspect()")
    public void beforeJoinPoint(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DurationTrace trace = methodSignature.getMethod().getAnnotation(DurationTrace.class);
        final String en = trace.event();
        mDurations.put(en, System.currentTimeMillis());
    }
}
