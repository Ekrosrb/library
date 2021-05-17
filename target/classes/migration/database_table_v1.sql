DROP TABLE IF EXISTS rents;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS auth_users;

CREATE TABLE users(
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(25) NOT NULL UNIQUE,
    password VARCHAR(150) NOT NULL,
    role INT NOT NULL
);

INSERT INTO users(email, password, role)
VALUES ('admin@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', 0),
       ('librarian@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', 1),
       ('user@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', 2);
