package com.kp8997.springboot.myapp.core.dao;


import com.kp8997.springboot.myapp.core.entity.Account;

public interface AccountDAO {
    void addAccount();

    void addAccount(Account account);

    void addAccount(Account account, boolean flag);
}
