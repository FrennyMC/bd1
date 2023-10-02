INSERT INTO `Proyecto`.`Sucursal` (`numPatronal`, `nombre`, `direccion`) VALUES
('123456', 'Sucursal Principal', '123 Calle Principal, Ciudad'),
('789012', 'Sucursal Norte', '456 Avenida Norte, Ciudad'),
('345678', 'Sucursal Sur', '789 Avenida Sur, Ciudad'),
('901234', 'Sucursal Este', '123 Avenida Este, Ciudad'),
('567890', 'Sucursal Oeste', '456 Avenida Oeste, Ciudad'),
('234567', 'Sucursal Centro', '789 Calle Centro, Ciudad'),
('890123', 'Sucursal Suburbio', '123 Calle Suburbio, Ciudad'),
('456789', 'Sucursal Playa', '456 Calle Playa, Ciudad'),
('123789', 'Sucursal Montaña', '789 Avenida Montaña, Ciudad'),
('987654', 'Sucursal Campo', '123 Camino Campo, Ciudad');

INSERT INTO `Proyecto`.`Tienda` (`IDSucursal`, `direccion`, `nombre`) VALUES
(1, '456 Avenida Principal, Ciudad', 'Tienda A'),
(2, '789 Avenida Norte, Ciudad', 'Tienda B'),
(3, '123 Avenida Sur, Ciudad', 'Tienda C'),
(4, '456 Avenida Este, Ciudad', 'Tienda D'),
(5, '789 Avenida Oeste, Ciudad', 'Tienda E'),
(6, '123 Calle Centro, Ciudad', 'Tienda F'),
(7, '456 Calle Suburbio, Ciudad', 'Tienda G'),
(8, '789 Calle Playa, Ciudad', 'Tienda H'),
(9, '123 Avenida Montaña, Ciudad', 'Tienda I'),
(10, '456 Camino Campo, Ciudad', 'Tienda J');

INSERT INTO `Proyecto`.`Empleado` (`nombre`, `cedula`, `visitaDoctor`, `IDSucursal`, `IDTienda`) VALUES
('Juan Pérez', '1234567890', '2023-09-15', 1, 1),
('María Rodríguez', '9876543210', '2023-09-20', 2, 2),
('Carlos González', '5432167890', '2023-09-22', 3, 3),
('Ana Martínez', '0987654321', '2023-09-25', 4, 4),
('Luis Sánchez', '5678901234', '2023-09-28', 5, 5),
('Laura Jiménez', '2345678901', '2023-09-30', 6, 6),
('Pedro Torres', '8765432109', '2023-10-01', 7, 7),
('Sofía Ramírez', '3210987654', '2023-10-05', 8, 8),
('Jorge López', '0123456789', '2023-10-10', 9, 9),
('Isabel Castro', '6789012345', '2023-10-15', 10, 10);

INSERT INTO `Proyecto`.`Articulo` (`nombre`, `codigo`, `desc`, `tipo`, `stock`, `precioVenta`, `precioUnitario`, `dateEndSell`, `IDTienda`) VALUES
('Camiseta', 'C001', 'Camiseta de algodón', 'Ropa', 100, 19.99, 9.99, '2023-12-31', 1),
('Zapatos', 'Z002', 'Zapatos deportivos', 'Calzado', 50, 49.99, 29.99, '2023-12-31', 2),
('Televisor', 'T003', 'Televisor LED de 55 pulgadas', 'Electrónica', 20, 499.99, 399.99, '2023-12-31', 3),
('Refrigeradora', 'R004', 'Refrigeradora de acero inoxidable', 'Electrodoméstico', 15, 699.99, 599.99, '2023-12-31', 4),
('Laptop', 'L005', 'Laptop de última generación', 'Informática', 30, 899.99, 799.99, '2023-12-31', 5),
('Libro', 'L006', 'Novela de ciencia ficción', 'Libros', 75, 12.99, 7.99, '2023-12-31', 6),
('Cámara', 'C007', 'Cámara digital de alta resolución', 'Electrónica', 10, 299.99, 199.99, '2023-12-31', 7),
('Micrófono', 'M008', 'Micrófono de condensador profesional', 'Audio', 25, 79.99, 59.99, '2023-12-31', 8),
('Mesa', 'M009', 'Mesa de comedor de madera', 'Muebles', 40, 199.99, 149.99, '2023-12-31', 9),
('Sofá', 'S010', 'Sofá de cuero de 3 plazas', 'Muebles', 12, 499.99, 399.99, '2023-12-31', 10);


INSERT INTO `Proyecto`.`Proveedor` (`nombre`, `cedula`, `tipo`, `telefono`, `direccion`, `contacto`) VALUES
('Proveedor XYZ', '1234567890', 'Distribuidora', '123-456-7890', 'Calle Principal 123, Ciudad A', 'John Doe'),
('Suministros ABC', '9876543210', 'Mayorista', '987-654-3210', 'Avenida Norte 456, Ciudad B', 'Jane Smith'),
('Electrónica Tech', '5432167890', 'Mayorista', '543-216-7890', 'Calle Sur 789, Ciudad C', 'David Johnson'),
('Muebles Express', '0987654321', 'Fabricante', '098-765-4321', 'Avenida Este 123, Ciudad D', 'Emily Brown'),
('Ropa Moda', '5678901234', 'Distribuidora', '567-890-1234', 'Avenida Oeste 456, Ciudad E', 'Robert Wilson'),
('Electrohogar', '2345678901', 'Mayorista', '234-567-8901', 'Calle Centro 789, Ciudad F', 'Sara Miller'),
('Tecnología Plus', '8765432109', 'Mayorista', '876-543-2109', 'Calle Suburbio 123, Ciudad G', 'Michael Davis'),
('Muebles Elegantes', '3210987654', 'Fabricante', '321-098-7654', 'Calle Playa 456, Ciudad H', 'Olivia White'),
('Juguetes Divertidos', '0123456789', 'Distribuidora', '012-345-6789', 'Avenida Montaña 789, Ciudad I', 'William Clark'),
('Electrónica Avanzada', '6789012345', 'Mayorista', '678-901-2345', 'Camino Campo 123, Ciudad J', 'Laura Lee');


INSERT INTO `Proyecto`.`Orden` (`numOrden`, `fechaOrden`, `usuarioComprador`, `usuarioAproboOrden`, `montoTotal`, `IDTienda`, `IDProveedor`) VALUES
(12345, '2023-09-15', 'María López', 'Carlos Rodríguez', 1500.00, 1, 1),
(12346, '2023-09-16', 'Juan Pérez', 'Ana García', 2200.00, 2, 2),
(12347, '2023-09-17', 'Laura Martínez', 'Pedro Sánchez', 1800.00, 3, 3),
(12348, '2023-09-18', 'Carlos González', 'Sofía Ramírez', 2700.00, 4, 4),
(12349, '2023-09-19', 'Luis Soto', 'Isabel Castro', 3200.00, 5, 5),
(12350, '2023-09-20', 'Ana Rodríguez', 'David Smith', 2100.00, 6, 6),
(12351, '2023-09-21', 'Sofía Fernández', 'Jorge López', 1900.00, 7, 7),
(12352, '2023-09-22', 'Javier Torres', 'Olivia White', 2800.00, 8, 8),
(12353, '2023-09-23', 'Elena López', 'Samuel Davis', 2400.00, 9, 9),
(12354, '2023-09-24', 'Pedro García', 'Ana Martínez', 3300.00, 10, 10);


INSERT INTO `Proyecto`.`Detalle` (`IDOrden`, `IDArticulo`, `cantidad`, `precio`) VALUES
(1, 1, 5, 99.99),
(1, 2, 3, 49.99),
(1, 3, 2, 199.99),
(1, 4, 1, 699.99),
(1, 5, 2, 899.99),
(1, 6, 10, 12.99),
(1, 7, 3, 299.99),
(1, 8, 5, 79.99),
(1, 9, 2, 199.99),
(2, 10, 1, 499.99);
