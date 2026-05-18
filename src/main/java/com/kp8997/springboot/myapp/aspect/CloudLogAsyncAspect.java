package com.kp8997.springboot.myapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CloudLogAsyncAspect {
    @Before("com.kp8997.springboot.myapp.aspect.AopExpressions.allMethodsExceptGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n =======>>> Cloud logging Datadog");
    }
}
