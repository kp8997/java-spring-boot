package com.kp8997.springboot.myapp.core.dao;

import com.kp8997.springboot.myapp.core.entity.Course;
import com.kp8997.springboot.myapp.core.entity.Instructor;
import com.kp8997.springboot.myapp.core.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCourseByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void update(Course course);

    Course findCourseById(int courseId);

    void deleteCourseById(int id);
}
