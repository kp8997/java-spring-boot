package com.kp8997.springboot.myapp.core.dao;

import com.kp8997.springboot.myapp.core.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

// change default endpoint from /employees to /members for spring-boot-starter-data-rest
@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
