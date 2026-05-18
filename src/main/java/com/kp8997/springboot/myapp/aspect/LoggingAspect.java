package com.kp8997.springboot.myapp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class LoggingAspect {

    //@Before("execution(public void addAccount())")
    //public void beforeAddAccountAdvice() {
    //    System.out.println("\n=======> Executing @Before the advice on addAccount");
    //}

    @Before("execution(public void addAccount())")
    //@Before("execution(public void com.kp8997.springboot.myapp.core.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=======> Executing @Before the advice on addAccount");
    }

    @Before("execution(public void add*())")
    public void beforeAnyAddAdvice() {
        System.out.println("\n=======> Executing @Before the advice on any add*");
    }

    @Before("execution(* add*())")
    public void beforeReturnAnyAddAdvice() {
        System.out.println("\n=======> Executing @Before the advice on any add* with any return type");
    }

    //@Before("execution(* add*(com.kp8997.springboot.myapp.core.entity.Account))")
    //public void beforeReturnAnyAddAdviceWithParam() {
    //    System.out.println("\n=======> Executing @Before the advice on any add* with any return type with Account param");
    //}

    // .. can be 0 or any params in this case, it will match addAccount(Account) too
    //@Before("execution(* add*(com.kp8997.springboot.myapp.core.entity.Account,..))")
    //public void beforeReturnAnyAddAdviceWithParams() {
    //    System.out.println("\n=======> Executing @Before the advice on any add* with any return type with Account param with FLAG");
    //}

    // this will cause error on intellij ultimate version, because of conflict bean of jmx
    // fix from  @Before("execution(* add*(..))")
    // to  @Before("execution(* add*(..))")
    // try to narrow the broad scope for only this package project


    @Before("com.kp8997.springboot.myapp.aspect.AopExpressions.addMethodsPointcut()")
    public void beforeReturnAnyAddAdviceWithParams() {
        System.out.println("\n=======> Executing @Before the advice on any add* with any return type with Account param with FLAG");
    }

    //@AfterReturning("com.kp8997.springboot.myapp.aspect.AopExpressions.addMethodsPointcut()")
    //public void afterAddAccountAdvice() {
    //    System.out.println("\n=======> Executing @AfterReturn the advice on any add* with any return type with Account param with FLAG");
    //}

    @Before("com.kp8997.springboot.myapp.aspect.AopExpressions.allMethodsExceptGetterSetter()")
    private void doEveryMethodsOfPackage() {
        System.out.println("\n=======> Executing @Before the advice on any methods in doEveryMethodsOfPackage");
    }

    //@Before("execution(public void updateAccount())")
    //public void beforeUpdateAccountAdvice() {
    //    System.out.println("\n=======> Executing @Before the advice on updateAccount");
    //}
}
