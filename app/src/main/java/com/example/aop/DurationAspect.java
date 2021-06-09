package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.HashMap;

/**
 * @author wangjian
 * 
 * @since 2016-06-30
 * */
@Aspect
public class DurationAspect {
	private static final String POINTCUT_METHOD = "execution(@com.example.aop.DurationTrace * *(..))";

	private static final String POINTCUT_CONSTRUCTOR = "execution(@com.example.aop.DurationTrace *.new(..))";
	
	private HashMap<String, Long> mDurations = new HashMap<>();
	
	@Pointcut(POINTCUT_METHOD)
	public void methodAnnotatedWithDurationTrace() 
	{}

	@Pointcut(POINTCUT_CONSTRUCTOR)
	public void constructorAnnotatedDurationTrace() 
	{}
	
	@After("methodAnnotatedWithDurationTrace() || constructorAnnotatedDurationTrace()")
	public void afterJoinPoint(JoinPoint joinPoint)
	{
	    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DurationTrace trace = methodSignature.getMethod().getAnnotation(DurationTrace.class);
	    final String en = trace.event();
	    if(mDurations.containsKey(en))
	    {
			System.out.println("AOP=" + en + " Duration=" + (System.currentTimeMillis() - mDurations.get(en)));
	    	mDurations.remove(en);
	    } else
	    {
	    	mDurations.put(en, System.currentTimeMillis());
	    }
	}
}
