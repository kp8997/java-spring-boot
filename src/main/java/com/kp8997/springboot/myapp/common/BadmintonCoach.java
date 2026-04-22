package com.kp8997.springboot.myapp.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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

    // some life cycle of the beans hook below

    @PreDestroy
    public void preDestroy() {
        System.out.println("In preDestroy: " + getClass().getSimpleName());
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("In postConstruct: " + getClass().getSimpleName());
    }
}
