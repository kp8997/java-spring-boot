package com.kp8997.springboot.myapp.features.api;

import com.kp8997.springboot.myapp.core.dao.StudentDAO;
import com.kp8997.springboot.myapp.core.entity.Student;
import com.kp8997.springboot.myapp.common.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {
    private final StudentDAO studentDAO;

    public StudentRestController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping("")
    public List<Student> getStudents() {
        return studentDAO.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        return studentDAO.findById(studentId).orElseThrow(
                () -> new EntityNotFoundException("Student id not found - " + studentId)
        );
    }
}
