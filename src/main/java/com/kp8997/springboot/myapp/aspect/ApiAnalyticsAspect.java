package com.kp8997.springboot.myapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiAnalyticsAspect {
    // Replace 'AopExpressions' with the actual class name where your pointcut lives
    @Before("com.kp8997.springboot.myapp.aspect.AopExpressions.allMethodsExceptGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n =======>>> Performing API analytics");
    }
}
