package com.kp8997.springboot.myapp.features.api;

import com.kp8997.springboot.myapp.common.exception.EntityErrorResponse;
import com.kp8997.springboot.myapp.common.exception.EntityNotFoundException;
import com.kp8997.springboot.myapp.core.entity.Employee;
import com.kp8997.springboot.myapp.features.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Employee addEmployee(@RequestBody Employee employee) {
        // save now use for both update and insert
        // set to 0 make sure it is a new object
        employee.setId(null);

        return employeeService.save(employee);
    }

    @PutMapping("")
    public  Employee updateEmployee(@RequestBody Employee employee) {

        if (employee.getId() == null) {
          throw new EntityNotFoundException("Student id is null");
        }
        return employeeService.save(employee);

    }
}
