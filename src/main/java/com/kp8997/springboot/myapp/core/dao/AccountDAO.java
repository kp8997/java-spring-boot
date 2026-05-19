package com.kp8997.springboot.myapp.core.dao;


import com.kp8997.springboot.myapp.core.entity.Account;

import java.util.List;

public interface AccountDAO {
    void addAccount();

    void addAccount(Account account);

    void addAccount(Account account, boolean flag);

    String getName();

    void setName(String name);

    String getServiceCode();

    void setServiceCode(String serviceCode);

    void doWork();

    List<Account> findAccount();

    List<Account>  findAccount(boolean flag);
}
