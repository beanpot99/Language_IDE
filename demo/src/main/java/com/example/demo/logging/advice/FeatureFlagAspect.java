package com.example.demo.logging.advice;

import com.example.demo.logging.annotations.FeatureFlag;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class FeatureFlagAspect {

    @Around("execution(* com.example..*.*(..)) && @annotation(featureFlag)")
    public Object around(ProceedingJoinPoint joinPoint, FeatureFlag featureFlag) throws Throwable {
        System.out.println("HEYYYYY");
        System.out.println(featureFlag.flagName() + " FLAG NAME!!!!");
        boolean featureFlagEnabled = true;

            if (featureFlagEnabled != featureFlag.execute()) {
                System.out.println("FEATURE FLAG IS DISABLED");
                return null;
            } else {
                System.out.println("FEATURE FLAG IS ENABLED");
                return joinPoint.proceed();
            }
    }
}
