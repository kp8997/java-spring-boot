package com.kp8997.springboot.myapp.features.api;

import com.kp8997.springboot.myapp.core.dao.StudentDAO;
import com.kp8997.springboot.myapp.core.entity.Student;
import com.kp8997.springboot.myapp.common.exception.StudentNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<List<Student>> getStudents() {
        return studentDAO.getAllStudents();
    }

    @GetMapping("/students/{studentId}")
    public Optional<Student> getStudent(@PathVariable int studentId) {
        //if ((studentId >= theStudents.size()) || (studentId < 0)) {
        //    throw new RuntimeException("Student id not found - " + studentId);
        //}
        //return theStudents.get(studentId);
        var result = studentDAO.findById(studentId);
        if (result.isEmpty()) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return result;
    }
}
