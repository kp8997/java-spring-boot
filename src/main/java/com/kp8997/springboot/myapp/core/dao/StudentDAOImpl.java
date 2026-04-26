package com.kp8997.springboot.myapp.core.dao;

import com.kp8997.springboot.myapp.core.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Optional<Student> findById(int id) {
        return Optional.ofNullable(entityManager.find(Student.class, id));
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

    @Override
    @Transactional
    public void update(Student student) {
        // merge function will find the object in persistent context
        // and copy the new value from student into the detached reference in persistent context
        // or it will fetch from db if not found
        // and return the object as managed one so we can do the operation
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(int studentId) {
        // this will not work because in our app, student object is detached in persistent context
        // remove() find it detached and can not delete
        // the object must be in managed state if we modify through persistent context
        // another way to fix is perform find in this method that are all under a transaction
        // findById(student.id)
        // gentityManager.remove(student);

        entityManager.remove(entityManager.find(Student.class, studentId));
    }

    @Override
    @Transactional
    public int deleteAll() {
        return entityManager.createQuery("DELETE FROM Student").executeUpdate();
    }

    @Override
    // get method doesn't need transactional annotation
    public Optional<List<Student>> getAllStudents() {
        return Optional.ofNullable(entityManager
                .createQuery("SELECT s FROM Student s ORDER BY s.firstName", Student.class)
                .getResultList());
    }
}
