package com.kp8997.springboot.myapp.aspect;

import com.kp8997.springboot.myapp.core.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private void doEveryMethodsOfPackage(JoinPoint joinPoint) {
        System.out.println("\n=======> Executing @Before the advice on any methods in doEveryMethodsOfPackage");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        Object[] objects = joinPoint.getArgs();
        for (Object o : objects) {
            if (o instanceof Account) {
                Account a = (Account) o;
                System.out.println("Account name: " + a.getName());
                System.out.println("Account level: " + a.getLevel());
            } else {
                //System.out.println(o.toString());
            }
        }
    }

    //@Before("execution(public void updateAccount())")
    //public void beforeUpdateAccountAdvice() {
    //    System.out.println("\n=======> Executing @Before the advice on updateAccount");
    //}



    @AfterReturning(pointcut = "execution(* com.kp8997.springboot.myapp.core.dao.AccountDAO.findAccount(..))", returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result) {
        System.out.println("\n=======> Executing @AfterReturning the advice on findAccount method");

        var method = joinPoint.getSignature().toShortString();

        System.out.println("Method: " + method);

        System.out.println("result: " + result);

        // modify return data

        convertAccountNameToUpperCase(result);
    }

    private void convertAccountNameToUpperCase(List<Account> result) {
        for (Account account : result) {
            account.setName(account.getName().toUpperCase());
        }
    }

    @AfterThrowing(
            pointcut = "execution(* com.kp8997.springboot.myapp.core.dao.AccountDAO.findAccount(..))",
            throwing = "exception")
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, RuntimeException exception) {
        System.out.println("\n=======> Executing @AfterThrowing the advice on findAccount method");

        var method = joinPoint.getSignature().toShortString();

        System.out.println("Method: " + method);

        System.out.println("exception: " + exception);
    }

    @After("execution(* com.kp8997.springboot.myapp.core.dao.AccountDAO.findAccount(..))")
    public void afterFindAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n=======> Executing @After (finally) the advice on findAccount method on successful and throwing case");

    }

    @Around("execution(* com.kp8997.springboot.myapp.features.service.TrafficFortuneService.getFortune(..)))")
    public Object aroundGetFortune(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("\n=======> Executing @Around the advice with BEFORE proceed function on getFortune method");

        long start = System.nanoTime();

        Object value = null;

        try {
            joinPoint.proceed();

        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println("Important issue happened: " + e.getMessage());

            // if we want override exception by successful data
            //value = "Major exception, but we handle it in try catch block of @around advice of AOP";

            // if we want to log and keep the exception to main program
            throw e;
        }

        long end = System.nanoTime();

        System.out.println("Time to run this method: " + (end - start));

        System.out.println("\n=======> Executing @Around the advice with AFTER proceed function on getFortune method");

        return value;
    }
}
