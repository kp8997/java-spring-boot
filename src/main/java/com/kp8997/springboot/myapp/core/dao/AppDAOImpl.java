package com.kp8997.springboot.myapp.core.dao;

import com.kp8997.springboot.myapp.core.entity.Course;
import com.kp8997.springboot.myapp.core.entity.Instructor;
import com.kp8997.springboot.myapp.core.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {
    private final EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        entityManager.remove(entityManager.find(Instructor.class, id));
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);

        // without this line the execution won't trigger
        instructorDetail.getInstructor().setInstructorDetail(null);
        // or we can call flush after removed

        entityManager.remove(instructorDetail);

        // like this
        // but not really recommend do it since it breaks the nature of persistent context
        //entityManager.flush();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " +
                        "JOIN FETCH i.courses " +
                        "JOIN FETCH i.instructorDetail " +
                        "where i.id = ?1",
                Instructor.class);
        query.setParameter(1, id);

        return query.getSingleResult();
    }

    @Override
    public List<Course> findCourseByInstructorId(int id) {
        //Query query = entityManager.createQuery("select c from Course c where c.instructor.id = ?");
        // shorthand will be:
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = ?1", Course.class);
        query.setParameter(1, id);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int courseId) {
        return entityManager.find(Course.class, courseId);
    }
}
