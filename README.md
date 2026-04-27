// create user for postgresql needed for this course
CREATE ROLE admin_spring WITH LOGIN SUPERUSER PASSWORD 'p@ssw0rd123';

// create table for postgresql needed for this course
CREATE TABLE students (id SERIAL NOT NULL PRIMARY KEY, first_name VARCHAR(45) DEFAULT NULL, last_name VARCHAR(45) DEFAULT NULL, email VARCHAR(45) DEFAULT NULL );

// Create db for employee_directory
// CREATE DATABASE employee_directory;

// Create table
CREATE TABLE employees (
id SERIAL PRIMARY KEY,
first_name varchar(45) DEFAULT NULL,
last_name varchar(45) DEFAULT NULL,
email varchar(45) DEFAULT NULL
);

// seed data
INSERT INTO employees VALUES
(1,'Leslie','Andrews','leslie@luv2code.com'),
(2,'Emma','Baumgarten','emma@luv2code.com'),
(3,'Avani','Gupta','avani@luv2code.com'),
(4,'Yuri','Petrov','yuri@luv2code.com'),
(5,'Juan','Vega','juan@luv2code.com');