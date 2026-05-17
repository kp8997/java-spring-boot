Aspect: Code to run aspect
Advice: Type of action for when to run
Join point: Where the aspect can be executed (methods, fields, or constructor of classes)
Pointcut (conditional to match the join point to run - list of where to execute - predicate join point)
Target Object: the object is being advised by 1 or many aspect
Weaving: process moment to make a target object into advised object (advise object is the target object wrapped with advice) 
    to execute the whole code for our program
    : compile time, load time, run-time

Type of Advice
    Before
    After return
    After throwing
    After finally
    Around

AspectJ and Spring boot aop
Spring Boot AOP is small scope aspectJ. so it is more restrictive

take this as examples for aop

public class UserAccount {
private String status;

    // Constructor Join Point
    public UserAccount() {
        this.status = "NEW";
    }
}

@Aspect
public class AuditAspect {
// 1. Pointcut targeting a Constructor Join Point
@Before("execution(com.khang.UserAccount.new())")
public void logNewUser() {
System.out.println("ADVICE: A new UserAccount object is being initialized.");
}

// 2. Pointcut targeting a Field Join Point (Set/Update)
@Before("set(String com.khang.UserAccount.status)")
public void logStatusChange() {
System.out.println("ADVICE: The 'status' field is about to be modified.");
}
}

