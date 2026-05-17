package com.kp8997.springboot.myapp.core.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{
    @Override
    public void addAccount() {
        System.out.println(getClass() + ": Doing my db work: adding an account");
    }
}
