package com.kp8997.springboot.myapp.core.dao;

import com.kp8997.springboot.myapp.core.entity.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAOImpl implements  EmployeeDAO {
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    @Override
    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    // for both insert and update
    @Override
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Employee.class, id));
    }
}
