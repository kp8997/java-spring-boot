package com.kp8997.springboot.myapp.core.dao;

import com.kp8997.springboot.myapp.core.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {

    void save(Student student);

    Optional<Student> findById(int id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    void delete(int studentId);

    int deleteAll();

    Optional<List<Student>> getAllStudents();
}
