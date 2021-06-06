ALTER DATABASE library CHARACTER SET utf8 COLLATE utf8_general_ci;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS books;

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
    name VARCHAR(140) NOT NULL,
    author VARCHAR(140) NOT NULL,
    edition VARCHAR(140) NOT NULL,
    description TEXT,
    description_ru TEXT,
    count INT UNSIGNED NOT NULL,
    UNIQUE (name, author)
);

CREATE TABLE orders(
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    term DATE NOT NULL,
    order_date DATE DEFAULT (CURRENT_DATE),
    fine LONG NOT NULL,
    status INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE,
    UNIQUE (user_id, book_id)
);

INSERT INTO users(first_name, last_name, email, password, birthday, phone, role, block)
VALUES
       ('Admin', 'Admin', 'admin@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2000-05-11', '123456', 0, false),
       ('Librarian', 'Librarian', 'lib@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2000-01-01', '54265624', 1, false),
       ('Mail', 'Mail', 'mail@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2001-02-01', '76513735', 2, false),
       ('Mail2', 'Mail2', 'mail2@mail.com', '827ccb0eea8a706c4c34a16891f84e7b', '2000-01-03', '2156674', 2, false);


INSERT INTO books(name, author, edition, description, description_ru, count)
VALUES
       ('Harry Potter and the Half-Blood Prince (Harry Potter #6)', 'J.K. Rowling/Mary GrandPré', 'book edition inc', 'super book', 'супер книга', 2),
       ('Harry Potter and the Order of the Phoenix (Harry Potter #5)', 'J.K. Rowling/Mary GrandPré', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Harry Potter and the Chamber of Secrets (Harry Potter #2)', 'J.K. Rowling', 'book inc', 'super book3', 'супер книга 3', 3),
       ('Harry Potter and the Prisoner of Azkaban (Harry Potter #3)', 'J.K. Rowling/Mary GrandPré', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('Harry Potter Boxed Set Books 1-5 (Harry Potter #1-5)', 'J.K. Rowling/Mary GrandPré', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Unauthorized Harry Potter Book Seven News: "Half-Blood Prince" Analysis and Speculation', 'W. Frederick Zimmerman', 'book edition inc', 'super book', 'супер книга', 2),
       ('Harry Potter Collection (Harry Potter #1-6)', 'J.K. Rowling', 'book edition', 'super book2', 'супер книга 2', 5),
       ('The Ultimate Hitchhiker''s Guide: Five Complete Novels and One Story (Hitchhiker''s Guide to the Galax...', 'Douglas Adams', 'book inc', 'super book3', 'супер книга 3', 3),
       ('The Ultimate Hitchhiker''s Guide to the Galaxy (Hitchhiker''s Guide to the Galaxy #1-5)', 'Douglas Adams', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('The Hitchhiker''s Guide to the Galaxy (Hitchhiker''s Guide to the Galaxy #1)', 'Douglas Adams', 'book edit', 'super book5', 'супер книга 5', 1),
       ('The Hitchhiker''s Guide to the Galaxy (Hitchhiker''s Guide to the Galaxy #1', 'Douglas Adams/Stephen Fry', 'book edition inc', 'super book', 'супер книга', 2),
       ('The Ultimate Hitchhiker''s Guide (Hitchhiker''s Guide to the Galaxy #1-5)', 'Douglas Adams', 'book edition', 'super book2', 'супер книга 2', 5),
       ('A Short History of Nearly Everything', 'Bill Bryson', 'book inc', 'super book3', 'супер книга 3', 3),
       ('Bill Bryson''s African Diary', 'Bill Bryson', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('Bryson''s Dictionary of Troublesome Words: A Writer''s Guide to Getting It Right', 'Bill Bryson', 'book edit', 'super book5', 'супер книга 5', 1),
       ('In a Sunburned Country', 'Bill Bryson', 'book edition inc', 'super book', 'супер книга', 2),
       ('I''m a Stranger Here Myself: Notes on Returning to America After Twenty Years Away', 'Bill Bryson', 'book edition', 'super book2', 'супер книга 2', 5),
       ('The Lost Continent: Travels in Small Town America', 'Bill Bryson', 'book inc', 'super book3', 'супер книга 3', 3),
       ('Neither Here nor There: Travels in Europe', 'Bill Bryson', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('Notes from a Small Island', 'Bill Bryson', 'book edit', 'super book5', 'супер книга 5', 1),
       ('The Mother Tongue: English and How It Got That Way', 'Bill Bryson', 'book edition inc', 'super book', 'супер книга', 2),
       ('J.R.R. Tolkien 4-Book Boxed Set: The Hobbit and The Lord of the Rings', 'J.R.R. Tolkien', 'book edition', 'super book2', 'супер книга 2', 5),
       ('The Lord of the Rings (The Lord of the Rings #1-3)', 'J.R.R. Tolkien', 'book inc', 'super book3', 'супер книга 3', 3),
       ('The Fellowship of the Ring (The Lord of the Rings #1)', 'J.R.R. Tolkien', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('The Lord of the Rings (The Lord of the Rings #1-3)', 'J.R.R. Tolkien/Alan Lee', 'book edit', 'super book5', 'супер книга 5', 1),
       ('The Lord of the Rings: Weapons and Warfare', 'Chris Smith/Christopher Lee/Richard Taylor', 'book edition inc', 'super book', 'супер книга', 2),
       ('The Lord of the Rings: Complete Visual Companion', 'Jude Fisher', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Agile Web Development with Rails: A Pragmatic Guide', 'Dave Thomas/David Heinemeier Hansson/Leon Breedt/Mike Clark/Thomas Fuchs/Andreas Schwarz', 'book inc', 'super book3', 'супер книга 3', 3),
       ('Hatchet (Brian''s Saga #1)', 'Gary Paulsen', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('Hatchet: A Guide for Using "Hatchet" in the Classroom', 'Donna Ickes/Edward Sciranko/Keith Vasconcelles', 'book edit', 'super book5', 'супер книга 5', 1),
       ('Guts: The True Stories behind Hatchet and the Brian Books', 'Gary Paulsen', 'book edition inc', 'super book', 'супер книга', 2),
       ('Molly Hatchet - 5 of the Best', 'Molly Hatchet', 'book edition', 'super book2', 'супер книга 2', 5),
       ('Hatchet Jobs: Writings on Contemporary Fiction', 'Dale Peck', 'book inc', 'super book3', 'супер книга 3', 3),
       ('A Changeling for All Seasons (Changeling Seasons #1)', 'Angela Knight/Sahara Kelly/Judy Mays/Marteeka Karland/Kate Douglas/Shelby Morgen/Lacey Savage/Kate H...', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('Changeling (Changeling #1)', 'Delia Sherman', 'book edit', 'super book5', 'супер книга 5', 1),
       ('The Changeling Sea', 'Patricia A. McKillip', 'book edition inc', 'super book', 'супер книга', 2),
       ('The Changeling', 'Zilpha Keatley Snyder', 'book edition', 'super book2', 'супер книга 2', 5),
       ('The Changeling', 'Kate Horsley', 'book inc', 'super book3', 'супер книга 3', 3),
       ('The Changeling (Daughters of England #15)', 'Philippa Carr', ' edition inc', 'super book4', 'супер книга 4', 7),
       ('The Known World', 'Edward P. Jones', 'book edit', 'super book5', 'супер книга 5', 1);

INSERT INTO orders(user_id, book_id, term, order_date, fine, status)
VALUES
(4, 1, '2021-03-03','2021-05-05', 27, 3),
(4, 5, '2021-03-11', '2021-05-05', 17, 3),
(4, 2, '2021-04-11', '2021-05-05', 14, 3),
(4, 8, '2021-03-12', '2021-05-05', 21, 3),
(3, 17, '2021-03-03', '2021-05-05', 0, 2),
(3, 9, '2021-03-03', '2021-05-05', 0, 2),
(3, 4, '2021-03-03', '2021-05-05', 0, 0),
(3, 5, '2021-03-03', '2021-05-05', 0, 1),
(3, 16, '2021-03-03', '2021-05-05', 0, 4),
(1, 16, '2021-03-03', '2021-05-05', 0, 4),
(1, 8, '2021-03-03', '2021-05-05', 0, 5),
(1, 9, '2021-03-03', '2021-05-05', 0, 5);