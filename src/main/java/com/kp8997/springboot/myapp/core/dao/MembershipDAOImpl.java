package com.kp8997.springboot.myapp.core.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public void addAccount() {
        System.out.println("Doing my db work: addAccount in membership");
    }

    @Override
    public void addMember() {
        System.out.println("Doing my db work: addMember in membership");
    }


    @Override
    public boolean addMemberAndReturnBoolean() {
        System.out.println("Doing my db work: addMemberAndReturnBoolean in membership: for return type check");
        return false;
    }
}
