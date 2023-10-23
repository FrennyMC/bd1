-- Crear la base de datos
CREATE DATABASE TiendaLibros;

-- Usar la base de datos
USE TiendaLibros;

-- Crear la tabla de Libros
CREATE TABLE Libros (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Titulo VARCHAR(255) NOT NULL,
    Autor VARCHAR(255) NOT NULL,
    Genero VARCHAR(50),
    Precio DECIMAL(10, 2) NOT NULL,
    Stock INT NOT NULL
);

-- Crear un índice en la columna de Título
CREATE INDEX idx_titulo ON Libros(Titulo);

-- Crear la tabla de Clientes
CREATE TABLE Clientes (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    CorreoElectronico VARCHAR(255) NOT NULL,
    IdentificadorUnico VARCHAR(10) NOT NULL
);

-- Crear un índice único en la columna de Correo Electrónico
CREATE UNIQUE INDEX idx_correo ON Clientes(CorreoElectronico);

-- Crear la tabla de Pedidos
CREATE TABLE Pedidos (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    cve_pedido INT NOT NULL,
    ClienteID INT NOT NULL,
    FechaPedido DATE NOT NULL,
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ID)
);

-- Crear un índice en la columna de ClienteID
CREATE INDEX idx_cliente_pedido ON Pedidos(ClienteID);

-- Crear la tabla de DetallesPedido
CREATE TABLE DetallesPedido (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    PedidoID INT NOT NULL,
    LibroID INT NOT NULL,
    Cantidad INT NOT NULL,
    FOREIGN KEY (PedidoID) REFERENCES Pedidos(ID),
    FOREIGN KEY (LibroID) REFERENCES Libros(ID)
);

-- Procedimiento Almacenado para Agregar Libros
DELIMITER //
CREATE PROCEDURE AgregarLibro(
    IN pTitulo VARCHAR(255),
    IN pAutor VARCHAR(255),
    IN pGenero VARCHAR(50),
    IN pPrecio DECIMAL(10, 2),
    IN pStock INT
)
BEGIN
    INSERT INTO Libros (Titulo, Autor, Genero, Precio, Stock)
    VALUES (pTitulo, pAutor, pGenero, pPrecio, pStock);
END//
DELIMITER ;

-- Procedimiento Almacenado para Realizar Pedidos
DELIMITER //
CREATE PROCEDURE RealizarPedido(
    IN pClienteID INT,
    IN pLibroID INT,
    IN pCantidad INT,
    IN pPedidoID INT
)
BEGIN
	 -- Verificar si hay suficiente stock del libro
     DECLARE StockDisponible INT;
    -- Verificar si se proporcionó un ID de pedido existente
    IF pPedidoID IS NULL THEN
        -- Insertar un nuevo pedido en la tabla de Pedidos
        INSERT INTO Pedidos (ClienteID, FechaPedido)
        VALUES (pClienteID, NOW());

        SET pPedidoID = LAST_INSERT_ID();
    END IF;

    SELECT Stock INTO StockDisponible
    FROM Libros
    WHERE ID = pLibroID;

    IF StockDisponible >= pCantidad THEN
        -- Insertar el detalle del pedido en la tabla DetallesPedido
        INSERT INTO DetallesPedido (PedidoID, LibroID, Cantidad)
        VALUES (pPedidoID, pLibroID, pCantidad);

        -- Actualizar el stock de libros
        UPDATE Libros
        SET Stock = Stock - pCantidad
        WHERE ID = pLibroID;

        SELECT 'Pedido realizado con éxito' AS Resultado;
    ELSE
        SELECT 'No hay suficiente stock del libro' AS Resultado;
    END IF;
END//
DELIMITER ;

-- Función para Calcular el Total del Pedido
DELIMITER //
CREATE FUNCTION CalcularTotalPedido(pPedidoID INT)
RETURNS DECIMAL(10, 2)
DETERMINISTIC
BEGIN
    DECLARE Total DECIMAL(10, 2);

    SELECT SUM(Libros.Precio * DetallesPedido.Cantidad) INTO Total
    FROM DetallesPedido
    INNER JOIN Libros ON DetallesPedido.LibroID = Libros.ID
    WHERE DetallesPedido.PedidoID = pPedidoID;

    RETURN Total;
END//
DELIMITER ;

-- Disparador para Actualizar el Stock de Libros
DELIMITER //
CREATE TRIGGER ActualizarStockDespuesDePedido
AFTER INSERT
ON DetallesPedido FOR EACH ROW
BEGIN
    -- Actualizar el stock de libros después de cada nuevo pedido
    UPDATE Libros
    SET Stock = Stock - NEW.Cantidad
    WHERE ID = NEW.LibroID;
END//
DELIMITER ;
