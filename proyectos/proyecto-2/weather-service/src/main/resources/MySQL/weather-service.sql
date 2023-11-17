-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS weather_service;
USE weather_service;

-- Crear la tabla country
CREATE TABLE IF NOT EXISTS country (
  id INT PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(10) UNIQUE NOT NULL,
  name VARCHAR(100) NOT NULL
);

-- Crear la tabla state
CREATE TABLE IF NOT EXISTS state (
  id INT PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(10) UNIQUE NOT NULL,
  name VARCHAR(100) NOT NULL,
  country_id INT,
  FOREIGN KEY (country_id) REFERENCES country(id)
);

-- Crear la tabla city
CREATE TABLE IF NOT EXISTS city (
  id INT PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(10) UNIQUE NOT NULL,
  name VARCHAR(100) NOT NULL,
  zip_code VARCHAR(10) UNIQUE NOT NULL,
  state_id INT,
  FOREIGN KEY (state_id) REFERENCES state(id)
);

-- Crear la tabla forecast
CREATE TABLE IF NOT EXISTS forecast (
  id INT PRIMARY KEY AUTO_INCREMENT,
  city_id INT,
  temperature FLOAT NOT NULL,
  forecast_date DATE NOT NULL,
  FOREIGN KEY (city_id) REFERENCES city(id)
);

-- Crear la tabla forecast_log la bitácora 
CREATE TABLE IF NOT EXISTS forecast_log (
  id INT PRIMARY KEY AUTO_INCREMENT,
  last_modified_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  forecast_id INT,
  entry_text VARCHAR(255) NOT NULL,
  FOREIGN KEY (forecast_id) REFERENCES forecast(id)
);

-- Procedimiento almacenado CRUD Country 
DELIMITER //
CREATE PROCEDURE CreateCountry(IN p_code VARCHAR(10), IN p_name VARCHAR(100))
BEGIN
    INSERT INTO country (code, name) VALUES (p_code, p_name);
END //

CREATE PROCEDURE ReadAllCountries()
BEGIN
    SELECT * FROM country;
END //

CREATE PROCEDURE ReadCountryById(IN p_id INT)
BEGIN
    SELECT * FROM country WHERE id = p_id;
END //

CREATE PROCEDURE UpdateCountry(IN p_id INT, IN p_code VARCHAR(10), IN p_name VARCHAR(100))
BEGIN
    UPDATE country SET code = p_code, name = p_name WHERE id = p_id;
END //

CREATE PROCEDURE DeleteCountry(IN p_id INT)
BEGIN
    DELETE FROM country WHERE id = p_id;
END //
DELIMITER ;

-- Procedimiento almacenado CRUD State

DELIMITER //
CREATE PROCEDURE CreateState(IN p_code VARCHAR(10), IN p_name VARCHAR(100), IN p_country_id INT)
BEGIN
    INSERT INTO state (code, name, country_id) VALUES (p_code, p_name, p_country_id);
END //

CREATE PROCEDURE ReadAllStates()
BEGIN
    SELECT * FROM state;
END //

CREATE PROCEDURE ReadStateById(IN p_id INT)
BEGIN
    SELECT * FROM state WHERE id = p_id;
END //

CREATE PROCEDURE UpdateState(IN p_id INT, IN p_code VARCHAR(10), IN p_name VARCHAR(100), IN p_country_id INT)
BEGIN
    UPDATE state SET code = p_code, name = p_name, country_id = p_country_id WHERE id = p_id;
END //

CREATE PROCEDURE DeleteState(IN p_id INT)
BEGIN
    DELETE FROM state WHERE id = p_id;
END //
DELIMITER ;

-- Procedimiento almacenado CRUD city 

DELIMITER //
CREATE PROCEDURE CreateCity(IN p_code VARCHAR(10), IN p_name VARCHAR(100), IN p_zip_code VARCHAR(10), IN p_state_id INT)
BEGIN
    INSERT INTO city (code, name, zip_code, state_id) VALUES (p_code, p_name, p_zip_code, p_state_id);
END //

CREATE PROCEDURE ReadAllCities()
BEGIN
    SELECT * FROM city;
END //

CREATE PROCEDURE ReadCityById(IN p_id INT)
BEGIN
    SELECT * FROM city WHERE id = p_id;
END //

CREATE PROCEDURE UpdateCity(IN p_id INT, IN p_code VARCHAR(10), IN p_name VARCHAR(100), IN p_zip_code VARCHAR(10), IN p_state_id INT)
BEGIN
    UPDATE city SET code = p_code, name = p_name, zip_code = p_zip_code, state_id = p_state_id WHERE id = p_id;
END //

CREATE PROCEDURE DeleteCity(IN p_id INT)
BEGIN
    DELETE FROM city WHERE id = p_id;
END //
DELIMITER ;

-- Procedimiento almacenado CRUD forecast
DELIMITER //
CREATE PROCEDURE CreateForecast(IN p_city_id INT, IN p_temperature FLOAT, IN p_forecast_date DATE)
BEGIN
    INSERT INTO forecast (city_id, temperature, forecast_date) VALUES (p_city_id, p_temperature, p_forecast_date);
END //

CREATE PROCEDURE ReadAllForecasts()
BEGIN
    SELECT * FROM forecast;
END //

CREATE PROCEDURE ReadForecastById(IN p_id INT)
BEGIN
    SELECT * FROM forecast WHERE id = p_id;
END //

CREATE PROCEDURE UpdateForecast(IN p_id INT, IN p_city_id INT, IN p_temperature FLOAT, IN p_forecast_date DATE)
BEGIN
    UPDATE forecast SET city_id = p_city_id, temperature = p_temperature, forecast_date = p_forecast_date WHERE id = p_id;
END //

CREATE PROCEDURE DeleteForecast(IN p_id INT)
BEGIN
    DELETE FROM forecast WHERE id = p_id;
END //
DELIMITER ;


-- Crear los índices
CREATE INDEX idx_country_id ON state (country_id);
CREATE INDEX idx_state_id ON city (state_id);
CREATE INDEX idx_forecast_date ON forecast (forecast_date);
CREATE INDEX idx_zip_code ON forecast (zip_code);

-- Crear el usuario para la aplicación
CREATE USER 'weatherappuser'@'localhost' IDENTIFIED BY 'weatherapppass';
GRANT SELECT, INSERT, UPDATE, DELETE ON weather_service.* TO 'weatherappuser'@'localhost';


