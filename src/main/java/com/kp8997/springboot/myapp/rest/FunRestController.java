package com.kp8997.springboot.myapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
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
}
