package com.kp8997.springboot.myapp.core.dao;

import com.kp8997.springboot.myapp.core.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {
    List<Employee> findAll();

    Optional<Employee> findById(int id);

    void save(Employee employee);

    void deleteById(int id);

    void update(Employee employee);
}
