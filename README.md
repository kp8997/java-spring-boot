// create user for postgresql needed for this course
CREATE ROLE admin_spring WITH LOGIN SUPERUSER PASSWORD 'p@ssw0rd123';

// create table for postgresql needed for this course
CREATE TABLE students (id SERIAL NOT NULL PRIMARY KEY, first_name VARCHAR(45) DEFAULT NULL, last_name VARCHAR(45)
DEFAULT NULL, email VARCHAR(45) DEFAULT NULL );

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

// for starter-security jdbc
CREATE TABLE users (
username VARCHAR(50) PRIMARY KEY,
password VARCHAR(70) DEFAULT NULL,
enabled SMALLINT NOT NULL
);

INSERT INTO users VALUES
('john', '{noop}test123', 1),
('marry', '{noop}test123', 1),
('tim', '{noop}test123', 1);

INSERT INTO users VALUES
('john','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('marry','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('tim','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);

CREATE TABLE authorities (
username VARCHAR(50) NOT NULL,
authority VARCHAR(50) NOT NULL,
CONSTRAINT uq_authorities_username_authority UNIQUE (username, authority),
CONSTRAINT authorities_ibfk FOREIGN KEY(username) REFERENCES users(username) ON DELETE CASCADE
);

INSERT INTO authorities VALUES
('john', 'ROLE_EMPLOYEE'),
('marry', 'ROLE_EMPLOYEE'),
('marry', 'ROLE_MANAGER'),
('tim', 'ROLE_EMPLOYEE'),
('tim', 'ROLE_MANAGER'),
('tim', 'ROLE_ADMIN');

// ====================
// customize table
CREATE TABLE members (
user_id VARCHAR(50) PRIMARY KEY,
password VARCHAR(70) DEFAULT NULL,
active SMALLINT NOT NULL
);

INSERT INTO members VALUES
('john', '{noop}test123', 1),
('marry', '{noop}test123', 1),
('tim', '{noop}test123', 1);

INSERT INTO members VALUES
('john','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('marry','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1),
('tim','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1);

CREATE TABLE roles (
user_id VARCHAR(50) NOT NULL,
role VARCHAR(50) NOT NULL,
CONSTRAINT uq_roles_user_id_role UNIQUE (user_id, role),
CONSTRAINT roles_ibfk FOREIGN KEY(user_id) REFERENCES members(user_id) ON DELETE CASCADE
);

INSERT INTO roles VALUES
('john', 'ROLE_EMPLOYEE'),
('marry', 'ROLE_EMPLOYEE'),
('marry', 'ROLE_MANAGER'),
('tim', 'ROLE_EMPLOYEE'),
('tim', 'ROLE_MANAGER'),
('tim', 'ROLE_ADMIN');


==================== instructor ==========

CREATE TABLE instructor_details (
id SERIAL PRIMARY KEY,
youtube_channel varchar(128) DEFAULT NULL,
hobby varchar(45) DEFAULT NULL
);

CREATE TABLE instructors (
id SERIAL PRIMARY KEY,—
first_name varchar(45) DEFAULT NULL,
last_name varchar(45) DEFAULT NULL,
email varchar(45) DEFAULT NULL,
instructor_detail_id int DEFAULT NULL,
CONSTRAINT fk_detail FOREIGN KEY (instructor_detail_id) REFERENCES instructor_details (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);