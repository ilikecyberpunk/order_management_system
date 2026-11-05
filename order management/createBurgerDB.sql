CREATE DATABASE IF NOT EXISTS burger_shop;
USE burger_shop;

CREATE TABLE burgers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    burger_id INT NOT NULL,
    quantity INT NOT NULL,
    total_price INT NOT NULL,
    FOREIGN KEY (burger_id) REFERENCES burgers(id)
) ENGINE=InnoDB;