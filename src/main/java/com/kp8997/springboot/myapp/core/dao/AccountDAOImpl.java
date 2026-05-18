package com.kp8997.springboot.myapp.core.dao;

import com.kp8997.springboot.myapp.core.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{
    @Override
    public void addAccount() {
        System.out.println(getClass() + "Doing my db work: adding an account");
    }

    @Override
    public void addAccount(Account account) {
        System.out.println(getClass() + "Doing my db work: adding an account with PARAM");
    }

    @Override
    public void addAccount(Account account, boolean flag) {
        System.out.println(getClass() + "Doing my db work: adding an account with PARAMS FLAG");
    }
}
