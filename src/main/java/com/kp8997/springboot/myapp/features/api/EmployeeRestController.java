package com.kp8997.springboot.myapp.features.api;

import com.kp8997.springboot.myapp.common.exception.EntityNotFoundException;
import com.kp8997.springboot.myapp.core.entity.Employee;
import com.kp8997.springboot.myapp.features.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private final EmployeeService employeeService;
    private final JsonMapper jsonMapper;

    public EmployeeRestController(EmployeeService employeeService, JsonMapper jsonMapper) {
        this.employeeService = employeeService;
        this.jsonMapper = jsonMapper;
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
    public Employee updateEmployee(@RequestBody Employee employee) {
        if (employee.getId() == null) {
          throw new EntityNotFoundException("Student id is null");
        }
        return employeeService.save(employee);
    }

    @PatchMapping("/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String, Object> payload) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new EntityNotFoundException("Employee id not found - " + employeeId);
        }
        if (payload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body - " + employeeId);
        }

        Employee patchedEmployee = jsonMapper.updateValue(employee, payload);
        return employeeService.save(patchedEmployee);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if (employee == null) {
            throw new EntityNotFoundException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
