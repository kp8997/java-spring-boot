package com.kp8997.springboot.myapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    @Value("${spring.application.author.name}")
    private String author;

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
