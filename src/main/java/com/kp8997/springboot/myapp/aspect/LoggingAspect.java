package com.kp8997.springboot.myapp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    //@Before("execution(public void addAccount())")
    //public void beforeAddAccountAdvice() {
    //    System.out.println("\n=======> Executing @Before the advice on addAccount");
    //}

    //@Before("execution(public void addAccount())")
    @Before("execution(public void com.kp8997.springboot.myapp.core.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=======> Executing @Before the advice on addAccount");
    }

    @AfterReturning("execution(public void addAccount())")
    public void afterAddAccountAdvice() {
        System.out.println("\n=======> Executing @AfterReturn the advice on addAccount");
    }

    @Before("execution(public void updateAccount())")
    public void beforeUpdateAccountAdvice() {
        System.out.println("\n=======> Executing @Before the advice on updateAccount");
    }
}
