package com.kp8997.springboot.myapp.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class BadmintonCoach implements Coach{

    public BadmintonCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }


    @Override
    public String getDailyWorkout() {
        return "Badminton coach tells you to practice in footwork and swing";
    }
}
