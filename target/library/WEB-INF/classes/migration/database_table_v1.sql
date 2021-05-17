DROP TABLE IF EXISTS rents;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS roles;

CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(25) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    role_name VARCHAR(20) NOT NULL
);

INSERT INTO users(email, password, role_name)
VALUES ('admin@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', 'ADMIN'),
       ('librarian@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', 'LIBRARIAN'),
       ('user@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', 'USER');
