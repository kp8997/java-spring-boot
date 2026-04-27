package com.kp8997.springboot.myapp.features.api;

import com.kp8997.springboot.myapp.common.exception.EntityNotFoundException;
import com.kp8997.springboot.myapp.core.dao.EmployeeDAO;
import com.kp8997.springboot.myapp.core.entity.Employee;
import com.kp8997.springboot.myapp.features.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        return employeeService.findById(employeeId);
    }

    @PostMapping("")
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }
}
