package com.kp8997.springboot.myapp.config;

import com.kp8997.springboot.myapp.common.Coach;
import com.kp8997.springboot.myapp.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
