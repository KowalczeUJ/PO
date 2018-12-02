CREATE DATABASE IF NOT EXISTS HOTEL;

USE HOTEL;

CREATE USER IF NOT EXISTS 'projektowanie'@'localhost' IDENTIFIED BY 'obiektowe';
GRANT ALL PRIVILEGES ON HOTEL.* TO 'projektowanie'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS user (
  id INT(10) AUTO_INCREMENT NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  type TINYINT(4) NOT NULL DEFAULT '2',
  is_regular TINYINT(1) DEFAULT '0',
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (username),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS room (
  id INT(10) AUTO_INCREMENT NOT NULL,
  number varchar(5) NOT NULL,
  floor int(10) NOT NULL,
  beds int(10) NOT NULL,
  price_per_night decimal(6,2) NOT NULL,
  `type` int(10) NOT NULL,
  UNIQUE (number),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_details (
  id INT(10) AUTO_INCREMENT NOT NULL,
  user_id INT(10) NOT NULL,
  address VARCHAR(50) NOT NULL,
  city VARCHAR(50) NOT NULL,
  phone_number VARCHAR(12) NOT NULL,
  birth_date DATE NOT NULL,
  PRIMARY KEY (id),
  UNIQUE(user_id),
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reservation (
  id INT(10) AUTO_INCREMENT NOT NULL,
  user_id INT(10) NOT NULL,
  room_id INT(10) NOT NULL,
  price DECIMAL(6,2) NOT NULL,
  persons INT(1) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  created_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
  FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE
);

INSERT INTO
    user (`username`, `password`, `type`, `is_regular`)
VALUES
    ('janKowalski123', 'janek123', 2, 0),
    ('admin', 'admin', 0, 0),
    ('receptionist1', 'password', 1, 0),
    ('johnNewman', 'johnny68', 2, 1);

INSERT INTO
    user_details (user_id, address, city, phone_number, birth_date)
VALUES
    (1, 'Main Street 12', 'New York', '129928391', '1971-12-24'),
    (2, 'Manning Square 122', 'Chicago', '857329102', '1985-10-01'),
    (3, 'Ozark 01', 'Ozark', '123940294', '1953-06-06'),
    (4, 'Melrose 403', 'Los Angeles', '049492893', '1991-04-25');

INSERT INTO
    room (number, floor, beds, price_per_night, type)
VALUES
    ('1A', 1, 2, 40.00, 0),
    ('1B', 1, 3, 55.00, 0),
    ('1C', 1, 4, 70.00, 1),
    ('1D', 1, 6, 90.00, 1),
    ('2A', 2, 2, 50.00, 2),
    ('2B', 2, 4, 100.00, 2),
    ('3A', 3, 4, 300.00, 3),
    ('3B', 4, 2, 500.00, 3);

INSERT INTO
    reservation (user_id, room_id, price, persons, start_date, end_date)
VALUES
    (1, 5, 150.00, 2, '2019-02-13', '2019-02-15'),
    (4, 8, 5312.50, 2, '2019-04-16', '2019-04-21');