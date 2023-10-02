INSERT INTO `ProyectoCaso2`.`Persona` (`cedula`, `tipo`, `nombre`, `fechaNacimiento`)
VALUES
    ('1234567890', 'Cliente', 'Juan Pérez', '1990-05-15'),
    ('2345678901', 'Cliente', 'María González', '1985-09-20'),
    ('3456789012', 'Cliente', 'Pedro Rodríguez', '1992-03-10'),
    ('4567890123', 'Cliente', 'Laura Martínez', '1988-07-05'),
    ('5678901234', 'Cliente', 'Carlos Sánchez', '1995-01-30'),
    ('6789012345', 'Cliente', 'Ana López', '1993-11-12'),
    ('7890123456', 'Cliente', 'Sofía Ramirez', '1997-04-25'),
    ('8901234567', 'Cliente', 'Luis Torres', '1991-12-01'),
    ('9012345678', 'Cliente', 'Elena Fernández', '1987-06-28'),
    ('0123456789', 'Cliente', 'Javier García', '1994-08-15');

INSERT INTO `ProyectoCaso2`.`Cuenta` (`monto`, `fechaVenc`, `CedulaPersona`)
VALUES
    (5000.00, '2023-12-31', '1234567890'),
    (7500.00, '2023-12-31', '2345678901'),
    (3000.00, '2023-12-31', '3456789012'),
    (4500.00, '2023-12-31', '4567890123'),
    (6000.00, '2023-12-31', '5678901234'),
    (8500.00, '2023-12-31', '6789012345'),
    (2000.00, '2023-12-31', '7890123456'),
    (3500.00, '2023-12-31', '8901234567'),
    (7000.00, '2023-12-31', '9012345678'),
    (5500.00, '2023-12-31', '0123456789');

INSERT INTO `ProyectoCaso2`.`Abono` (`cuota`, `fecha`, `monto`, `IDCuenta`)
VALUES
    (1, '2023-01-05', 500.00, 1),
    (2, '2023-02-10', 750.00, 1),
    (3, '2023-03-15', 300.00, 1),
    (4, '2023-04-20', 450.00, 1),
    (5, '2023-05-25', 600.00, 1),
    (6, '2023-06-30', 850.00, 1),
    (7, '2023-07-05', 200.00, 2),
    (8, '2023-08-10', 350.00, 2),
    (9, '2023-09-15', 700.00, 2),
    (10, '2023-10-20', 550.00, 2);

INSERT INTO `ProyectoCaso2`.`FormaPago` (`Nombre`, `IDAbono`)
VALUES
    ('Tarjeta de crédito', 1),
    ('Transferencia bancaria', 1),
    ('Efectivo', 1),
    ('Cheque', 1),
    ('Tarjeta de débito', 1),
    ('PayPal', 1),
    ('Depósito en efectivo', 1),
    ('Transferencia electrónica', 1),
    ('Giro postal', 2),
    ('Cheque de viajero', 2);
