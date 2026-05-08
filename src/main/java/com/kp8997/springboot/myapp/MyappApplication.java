package com.kp8997.springboot.myapp;

import com.kp8997.springboot.myapp.core.dao.AppDAO;
import com.kp8997.springboot.myapp.core.dao.StudentDAO;
import com.kp8997.springboot.myapp.core.entity.Course;
import com.kp8997.springboot.myapp.core.entity.Instructor;
import com.kp8997.springboot.myapp.core.entity.InstructorDetail;
import com.kp8997.springboot.myapp.core.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

// default, it will only look the main one com.kp8997.springboot.myapp in this case
// can add more scan base packages in @SpringBootApplication
/*
@SpringBootApplication(scanBasePackages = {"com.kp8997.springboot.utils", "com.kp8997.springboot.handlers"})
 */
@SpringBootApplication
public class MyappApplication {

    static void main(String[] args) {
        SpringApplication.run(MyappApplication.class, args);
    }

    @Bean
    CommandLineRunner initAdmin(
            JdbcUserDetailsManager userDetailsManager,
            PasswordEncoder passwordEncoder) {
        return args -> {

            List<String> usernames = List.of("john", "marry", "tim", "kan");

            for (String username : usernames) {
                if (userDetailsManager.userExists(username)) {
                    continue;
                }

                UserBuilder user = User.builder().username(username);

                // When explicitly using BCryptPasswordEncoder, we shouldn't append {bcrypt}
                String password = passwordEncoder.encode("test123");

                if (username.equals("john")) {
                    user
                            .password(password)
                            .roles("EMPLOYEE");

                }
                if (username.equals("marry")) {
                    user
                            .password(password)
                            .roles("EMPLOYEE", "MANAGER");
                }
                if (username.equals("tim")) {
                    user
                            .password(password)
                            .roles("EMPLOYEE", "MANAGER", "ADMIN");
                }
                if (username.equals("kan")) {
                    user
                            .password(password)
                            .roles("EMPLOYEE", "MANAGER", "ADMIN");
                }

                UserDetails builder = user.build();
                userDetailsManager.createUser(builder);
            }
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO, AppDAO appDAO) {
        return r -> {
            System.out.println("Hello World");
            //createInstructor(appDAO);
            findInstructor(appDAO);
            //deleteInstructor(appDAO);
            findInstructorDetail(appDAO);
            //deleteInstructorDetail(appDAO);

            createInstructorWithCourses(appDAO);

            //createStudent(studentDAO);
            //createMultipleStudents(studentDAO);
            //createAndRetrieveStudent(studentDAO);
            //queryAllStudents(studentDAO);
            //queryStudentsByLastName(studentDAO);
            //updateStudent(studentDAO);
            //deleteStudent(studentDAO);
            //deleteAllStudents(studentDAO);
        };
    }

    private void createInstructorWithCourses(AppDAO appDAO) {
        Instructor instructor = new Instructor("Jet", "Pan", "jp@test.com");

        InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/@kannnahan9120", "coding and gggggggg");
        instructor.setInstructorDetail(instructorDetail);

        instructor.add(new Course("Martial Art Master"));
        instructor.add(new Course("Master of Java"));

        // cascade.ALL include persist so the relational entity will be persisted too
        System.out.println("Instructor with COURSES created" + instructor);
        appDAO.save(instructor);
    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int id = 6;
        System.out.println("Deleting instructor detail id: " + id);
        appDAO.deleteInstructorDetailById(id);
        System.out.println("Deleted");
    }

    private void findInstructorDetail(AppDAO appDAO) {
        int id = 1;
        InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
        System.out.println("Instructor Detail with inverted direction: " + instructorDetail);
        System.out.println("Instructor: " + instructorDetail.getInstructor());
    }

    private void deleteInstructor(AppDAO appDAO) {
        int id = 4;
        System.out.println();

        appDAO.deleteInstructorById(id);

        System.out.println("Deleted instructor by id: " + id);
    }

    private void findInstructor(AppDAO appDAO) {
        int id = 1;
        Instructor instructor = appDAO.findInstructorById(id);
        System.out.println("Instructor: " + instructor);
        // cascade.ALL include find so the relational entity will be found too

        System.out.println("Instructor Detail: " + instructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {
        Instructor instructor = new Instructor("Kan", "Han", "kp@test.com");

        InstructorDetail instructorDetail = new InstructorDetail("https://www.youtube.com/@kannnahan9120", "coding and gggggggg");
        instructor.setInstructorDetail(instructorDetail);

        // cascade.ALL include persist so the relational entity will be persisted too
        System.out.println("Instructor created" + instructor);
        appDAO.save(instructor);

    }

    //private void deleteAllStudents(StudentDAO studentDAO) {
    //    System.out.println("Delete all students");
    //    int num = studentDAO.deleteAll();
    //    System.out.println("Deleted row count " + num);
    //}
    //
    //private void deleteStudent(StudentDAO studentDAO) {
    //    int id = 3;
    //    Optional<Student> student = studentDAO.findById(id);
    //    if (student.isEmpty()) {
    //        return;
    //    }
    //    System.out.println("Before delete Student: " + student);
    //
    //    System.out.println("Deleting student with id: " + id);
    //    studentDAO.delete(id);
    //
    //    System.out.println("After Deleted student");
    //}
    //
    //private void updateStudent(StudentDAO studentDAO) {
    //    int id = 2;
    //    Optional<Student> student = studentDAO.findById(id);
    //    if (student.isEmpty()) {
    //        return;
    //    }
    //    System.out.println("Before update Student: " + student);
    //
    //    System.out.println("Updating student with id: " + id);
    //    student.get().setFirstName("Scooby");
    //
    //    studentDAO.update(student.orElse(null));
    //
    //    System.out.println("Updated student: " + student);
    //}
    //
    //private void queryStudentsByLastName(StudentDAO studentDAO) {
    //    var students = studentDAO.findByLastName("Doe");
    //
    //    for (Student s : students) {
    //        System.out.println(s);
    //    }
    //}
    //
    //private void queryAllStudents(StudentDAO studentDAO) {
    //    var students = studentDAO.findAll();
    //
    //    for (Student s : students) {
    //        System.out.println(s);
    //    }
    //}
    //
    //private void createStudent(StudentDAO studentDAO) {
    //    System.out.println("Create a new student object");
    //    Student student = new Student("John", "Doe", "doejohn@test.com");
    //
    //    studentDAO.save(student);
    //
    //    System.out.println("Saving the student object");
    //
    //    System.out.println("Student saved. Id: " + student.getId());
    //}
    //
    //private void createMultipleStudents(StudentDAO studentDAO) {
    //    System.out.println("Create multiple students");
    //    Student student1 = new Student("Paul", "Doe", "doejohn@test.com");
    //    Student student2 = new Student("Mary", "Public", "publicmary@test.com");
    //    Student student3 = new Student("Bonita", "Applebum", "applebum@test.com");
    //
    //    studentDAO.save(student1);
    //    studentDAO.save(student2);
    //    studentDAO.save(student3);
    //}
    //
    //private void createAndRetrieveStudent(StudentDAO studentDAO) {
    //    System.out.println("Create student object");
    //    Student student = new Student("Dazzy", "Dougless", "dg@test.com");
    //
    //    System.out.println("Saving student info");
    //    studentDAO.save(student);
    //
    //    System.out.println("Retrieve new student: " + student.getId());
    //    Optional<Student> retrievedStudent = studentDAO.findById(student.getId());
    //    if (retrievedStudent.isEmpty()) {
    //        return;
    //    }
    //
    //    System.out.println("Returned student: " + retrievedStudent);
    //}
}
