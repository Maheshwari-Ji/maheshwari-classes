CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(1000) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS teacher(
    userid INT NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255),
    middleName VARCHAR(255),
    educationDetails VARCHAR(255),
    contactNumber INT,
    experience INT,
    dob DATE,
    age INT,
    email VARCHAR(255),
    address VARCHAR(255),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS student(
    userid INT NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255),
    middleName VARCHAR(255),
    fatherName VARCHAR(255),
    motherName VARCHAR(255),
    contactNumber INT,
    dob DATE,
    age INT,
    address VARCHAR(255),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS course(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    teacherId INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS batch(
    id INT NOT NULL AUTO_INCREMENT,
    batchName VARCHAR(255) NOT NULL,
    fees INT NOT NULL,
    startDate DATE,
    endDate DATE,
    duration INT,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS batch_course(
    batchId INT NOT NULL,
    courseId INT NOT NULL,
    PRIMARY KEY(batchId,courseId),
    FOREIGN KEY(batchId) REFERENCES batch(id) ON DELETE CASCADE,
    FOREIGN KEY(courseId) REFERENCES course(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS batch_student(
    batchId INT NOT NULL,
    studentId INT NOT NULL,
    PRIMARY KEY(batchId,studentId),
    FOREIGN KEY(batchId) REFERENCES batch(id) ON DELETE CASCADE,
    FOREIGN KEY(studentId) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS notes(
    id INT NOT NULL AUTO_INCREMENT,
    courseId INT NOT NULL,
    date DATE,
    title VARCHAR(100),
    notes VARCHAR(10000),
    PRIMARY KEY(id),
    FOREIGN KEY(courseId) REFERENCES course(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tests(
    id INT NOT NULL AUTO_INCREMENT,
    courseId INT NOT NULL,
    testName VARCHAR(255),
    question VARCHAR(10000),
    testDate DATE,
    startTime VARCHAR(255),
    endTime VARCHAR(255),
    duration INT,
    maximumMarks INT,
    averageMarks INT,
    PRIMARY KEY(id),
    FOREIGN KEY(courseId) REFERENCES course(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS answers(
    id INT NOT NULL,
    testId INT NOT NULL,
    studentId INT NOT NULL,
    answer VARCHAR(10000),
    date DATE,
    marks INT,
    PRIMARY KEY(testId,studentId),
    FOREIGN KEY(testId) REFERENCES tests(id) ON DELETE CASCADE,
    FOREIGN KEY(studentId) REFERENCES users(id) ON DELETE CASCADE 
);






