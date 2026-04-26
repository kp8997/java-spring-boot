package com.kp8997.springboot.myapp;

import com.kp8997.springboot.myapp.dao.StudentDAO;
import com.kp8997.springboot.myapp.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
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
			System.out.println("Hello World");

			//createStudent(studentDAO);
			//createMultipleStudents(studentDAO);
			//createAndRetrieveStudent(studentDAO);
			//queryAllStudents(studentDAO);
			//queryStudentsByLastName(studentDAO);

			updateStudent(studentDAO);
		};
	}

	private void updateStudent(StudentDAO studentDAO) {
		int id = 2;
		Student student = studentDAO.findById(id);
		System.out.println("Before update Student: " + student);

		System.out.println("Updating student with id: " + id);
		student.setFirstName("Scooby");

		studentDAO.update(student);

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
		Student retrievedStudent = studentDAO.findById(student.getId());

		System.out.println("Returned student: " + retrievedStudent);
	}
}
