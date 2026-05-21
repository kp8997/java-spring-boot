package com.kp8997.springboot.myapp;

import com.kp8997.springboot.myapp.core.dao.AccountDAO;
import com.kp8997.springboot.myapp.core.dao.AppDAO;
import com.kp8997.springboot.myapp.core.dao.MembershipDAO;
import com.kp8997.springboot.myapp.core.dao.StudentDAO;
import com.kp8997.springboot.myapp.core.entity.*;
import com.kp8997.springboot.myapp.features.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.security.SecureRandom;
import java.util.List;

// default, it will only look the main one com.kp8997.springboot.myapp in this case
// can add more scan base packages in @SpringBootApplication
/*
@SpringBootApplication(scanBasePackages = {"com.kp8997.springboot.utils", "com.kp8997.springboot.handlers"})
 */
@SpringBootApplication
public class MyappApplication {

    private static final SecureRandom rand = new SecureRandom();

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
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO,
                                               AppDAO appDAO,
                                               AccountDAO accountDAO,
                                               MembershipDAO membershipDAO,
                                               TrafficFortuneService trafficFortuneService) {
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

            //createInstructor(appDAO);
            findInstructor(appDAO);
            //deleteInstructor(appDAO);
            findInstructorDetail(appDAO);
            //deleteInstructorDetail(appDAO);

            //createInstructorWithCourses(appDAO);
            //findInstructorWithCourses(appDAO);
            findInstructorWithCoursesLazy(appDAO);
            findInstructorWithJoinFetch(appDAO);
            updateInstructor(appDAO);
            updateCourse(appDAO);
            //deleteCourse(appDAO);
            //deleteInstructor(appDAO);

            //createCourseAndReviews(appDAO);
            //retrieveCourseAndReviews(appDAO);
            //deleteCourseAndReviews(appDAO);

            //createCourseAndStudents(appDAO);
            findCourseAndStudents(appDAO);
            //findStudentAndCourses(appDAO);
            //addMoreCoursesForStudent(appDAO);
            //deleteCourse(appDAO);
            //deleteStudent(appDAO);

            doStuffTheBeforeAdvice(accountDAO, membershipDAO);
            doStuffTheAfterReturningAdvice(accountDAO, membershipDAO);
            doStuffTheAfterThrowingAdvice(accountDAO, membershipDAO);
            doStuffTheAfterAdvice(accountDAO, membershipDAO);
            doStuffTheAroundAdvice(trafficFortuneService);
            doStuffTheAroundAdviceHandleException(trafficFortuneService);
            doStuffTheAroundAdviceThrowException(trafficFortuneService);
        };
    }

    private void doStuffTheAroundAdviceThrowException(TrafficFortuneService trafficFortuneService) {
        System.out.println("\n Around advice");

        System.out.println("Calling getFortune()");

        boolean flag = true;
        String data = trafficFortuneService.getFortune(flag);
    }

    private void doStuffTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
        System.out.println("\n Around advice");

        System.out.println("Calling getFortune()");

        boolean flag = true;
        String data = trafficFortuneService.getFortune(flag);
    }

    private void doStuffTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
        System.out.println("\n Around advice");

        System.out.println("Calling getFortune()");

        String data = trafficFortuneService.getFortune();
    }

    private void doStuffTheAfterAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        System.out.println("\n\n After Advice");

        List<Account> theAccounts = null;

        try {
            boolean flag = true;
            theAccounts = accountDAO.findAccount(flag);

        } catch (Exception e) {
            System.out.println("After Caught exception: " + e);
        }

        System.out.println("After Throwing theAccounts: " + theAccounts);

    }

    private void doStuffTheAfterThrowingAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        System.out.println("\n\n AfterReturning Advice");

        List<Account> theAccounts = null;

        try {
            boolean flag = true;
             theAccounts = accountDAO.findAccount(flag);

        } catch (Exception e) {
            System.out.println("Caught exception: " + e);
        }

        System.out.println("Throwing theAccounts: " + theAccounts);
    }

    private void doStuffTheAfterReturningAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        System.out.println("\n\n AfterReturning Advice");


        var theAccounts = accountDAO.findAccount();
        System.out.println("Returning theAccounts: " + theAccounts);
    }

    private void doStuffTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
        accountDAO.addAccount();

        accountDAO.addAccount();

        membershipDAO.addAccount();

        membershipDAO.addMemberAndReturnBoolean();

        accountDAO.addAccount(new Account("kp@test.com", "4"));

        accountDAO.addAccount(new Account("kp@test.com", "5"), true);

        // calling for pointcut combination

        accountDAO.setName("Jonathan");
        accountDAO.getName();
        accountDAO.setServiceCode("12345");
        accountDAO.getServiceCode();
        accountDAO.doWork();
        accountDAO.addAccount(new Account("kp@test.com", "4"));

    }

    private void deleteStudent(AppDAO appDAO) {
        int studentId = 160;

        appDAO.deleteStudentById(studentId);

        System.out.println("Deleted student with id " + studentId);
    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
        int studentId = 159;

        Student student = appDAO.findStudentAndCoursesById(studentId);

        Course course1 = new Course("Oracle Hardware + networking " + generateRandomString(5));
        Course course2 = new Course("Oracle Hardware + networking " + generateRandomString(5));

        student.addCourse(course1);
        student.addCourse(course2);

        appDAO.update(student);

        System.out.println("Student " + student);
        System.out.println("Associated Course " + student.getCourses());
    }

    private void findStudentAndCourses(AppDAO appDAO) {
        int id = 159;

        Student student = appDAO.findStudentAndCoursesById(id);

        System.out.println("Loaded student " + student);
        System.out.println("Loaded courses " + student.getCourses());
    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int id = 24;

        Course course = appDAO.findCourseAndStudentsById(id);

        System.out.println("Loaded course " + course);

        System.out.println("Loaded students " + course.getStudents());

    }

    private void createCourseAndStudents(AppDAO appDAO) {
        Course course = new Course("DEvop with OCI" + generateRandomString(4));

        Student s1 = new Student("John", "Doe", "doejohn@test.");
        Student s2 = new Student("Maradoni", "lavaka", "papa@test.com");

        course.addStudents(s1);
        course.addStudents(s2);

        appDAO.save(course);

        System.out.println("Created course" + course);
        System.out.println("Students: " + course.getStudents());
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int id = 8;

        appDAO.deleteCourseById(id);

        System.out.println("Delete the id: " + id);
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        int id = 11;

        Course course = appDAO.findCourseAndReviewById(id);

        List<Review> reviews = course.getReviews();

        System.out.println("Course: " + course);
        System.out.println("Reviews: " + reviews);
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        Course course = new Course("React and Nextjs 123");

        course.addReview(new Review("Wow, it looks pretty good"));
        course.addReview(new Review("quite good"));
        course.addReview(new Review("Hard to trace"));
        course.addReview(new Review("Hard to SEO of Google"));

        appDAO.save(course);
        System.out.println("Course created with reviews");
    }

    private void deleteCourse(AppDAO appDAO) {
        int id = 14;
        appDAO.deleteCourseById(id);

        System.out.println("Deleted the course with id " + id);
    }

    private void updateCourse(AppDAO appDAO) {
        int id = 2;

        Course course = appDAO.findCourseById(id);

        course.setTitle("My new NextJs course " + generateRandomString(5));

        appDAO.update(course);
        System.out.println("Updated");
    }

    private String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char randomChar = (char) (rand.nextInt(26) + 'A');
            sb.append(randomChar);
        }
        return sb.toString();
    }

    private void updateInstructor(AppDAO appDAO) {
        int id = 10;
        Instructor instructor = appDAO.findInstructorById(id);

        System.out.println("Instructor id " + id);
        String randomSuffix = generateRandomString(4);
        System.out.println("Instructor random suffix " + randomSuffix);
        instructor.setLastName("Johnathan " + randomSuffix);

        appDAO.update(instructor);
        System.out.println("Updated");
    }

    private void findInstructorWithJoinFetch(AppDAO appDAO) {
        int id = 10;
        Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

        System.out.println("Instructor: " + instructor);
        System.out.println("Courses join fetch: " + instructor.getCourses());
        System.out.println("Instructor Detail: " + instructor.getInstructorDetail());
    }

    private void findInstructorWithCoursesLazy(AppDAO appDAO) {
        int id = 9;
        Instructor instructor = appDAO.findInstructorById(id);

        System.out.println("Instructor: " + instructor);

        instructor.setCourses(appDAO.findCourseByInstructorId(id));
        System.out.println("Instructor with Courses: : " + instructor.getCourses());
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int id = 10;
        Instructor instructor = appDAO.findInstructorById(id);

        System.out.println("Instructor: " + instructor);
        System.out.println("Courses attachment: " + instructor.getCourses());
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
