package com.kp8997.springboot.myapp.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
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

    @Pointcut("execution(* com.kp8997.springboot.myapp.core.dao.AccountDAO.add*(..))")
    private void addMethodsPointcut() {
    }


    @Before("addMethodsPointcut()")
    public void beforeReturnAnyAddAdviceWithParams() {
        System.out.println("\n=======> Executing @Before the advice on any add* with any return type with Account param with FLAG");
    }

    @AfterReturning("addMethodsPointcut()")
    public void afterAddAccountAdvice() {
        System.out.println("\n=======> Executing @AfterReturn the advice on any add* with any return type with Account param with FLAG");
    }


    @Pointcut("execution(* com.kp8997.springboot.myapp.core.dao.*.get*(..))")
    private void getMethodsPointcut() {}

    @Pointcut("execution(* com.kp8997.springboot.myapp.core.dao.*.set*(..))")
    private void setMethodsPointcut() {}

    @Pointcut("execution(* com.kp8997.springboot.myapp.core.dao.*.*(..))")
    private void everyMethodsOfPackagePointcut() {
    }

    @Pointcut("everyMethodsOfPackagePointcut() && !(getMethodsPointcut() || setMethodsPointcut())")
    private void allMethodsExceptGetterSetter() {}

    @Before("allMethodsExceptGetterSetter()")
    private void doEveryMethodsOfPackage() {
        System.out.println("\n=======> Executing @Before the advice on any methods in doEveryMethodsOfPackage");
    }

    //@Before("execution(public void updateAccount())")
    //public void beforeUpdateAccountAdvice() {
    //    System.out.println("\n=======> Executing @Before the advice on updateAccount");
    //}
}
