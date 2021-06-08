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
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);