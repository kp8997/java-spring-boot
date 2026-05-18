package com.kp8997.springboot.myapp.core.dao;

import com.kp8997.springboot.myapp.core.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{

    private String name;

    private String serviceCode;

    public String getName() {
        System.out.println(getClass() + " in getName");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + " in setName");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + " in getServiceCode");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + " in setServiceCode");
        this.serviceCode = serviceCode;
    }

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

    @Override
    public void doWork() {
        System.out.println(getClass() + ": Do work in normal function");
    }
}
