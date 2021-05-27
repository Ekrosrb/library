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
    role INT NOT NULL,
    block BOOLEAN NOT NULL
);

CREATE TABLE books(
    id INT PRIMARY KEY AUTO_INCREMENT,
    img BLOB,
    name VARCHAR(40) NOT NULL,
    author VARCHAR(60) NOT NULL,
    edition VARCHAR(60) NOT NULL,
    description TEXT,
    description_ru TEXT,
    count INT NOT NULL
);

INSERT INTO users(first_name, last_name, email, password, birthday, phone, role, block)
VALUES
       ('Admin', 'Admin', 'admin@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2000-01-01', '123456', 0, false),
       ('Librarian', 'Librarian', 'lib@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2000-01-01', '123456', 1, false);


INSERT INTO books(name, author, edition, description, description_ru, count)
VALUES
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Book1', 'Richard', 'book edition inc', 'super book', 'супер книга', 2),
       ('Book2', 'Richard', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Book3', 'Max', 'book inc', 'super book3', 'супер книга 3', 3),
       ('4Book', 'Richard', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('5Book1', 'Max', 'book edit', 'super book5', 'супер книга 5', 1),
       ('B6ok', 'Max', 'book inc', 'super book6', 'супер книга 6', 3);
