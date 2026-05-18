package com.kp8997.springboot.myapp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


// optional @Aspect if we use only for pointcut
@Aspect
@Component
public class AopExpressions {

    @Pointcut("execution(* com.kp8997.springboot.myapp.core.dao.AccountDAO.add*(..))")
    public void addMethodsPointcut() {
    }
    @Pointcut("execution(* com.kp8997.springboot.myapp.core.dao.*.get*(..))")
    public void getMethodsPointcut() {}

    @Pointcut("execution(* com.kp8997.springboot.myapp.core.dao.*.set*(..))")
    public void setMethodsPointcut() {}

    @Pointcut("execution(* com.kp8997.springboot.myapp.core.dao.*.*(..))")
    public void everyMethodsOfPackagePointcut() {
    }

    @Pointcut("everyMethodsOfPackagePointcut() && !(getMethodsPointcut() || setMethodsPointcut())")
    public void allMethodsExceptGetterSetter() {}
}
