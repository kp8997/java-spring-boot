// create user for postgresql needed for this course
CREATE ROLE admin_spring WITH LOGIN SUPERUSER PASSWORD 'p@ssw0rd123';

// create table for postgresql needed for this course
CREATE TABLE students (id SERIAL NOT NULL PRIMARY KEY, first_name VARCHAR(45) DEFAULT NULL, last_name VARCHAR(45) DEFAULT NULL, email VARCHAR(45) DEFAULT NULL );