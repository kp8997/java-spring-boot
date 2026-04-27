package com.kp8997.springboot.myapp.features.api;

import com.kp8997.springboot.myapp.common.exception.EntityNotFoundException;
import com.kp8997.springboot.myapp.core.dao.EmployeeDAO;
import com.kp8997.springboot.myapp.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeRestController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("")
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @GetMapping("/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        return employeeDAO.findById(employeeId).orElseThrow(
                () -> new EntityNotFoundException("Employee id not found - " + employeeId)
        );
    }
}
