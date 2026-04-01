-- Se crea la base de datos
CREATE DATABASE ejercisioJOIN;

-- se para en la base de datos
USE ejercisioJOIN;


-- Crear tabla clientes
CREATE TABLE clientes (
    id_cliente INT PRIMARY KEY,
    nombre VARCHAR(50),
    email VARCHAR(100)
);

-- Crear tabla pedidos
CREATE TABLE pedidos (
    id_pedido INT PRIMARY KEY,
    id_cliente INT,
    producto VARCHAR(50),
    cantidad INT,
    fecha_pedido DATE
);

-- Insertar datos en la tabla clientes
INSERT INTO clientes (id_cliente, nombre, email) VALUES
(1, 'Juan Pérez', 'juan@email.com'),
(2, 'María López', 'maria@email.com'),
(3, 'Carlos Ruiz', 'carlos@email.com'),
(4, 'Ana Martínez', 'ana@email.com');

-- Insertar datos en la tabla pedidos
INSERT INTO pedidos (id_pedido, id_cliente, producto, cantidad, fecha_pedido) VALUES
(101, 1, 'Laptop', 1, '2024-03-15'),
(102, 1, 'Mouse', 2, '2024-03-16'),
(103, 2, 'Teclado', 1, '2024-03-17'),
(104, 3, 'Monitor', 1, '2024-03-18'),
(105, 2, 'Impresora', 1, '2024-03-19'),
(106, NULL, 'Disco Duro', 1, '2024-03-20');

-- Mostrar todos los clientes y sus pedidos,
-- incluyendo a los clientes que no han realizado pedidos.

SELECT c.nombre, p.producto
FROM clientes c
LEFT JOIN pedidos p ON c.id_cliente = p.id_cliente;

-- Listar todos los pedidos con los nombres de los clientes, 
-- incluyendo los pedidos que no tienen cliente asignado.
SELECT p.producto,c.nombre
FROM pedidos p
LEFT JOIN clientes c ON c.id_cliente = p.id_cliente;

-- Encontrar los clientes que no han realizado ningún pedido.
SELECT c.nombre
FROM clientes c
LEFT JOIN pedidos p ON c.id_cliente = p.id_cliente
WHERE p.id_pedido IS NULL;

-- Mostrar el nombre del cliente que ha realizado más pedidos.
SELECT c.nombre
FROM clientes -- se hizo una investigacion para hacer esta consulta, por que no sabia como hacerla.
JOIN pedidos USING (id_cliente)
GROUP BY c.nombre
ORDER BY COUNT(*) DESC
LIMIT 1;

-- Listar los productos que han sido pedidos más de una vez, junto con el nombre del cliente que hizo cada pedido.
SELECT c.nombre, p.producto
FROM pedidos p -- para esta consulta se investiga sobre como solucionarla en una IA
JOIN clientes c ON c.id_cliente = p.id_cliente
GROUP BY c.nombre, p.producto
HAVING COUNT(*) > 1;



-- ( __________ TRABAJO EXTRA! __________ )


-- 1. Mostrar clientes y sus pedidos
SELECT c.nombre,p.producto,fecha_pedido
FROM clientes c
INNER JOIN pedidos p ON c.id_cliente = p.id_cliente;

-- 2. Listar todos los clientes y sus pedidos, incluyendo los que no han realizado pedidos
SELECT c.nombre,p.producto,fecha_pedido
FROM clientes c
LEFT JOIN pedidos p ON c.id_cliente = p.id_cliente;

 -- 3. Mostrar todos los pedidos y los clientes asociados, incluyendo pedidos sin cliente
 
SELECT  c.nombre,p.producto, fecha_pedido
FROM pedidos p
LEFT JOIN clientes c ON p.id_cliente = c.id_cliente;

-- 4. Listar todos los clientes y pedidos, incluyendo los sin relación
SELECT c.nombre, p.producto, p.fecha_pedido
FROM clientes c
LEFT JOIN pedidos p ON c.id_cliente = p.id_cliente
UNION
SELECT c.nombre, p.producto, p.fecha_pedido
FROM clientes c
RIGHT JOIN pedidos p ON c.id_cliente = p.id_cliente
WHERE c.id_cliente IS NULL;

-- 5. Generar todas las combinaciones posibles entre clientes y productos
SELECT c.nombre, p.producto
FROM clientes c
CROSS JOIN pedidos p;

-- 6. Mostrar clientes que han realizado más de un pedido
SELECT c.nombre, COUNT(p.id_pedido) AS total_pedidos
FROM clientes c
INNER JOIN pedidos p ON c.id_cliente = p.id_cliente
GROUP BY c.id_cliente, c.nombre
HAVING COUNT(p.id_pedido) > 1;

-- 7. Listar clientes y la cantidad total de productos pedidos
SELECT c.nombre, SUM(p.cantidad) AS total_productos
FROM clientes c
INNER JOIN pedidos p ON c.id_cliente = p.id_cliente
GROUP BY c.id_cliente, c.nombre;

-- 8. Mostrar productos pedidos y clientes asociados, incluyendo productos sin cliente
SELECT p.producto, p.cantidad, c.nombre
FROM pedidos p
LEFT JOIN clientes c ON p.id_cliente = c.id_cliente;

-- 9. Encontrar clientes cuyo nombre empiece con 'J'
SELECT * FROM clientes
WHERE nombre LIKE 'J%';

-- 10. Mostrar nombres de clientes en orden inverso
SELECT nombre FROM clientes
ORDER BY nombre DESC;

-- 11. Eliminar espacios en blanco al inicio y final de los correos / emails
SELECT id_cliente, nombre, TRIM(email) AS email_limpio
FROM clientes;

-- 12. Convertir nombres de productos a mayúsculas
SELECT id_pedido, UPPER(producto) AS producto_mayuscula, cantidad
FROM pedidos;
 ==
-- 13. Contar pedidos por cliente
SELECT c.nombre, COUNT(p.id_pedido) AS total_pedidos
FROM clientes c
LEFT JOIN pedidos p ON c.id_cliente = p.id_cliente
GROUP BY c.id_cliente, c.nombre;

-- 14. Mostrar clientes que no han realizado pedidos
SELECT c.nombre
FROM clientes c
LEFT JOIN pedidos p ON c.id_cliente = p.id_cliente
WHERE p.id_pedido IS NULL;

-- 15. Listar productos pedidos más de una vez
SELECT producto, SUM(cantidad) AS total_pedido
FROM pedidos
GROUP BY producto
HAVING SUM(cantidad) > 1;

-- 16. Mostrar pedidos realizados en marzo de 2024
SELECT * FROM pedidos
WHERE fecha_pedido BETWEEN '2024-03-01' AND '2024-03-31';

-- 17. Encontrar clientes con correo / email que son del proveedor: 'email.com'
SELECT nombre, email
FROM clientes
WHERE email LIKE '%@email.com';

-- 18. Mostrar productos y la cantidad total pedida
SELECT producto, SUM(cantidad) AS cantidad_total
FROM pedidos
GROUP BY producto;

-- 19. Listar clientes y su pedido más reciente
SELECT c.nombre, MAX(p.fecha_pedido) AS ultimo_pedido
FROM clientes c
INNER JOIN pedidos p ON c.id_cliente = p.id_cliente
GROUP BY c.id_cliente, c.nombre;

-- 20. Mostrar pedidos sin cliente asociado
SELECT p.id_pedido, p.producto, p.fecha_pedido
FROM pedidos p
LEFT JOIN clientes c ON p.id_cliente = c.id_cliente
WHERE c.id_cliente IS NULL;