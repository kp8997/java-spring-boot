package com.kp8997.springboot.myapp.features.service;

import com.kp8997.springboot.myapp.common.exception.EntityNotFoundException;
import com.kp8997.springboot.myapp.core.dao.EmployeeDAO;
import com.kp8997.springboot.myapp.core.dao.EmployeeRepository;
import com.kp8997.springboot.myapp.core.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //private final EmployeeDAO employeeDAO;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository ) {
        this.employeeRepository = employeeRepository;
    }

    //@Autowired
    //public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
    //    this.employeeDAO = employeeDAO;
    //    this.employeeRepository = employeeRepository;
    //}
    //
    //@Override
    //public List<Employee> findAll() {
    //    return employeeDAO.findAll();
    //}
    //
    //@Override
    //public Employee findById(int employeeId) {
    //    return employeeDAO.findById(employeeId).orElseThrow(
    //            () -> new EntityNotFoundException("Employee id not found - " + employeeId)
    //    );
    //}
    //
    //@Transactional
    //@Override
    //public Employee save(Employee employee) {
    //    return employeeDAO.save(employee);
    //}

    //@Transactional
    //@Override
    //public void deleteById(int id) {
    //    employeeDAO.deleteById(id);
    //}

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(
                () -> new EntityNotFoundException("Employee id not found - " + employeeId)
        );
    }

    // no need transactional annotation because JpaRepository already implemented it
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    // no need transactional annotation because JpaRepository already implemented it
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
