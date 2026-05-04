package com.kp8997.springboot.myapp;

import com.kp8997.springboot.myapp.core.dao.StudentDAO;
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
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        // 1. Override the "Create User" query to convert boolean to smallint
        // PostgreSQL doesn't support casting boolean to smallint directly.
        //manager.setCreateUserSql(
        //        "insert into users (username, password, enabled) values (?,?, CASE WHEN ? THEN 1 ELSE 0 END)"
        //);
        //
        //// 2. Override the "Load User" query if necessary
        //// This ensures that when Spring reads the smallint, it treats 1 as true.
        //manager.setUsersByUsernameQuery(
        //        "select username, password, (enabled::int = 1) as enabled from users where username = ?"
        //);

        // 2. Override the "Load User" query if necessary
        // This ensures that when Spring reads the smallint, it treats 1 as true.
        manager.setUsersByUsernameQuery(
                "select user_id, password, (active::int = 1) as enabled from members where user_id = ?"
        );

        manager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id = ?"
        );

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
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
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return r -> {
            System.out.println("Hello World");

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

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Delete all students");
        int num = studentDAO.deleteAll();
        System.out.println("Deleted row count " + num);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int id = 3;
        Optional<Student> student = studentDAO.findById(id);
        if (student.isEmpty()) {
            return;
        }
        System.out.println("Before delete Student: " + student);

        System.out.println("Deleting student with id: " + id);
        studentDAO.delete(id);

        System.out.println("After Deleted student");
    }

    private void updateStudent(StudentDAO studentDAO) {
        int id = 2;
        Optional<Student> student = studentDAO.findById(id);
        if (student.isEmpty()) {
            return;
        }
        System.out.println("Before update Student: " + student);

        System.out.println("Updating student with id: " + id);
        student.get().setFirstName("Scooby");

        studentDAO.update(student.orElse(null));

        System.out.println("Updated student: " + student);
    }

    private void queryStudentsByLastName(StudentDAO studentDAO) {
        var students = studentDAO.findByLastName("Doe");

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void queryAllStudents(StudentDAO studentDAO) {
        var students = studentDAO.findAll();

        for (Student s : students) {
            System.out.println(s);
        }
    }

    private void createStudent(StudentDAO studentDAO) {
        System.out.println("Create a new student object");
        Student student = new Student("John", "Doe", "doejohn@test.com");

        studentDAO.save(student);

        System.out.println("Saving the student object");

        System.out.println("Student saved. Id: " + student.getId());
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        System.out.println("Create multiple students");
        Student student1 = new Student("Paul", "Doe", "doejohn@test.com");
        Student student2 = new Student("Mary", "Public", "publicmary@test.com");
        Student student3 = new Student("Bonita", "Applebum", "applebum@test.com");

        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);
    }

    private void createAndRetrieveStudent(StudentDAO studentDAO) {
        System.out.println("Create student object");
        Student student = new Student("Dazzy", "Dougless", "dg@test.com");

        System.out.println("Saving student info");
        studentDAO.save(student);

        System.out.println("Retrieve new student: " + student.getId());
        Optional<Student> retrievedStudent = studentDAO.findById(student.getId());
        if (retrievedStudent.isEmpty()) {
            return;
        }

        System.out.println("Returned student: " + retrievedStudent);
    }
}