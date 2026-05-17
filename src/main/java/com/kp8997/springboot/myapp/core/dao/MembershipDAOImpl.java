package com.kp8997.springboot.myapp.core.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public void addAccount() {
        System.out.println("Doing my db work: Adding a membership account");
    }
}
