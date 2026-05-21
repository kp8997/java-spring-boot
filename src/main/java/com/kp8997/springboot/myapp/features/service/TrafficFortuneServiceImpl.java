package com.kp8997.springboot.myapp.features.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {
    @Override
    public String getFortune() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    @Override
    public String getFortune(boolean flag) {
        if (flag) {
            throw new RuntimeException("Caught exception for getFortune");
        }

        return "Heavy traffic after fortune";
    }
}
