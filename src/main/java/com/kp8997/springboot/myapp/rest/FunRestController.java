package com.kp8997.springboot.myapp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @Value("${spring.application.author.name}")
    private String author;

    private Coach coach;

    @Autowired
    public FunRestController(Coach coach) {
        // this automatically initialized the CricketCoach as Coach
        this.coach = coach;
    }

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/user")
    public String getUser() {
        return "User";
    }

    @GetMapping("/rocket")
    public String getRocket() {
        return "Rocket";
    }

    @GetMapping("/author")
    public String getAuthor() {
        return author;
    }
}
