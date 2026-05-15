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
