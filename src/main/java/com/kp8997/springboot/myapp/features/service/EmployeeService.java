package com.kp8997.springboot.myapp.features.service;

import com.kp8997.springboot.myapp.core.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(int employeeId);

    Employee save(Employee employee);

    void deleteById(int id);
}
