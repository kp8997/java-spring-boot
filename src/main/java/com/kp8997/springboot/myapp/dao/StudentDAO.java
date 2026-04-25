package com.kp8997.springboot.myapp.dao;

import com.kp8997.springboot.myapp.entity.Student;

public interface StudentDAO {

    void save(Student student);

    Student findById(int id);
}
