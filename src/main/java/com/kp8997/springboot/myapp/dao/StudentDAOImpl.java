package com.kp8997.springboot.myapp.dao;

import com.kp8997.springboot.myapp.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("SELECT s FROM Student s ORDER BY s.lastName", Student.class).getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        var query = entityManager.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class);
        return query.setParameter("lastName", lastName).getResultList();

    }
}
