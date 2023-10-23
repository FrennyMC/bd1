Objetivo
Crear una base de datos e implementar procedimientos almacenados, funciones y triggers
en esta.
Caso de Uso: Sistema de Gestión de una Tienda de Libros
Descripción:
Se le ha encargado el diseño de una base de datos para una tienda de libros en línea. La
tienda necesita llevar un registro de los libros disponibles, los clientes que compran libros y
los pedidos realizados. Su tarea será diseñar una base de datos simple para gestionar esta
tienda de libros.
Requisitos:
Tabla de Libros:
1. Mantener un registro de todos los libros disponibles para la venta.
2. Cada libro debe tener un título, autor, género, precio y stock disponible.
3. Debe existir un índice en la columna de título del libro.
Tabla de Clientes:
1. Registrar información sobre los clientes que realizan compras.
2. Cada cliente debe tener un nombre, dirección de correo electrónico y un identificador
único.
3. Debe existir un índice en la columna de correo electrónico del cliente.
Tabla de Pedidos:
1. Registrar todos los pedidos realizados por los clientes.
2. Cada pedido debe tener un identificador único, el cliente que realizó el pedido y la
fecha del pedido.
3. Debe existir un índice en la columna del cliente que realizó el pedido.
Procedimientos Almacenados:
Procedimiento Almacenado para Agregar Libros:
Crear un procedimiento almacenado que permita agregar nuevos libros a la base de datos
de libros.
Procedimiento Almacenado para Realizar Pedidos:
Crea un procedimiento almacenado que permita a un cliente realizar un pedido. Esto debe
disminuir el stock disponible de libros en la tabla de libros.
Función:
Función para Calcular el Total del Pedido:
Crear una función que acepte un identificador de pedido y calcule el total del pedido
sumando los precios de los libros solicitados en ese pedido.
Disparador (Trigger):
Disparador para Actualizar el Stock de Libros:
Crea un disparador que se activa cuando se registra un nuevo pedido. Este disparador debe
actualizar el stock disponible de libros en la tabla de libros para reflejar la cantidad vendida
en el pedido
