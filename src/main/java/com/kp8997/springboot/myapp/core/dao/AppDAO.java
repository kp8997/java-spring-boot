package com.kp8997.springboot.myapp.core.dao;

import com.kp8997.springboot.myapp.core.entity.Instructor;
import com.kp8997.springboot.myapp.core.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);
}
