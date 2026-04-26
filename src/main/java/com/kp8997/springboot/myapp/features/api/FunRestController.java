package com.kp8997.springboot.myapp.features.api;

import com.kp8997.springboot.myapp.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @Value("${spring.application.author.name}")
    private String author;

    private final Coach coach;
    private final Coach anotherCoach;

    private final Coach coach1;
    private final Coach anotherCoach1;

    private final Coach swimCoach;

    @Autowired
    public FunRestController(
            @Qualifier("badmintonCoach") Coach coach,
            @Qualifier("badmintonCoach") Coach anotherCoach,
            @Qualifier("cricketCoach") Coach coach1,
            @Qualifier("cricketCoach") Coach anotherCoach1,
            @Qualifier("aquatic") Coach swimCoach
            ) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        // this automatically initialized the CricketCoach as Coach
        this.coach = coach;
        this.anotherCoach = anotherCoach;

        this.coach1 = coach1;
        this.anotherCoach1 = anotherCoach1;

        this.swimCoach = swimCoach;
    }

    // should not have the same type of @Autowired
    //@Autowired
    //public void setCoach(BadmintonCoach badmintonCoach) {
    //    this.coach = badmintonCoach;
    //}

    @GetMapping("/check-swim")
    public String getCheckSwim() {
        return this.swimCoach.getDailyWorkout();
    }

    @GetMapping("/check-badminton")
    public String getCheckBadminton() {
        return "Comparing beans Badminton Coach: coach == anotherCoach, " + (coach == anotherCoach);
    }

    @GetMapping("/check-cricket")
    public String getCheckCricket() {
        return "Comparing beans Cricket Coach: coach == anotherCoach, " + (coach1 == anotherCoach1);
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
