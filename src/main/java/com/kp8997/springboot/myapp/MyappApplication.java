package com.kp8997.springboot.myapp;

import com.kp8997.springboot.myapp.dao.StudentDAO;
import com.kp8997.springboot.myapp.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
//import org.springframework.boot.security.autoconfigure.actuate.web.servlet.ManagementWebSecurityAutoConfiguration;

// default, it will only look the main one com.kp8997.springboot.myapp in this case
// can add more scan base packages in @SpringBootApplication
/*
@SpringBootApplication(scanBasePackages = {"com.kp8997.springboot.utils", "com.kp8997.springboot.handlers"})
 */
@SpringBootApplication
public class MyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return r -> {
			//createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			System.out.println("Hello World");
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Create a new student object");
		Student student = new Student("John", "Doe", "doejohn@test.com");

		studentDAO.save(student);

		System.out.println("Saving the student object");

		System.out.println("Student saved. Id: " + student.id);
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
}
