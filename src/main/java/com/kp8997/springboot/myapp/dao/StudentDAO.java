package com.kp8997.springboot.myapp.dao;

import com.kp8997.springboot.myapp.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);

    Student findById(int id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    void delete(int studentId);

    int deleteAll();
}
