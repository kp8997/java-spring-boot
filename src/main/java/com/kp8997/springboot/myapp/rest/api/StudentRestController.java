package com.kp8997.springboot.myapp.rest.api;

import com.kp8997.springboot.myapp.dao.StudentDAO;
import com.kp8997.springboot.myapp.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    //private List<Student> theStudents;

    private  StudentDAO studentDAO;

    public StudentRestController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    //@PostConstruct
    //public void loadData() {
    //    theStudents = new ArrayList<>();
    //
    //    theStudents.add(new Student("Poornima", "Patel", "poornima@test.com"));
    //    theStudents.add(new Student("Mario", "Rossi", "mario@test.com"));
    //    theStudents.add(new Student("Mary", "Smith", "mary@test.com"));
    //}

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentDAO.getAllStudents();
    }

    //@GetMapping("/students/{studentId}")
    //public Student getStudent(@PathVariable int studentId) {
    //    if ((studentId >= theStudents.size()) || (studentId < 0)) {
    //        throw new RuntimeException("Student id not found - " + studentId);
    //    }
    //    return theStudents.get(studentId);
    //}

}
