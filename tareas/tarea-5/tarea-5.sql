-- Crear la base de datos tec_movies
CREATE DATABASE IF NOT EXISTS tec_movies;
USE tec_movies;

-- Crear la tabla customer
CREATE TABLE IF NOT EXISTS customer (
    id INT PRIMARY KEY AUTO_INCREMENT, -- cedula
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    cell_phone VARCHAR(15) NOT NULL,
    address VARCHAR(200) DEFAULT 'N/A'
);

-- Crear la tabla movie_category
CREATE TABLE IF NOT EXISTS movie_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(30) NOT NULL
);

-- Crear la tabla movie
CREATE TABLE IF NOT EXISTS movie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    release_date TIMESTAMP NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT category_id_positive_value CHECK (category_id > 0),
    CONSTRAINT movie_category_id_fk FOREIGN KEY (category_id) REFERENCES movie_category (id)
);

-- Crear la tabla rental
CREATE TABLE IF NOT EXISTS rental (
    id INT NOT NULL AUTO_INCREMENT UNIQUE,
    customer_id INT NOT NULL,
    movie_id INT NOT NULL UNIQUE,
    rental_date TIMESTAMP NOT NULL,
    return_date TIMESTAMP NOT NULL,
    PRIMARY KEY (customer_id, movie_id),
    CONSTRAINT customer_id_positive_value CHECK (customer_id > 0),
    CONSTRAINT movie_id_positive_value CHECK (movie_id > 0),
    CONSTRAINT rental_customer_id_fk FOREIGN KEY (customer_id) REFERENCES customer (id),
    CONSTRAINT rental_movie_id_fk FOREIGN KEY (movie_id) REFERENCES movie (id)
);

-- Consultas:

-- 1. Mostrar la Cédula, Nombre, Apellido y Teléfono Celular de los clientes que han pedido prestada una película al video, no importa si el préstamo está activo o no.
SELECT 
    c.id, c.first_name, c.last_name, c.cell_phone, r.movie_id, m.title
FROM 
    customer c
INNER JOIN rental r ON c.id = r.customer_id
LEFT JOIN movie m ON r.movie_id = m.id;

-- 2. Mostrar la cantidad de préstamos activos por cédula.
SELECT 
    c.id, 
    r.rental_date,
    m.title,
    DATEDIFF(NOW(), r.rental_date) AS days_in_rental
FROM 
    rental AS r
INNER JOIN customer c ON r.customer_id = c.id
INNER JOIN movie m ON r.movie_id = m.id
WHERE 
    DATEDIFF(NOW(), r.rental_date) <= 3;

-- 3. Mostrar la cantidad de préstamos inactivos existentes por cédula.
SELECT 
    c.id, 
    r.rental_date,
    m.title,
    DATEDIFF(NOW(), r.rental_date) AS days_in_rental
FROM 
    rental AS r
INNER JOIN customer c ON r.customer_id = c.id
INNER JOIN movie m ON r.movie_id = m.id
WHERE 
    DATEDIFF(NOW(), r.rental_date) > 3;

-- 4. Mostrar el total de préstamos inactivos existentes.
SELECT 
    COUNT(*)
FROM 
    rental r
WHERE
    DATEDIFF(NOW(), r.rental_date) > 3;

-- 5. Mostrar a todos aquellos clientes que nunca han realizado un préstamo.
SELECT 
    c.id, 
    c.first_name, 
    c.last_name, 
    c.cell_phone, 
    r.id, 
    r.movie_id
FROM 
    customer c 
LEFT JOIN rental r ON c.id = r.customer_id 
WHERE r.id IS NULL;

-- 6. Actualizar el campo Dirección y poner Guanacaste, del cliente con cédula 10.
UPDATE customer
SET address = 'Guanacaste'
WHERE id = 10;

-- 7. Mostrar la cédula, nombre, apellido de los clientes que tienen entre 1 y 3 préstamos activos.
SELECT
    c.id,
    CONCAT(c.first_name, ' ', c.last_name) AS customer,
    COUNT(*) AS number_of_rentals
FROM 
    customer c
INNER JOIN rental r ON c.id = r.customer_id
GROUP BY 
    r.customer_id
HAVING
    number_of_rentals >= 1 AND number_of_rentals <= 3;

-- 8. Listar todas las películas de la categoría “Comedia”.
SELECT
    m.title
FROM 
    movie AS m
INNER JOIN movie_category AS c ON m.category_id = c.id 
WHERE c.category_name = 'Comedy';

-- 9. Listar todas las películas prestadas de la categoría “Comedia”.
SELECT
    m.title
FROM 
    rental AS r
INNER JOIN movie m ON r.movie_id = m.id 
INNER JOIN movie_category AS c ON m.category_id = c.id 
WHERE c.category_name = 'Comedy';
