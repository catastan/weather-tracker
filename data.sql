CREATE DATABASE IF NOT EXISTS weather_db;

USE weather_db;

CREATE TABLE countries (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL UNIQUE,
       latitude DOUBLE NOT NULL,
       longitude DOUBLE NOT NULL
);

CREATE TABLE cities (
        id INT AUTO_INCREMENT PRIMARY KEY,
        country_id INT NOT NULL,
        name VARCHAR(100) NOT NULL,
        latitude DOUBLE NOT NULL,
        longitude DOUBLE NOT NULL,
        UNIQUE(country_id, name),
        FOREIGN KEY (country_id) REFERENCES countries(id) ON DELETE CASCADE
);

CREATE TABLE temperatures (
      id INT AUTO_INCREMENT PRIMARY KEY,
      city_id INT NOT NULL,
      value DOUBLE NOT NULL,
      timestamp DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
      UNIQUE(city_id, timestamp),
      FOREIGN KEY (city_id) REFERENCES cities(id) ON DELETE CASCADE
);
