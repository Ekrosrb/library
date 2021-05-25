ALTER DATABASE library CHARACTER SET utf8 COLLATE utf8_general_ci;
DROP TABLE IF EXISTS rents;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS auth_users;

CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    email VARCHAR(25) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    birthday DATE NOT NULL,
    phone VARCHAR(15) NOT NULL,
    role INT NOT NULL
);

CREATE TABLE books(
    id INT PRIMARY KEY AUTO_INCREMENT,
    img BLOB,
    name VARCHAR(40) NOT NULL UNIQUE,
    author VARCHAR(60) NOT NULL,
    edition VARCHAR(60) NOT NULL,
    description TEXT,
    description_ru TEXT,
    count INT NOT NULL
);

INSERT INTO users(first_name, last_name, email, password, birthday, phone, role)
VALUES
       ('Admin', 'Admin', 'admin@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2000-01-01', '123456', 0),
       ('Librarian', 'Librarian', 'lib@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2000-01-01', '123456', 1);

