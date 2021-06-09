package com.example.aop;

import android.widget.Toast;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class PointAspect {
    private static final String POINTCUT_METHOD = "execution(@com.example.aop.PointTrace * *(..))";


    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithFromTrace() {
    }

    @Before("methodAnnotatedWithFromTrace()")
    public void beforeJoinPoint(JoinPoint joinPoint) {
        Long id = 0L;
        String name = "";
        Signature signature = joinPoint.getSignature();
        PointTrace trace = ((MethodSignature) signature).getMethod().getAnnotation(PointTrace.class);
        final String en = trace.event();
        //访问目标方法的参数：
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length >= 2 && args[0].getClass() == Long.class && args[1].getClass() == String.class) {
            id = (Long) args[0];
            name = (String) args[1];
        }
        System.out.println("AOP=" + en + " id=" + id + " name=" + name);
    }
}
