USE tallerrepaso;

-- Tabla productos
CREATE TABLE productos (
 id_producto INT PRIMARY KEY AUTO_INCREMENT,
 nombre VARCHAR(255) NOT NULL,
 precio DECIMAL(10,2) NOT NULL,
 tipo VARCHAR(50) NOT NULL,
 ubicacion VARCHAR(50) DEFAULT 'Productos_nuevos'
);

-- Tabla clientes
CREATE TABLE clientes (
 id_cliente INT PRIMARY KEY AUTO_INCREMENT,
 nombre VARCHAR(255) NOT NULL,
 fecha_nacimiento DATE NOT NULL
);

CREATE TABLE ventas (
 id_venta INT PRIMARY KEY AUTO_INCREMENT,
 id_producto INT NOT NULL,
 id_cliente INT NOT NULL,
 cantidad INT NOT NULL,
 fecha_venta DATE NOT NULL,
 precio_total DECIMAL(10,2) NOT NULL, FOREIGN KEY (id_producto) REFERENCES productos(id_producto), 
 FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)
);

INSERT INTO productos (nombre, precio, tipo, ubicacion) VALUES
 ('Leche Deslactosada Alpina', 2500, 'Lácteos', 'Pasillo 1'),
 ('Café Molido Tostao', 8500, 'Abarrotes', 'Pasillo 2'),
 ('Arroz Diana Familiar', 5200, 'Cereales', 'Pasillo 3'),
 ('Huevos Criollos x30', 12000, 'Lácteos', 'Pasillo 1'),
 ('Pollo Entero Avestruz', 12000, 'Carnes', 'Mostrador 2'),
 ('Manzanas Rojas Importadas', 4800, 'Frutas', 'Pasillo 3'),
 ('Tomates Pera Frescos', 2300, 'Verduras', 'Pasillo 4'),
 ('Pasta Fusilli La Muchetti', 3500, 'Cereales', 'Pasillo 3'),
 ('Aceite Vegetal Canola', 7200, 'Abarrotes', 'Pasillo 2'),
 ('Galletas Saltinas Noel', 2800, 'Abarrotes', 'Pasillo 2'),
 ('Atún enlatado Calvo', 5500, 'Conservas', 'Pasillo 5'),
 ('Jabón en Polvo Ariel', 14500, 'Limpieza', NULL), -- Sin ubicación específica
 ('Detergente Líquido Lavaloza Ala', 8900, 'Limpieza',NULL), -- Sin ubicación específica
 ('Papel Higiénico Familia x4', 9800, 'Limpieza', 'Pasillo 5'),
 ('Toallas de Papel Scott x3', 5600, 'Limpieza',NULL), -- Sin ubicación específica
 ('Limpiador Multiusos Fabuloso', 4200, 'Limpieza',NULL), -- Sin ubicación específica
 ('Shampoo Sedal Hidratante', 11000, 'Cuidado Personal', 'Pasillo 7'),
 ('Crema Dental Colgate Total', 6300, 'Cuidado Personal', 'Pasillo 7'),
 ('Desodorante Rexona Invisible', 8700, 'Cuidado Personal', 'Pasillo 7'),
 ('Jabón Corporal Dove', 7500, 'Cuidado Personal', 'Pasillo 7');
SELECT * from productos


INSERT INTO clientes (nombre, fecha_nacimiento) VALUES
 ('Maria Pérez', '1985-01-01'),
 ('Juan López', '1992-07-14'),
 ('Ana González', '2000-12-25'),
 ('Pedro García', '1970-10-04'),
 ('Sofia Martínez', '1988-03-08');

INSERT INTO ventas (id_producto, id_cliente, cantidad, fecha_venta, precio_total)
VALUES
(11,2,1,'2026-03-08',16500),
(7,3,3,'2026-03-06',2300),
(10,1,2,'2026-03-08',5600),
(13,4,4,'2026-03-05',17800),
(8,2,3,'2026-03-05',14000),
(12,3,4,'2026-03-04',72500),
(15,2,5,'2026-03-08',11200),
(5,4,5,'2026-03-08',60000),
(19,1,5,'2026-03-08',17400),
(7,4,2,'2026-03-04',2300),
(19,5,1,'2026-03-08',34800),
(13,2,4,'2026-03-08',8900),
(4,1,2,'2026-03-06',24000),
(14,5,1,'2026-03-07',49000),
(17,3,4,'2026-03-07',44000),
(15,1,5,'2026-03-07',11200),
(10,5,1,'2026-03-04',5600),
(2,5,2,'2026-03-06',34000),
(18,4,3,'2026-03-07',31500),
(1,1,4,'2026-03-05',2500),
(13,1,2,'2026-03-05',17800),
(5,1,1,'2026-03-04',24000),
(15,4,4,'2026-03-04',11200),
(12,2,2,'2026-03-08',29000),
(3,4,3,'2026-03-05',10400),
(19,3,4,'2026-03-06',34800),
(6,1,3,'2026-03-04',19200),
(17,4,1,'2026-03-08',11000),
(2,3,5,'2026-03-08',25500),
(16,1,2,'2026-03-07',16800),
(19,2,1,'2026-03-05',43500),
(12,4,3,'2026-03-07',43500),
(7,5,2,'2026-03-07',4600),
(20,3,5,'2026-03-04',37500),
(5,2,4,'2026-03-07',36000),
(17,5,2,'2026-03-08',22000),
(9,2,4,'2026-03-07',14400),
(11,1,4,'2026-03-05',27500),
(8,4,4,'2026-03-07',10500),
(2,2,5,'2026-03-08',8500);
SELECT * FROM ventas

-- Listar todos los productos, ordenados alfabéticamente por nombre.
SELECT * FROM productos ORDER BY nombre ASC;

-- Mostrar los productos que tienen un precio superior a $10.000
SELECT * FROM productos WHERE precio > 10000;

-- Obtener los productos que pertenecen a la categoría "Lácteos".
SELECT * FROM productos WHERE tipo = 'Lácteos';

-- Listar los productos que se encuentran en el pasillo 2.
SELECT * FROM productos WHERE  ubicacion = 'Pasillo 2'

-- Mostrar los 5 primeros productos de la lista.
SELECT * FROM productos WHERE id_producto BETWEEN 1 AND 5

-- Consultar los productos que tienen un nombre que comienza con la letra "A".
SELECT * FROM productos  WHERE nombre LIKE 'A%';

-- Buscar los productos que contienen la palabra "Arroz" en su nombre
SELECT * FROM productos WHERE nombre LIKE '%Arroz%'

-- Obtener los productos que no tienen una ubicación específica.
SELECT * FROM productos WHERE ubicacion IS NULL;

-- Listar los clientes ordenados por fecha de nacimiento de menor a mayor.
SELECT * FROM clientes ORDER BY fecha_nacimiento ASC;

-- Mostrar los clientes que nacieron en el año 1990 o posterior.
SELECT * FROM clientes WHERE YEAR(fecha_nacimiento) >= 1990;


-- Obtener el número de clientes que nacieron en cada año.

-- Clientes nacidos en 1970
SELECT nombre, fecha_nacimiento
FROM clientes
WHERE fecha_nacimiento BETWEEN '1970-01-01' AND '1970-12-31';

-- Clientes nacidos en 1985
SELECT nombre, fecha_nacimiento
FROM clientes
WHERE fecha_nacimiento BETWEEN '1985-01-01' AND '1985-12-31';

-- Clientes nacidos en 1988
SELECT nombre, fecha_nacimiento
FROM clientes
WHERE fecha_nacimiento BETWEEN '1988-01-01' AND '1988-12-31';

-- Clientes nacidos en 1992
SELECT nombre, fecha_nacimiento
FROM clientes
WHERE fecha_nacimiento BETWEEN '1992-01-01' AND '1992-12-31';

-- Clientes nacidos en 2000
SELECT nombre, fecha_nacimiento
FROM clientes
WHERE fecha_nacimiento BETWEEN '2000-01-01' AND '2000-12-31';

-- Listar las ventas realizadas en el mes de marzo del año 2026
SELECT * FROM ventas WHERE fecha_venta BETWEEN '2026-03-01' AND '2026-03-31';

-- Mostrar las ventas realizadas por cada cliente, ordenadas por fecha de venta de menor a mayor.
select c.nombre, v.fecha_venta, v.cantidad, v.precio_total
from ventas v
join clientes c on v.id_cliente = c.id_cliente
order by v.fecha_venta ASC;

-- Obtener el total de ventas realizadas por cada cliente.
SELECT c.nombre, SUM(v.precio_total) AS total_ventas
FROM ventas v
JOIN clientes c ON v.id_cliente = c.id_cliente
GROUP BY c.nombre;

-- Listar las ventas que superan los $20.000.
SELECT * FROM ventas WHERE precio_total > 20000;

-- Mostrar el producto más vendido durante marzo 2026.
SELECT p.nombre, SUM(v.cantidad) AS total_vendido
FROM ventas v
JOIN productos p ON v.id_producto = p.id_producto
WHERE v.fecha_venta BETWEEN '2026-03-01' AND '2026-03-31'
GROUP BY p.nombre
ORDER BY total_vendido DESC
LIMIT 1;

-- Obtener el total de ventas de cada tipo de producto.
SELECT p.tipo, SUM(v.precio_total) AS total_ventas
FROM ventas v
JOIN productos p ON v.id_producto = p.id_producto
GROUP BY p.tipo
ORDER BY total_ventas DESC;

-- Listar los productos que NO se han vendido durante marzo 2026.
SELECT p.nombre
FROM productos p
WHERE p.id_producto NOT IN (
    SELECT DISTINCT v.id_producto
    FROM ventas v
    WHERE v.fecha_venta BETWEEN '2026-03-01' AND '2026-03-31'
);

-- Mostrar el cliente que ha realizado la mayor compra en total.
SELECT c.nombre, SUM(v.precio_total) AS total_compras
FROM ventas v
JOIN clientes c ON v.id_cliente = c.id_cliente
GROUP BY c.nombre
ORDER BY total_compras DESC
LIMIT 1;

-- Obtener el promedio de venta por cada día de marzo 2026.
SELECT fecha_venta, AVG(precio_total) AS promedio_diario
FROM ventas
WHERE fecha_venta BETWEEN '2026-03-01' AND '2026-03-31'
GROUP BY fecha_venta
ORDER BY fecha_venta;

-- Listar los productos cuyo nombre tiene una longitud mayor a 15 caracteres.
SELECT nombre, LENGTH(nombre) AS longitud
FROM productos
WHERE LENGTH(nombre) > 15;

-- Nombre en mayúsculas, minúsculas y con primera letra en mayúscula.
SELECT 
    nombre,
    UPPER(nombre)   AS en_mayusculas,
    LOWER(nombre)   AS en_minusculas,
    CONCAT(UPPER(LEFT(nombre, 1)), LOWER(SUBSTRING(nombre, 2))) AS primera_mayuscula
FROM productos;

-- Eliminar espacios en blanco al inicio y final del nombre.
SELECT TRIM(nombre) AS nombre_sin_espacios
FROM productos;

-- Reemplazar "Pasillo" por "Estante" en la ubicación.
SELECT nombre, REPLACE(ubicacion, 'Pasillo', 'Estante') AS ubicacion_nueva
FROM productos;

-- Invertir el orden de los caracteres del nombre.
SELECT nombre, REVERSE(nombre) AS nombre_invertido
FROM productos;

-- Extraer los primeros 3 caracteres del nombre.
SELECT nombre, LEFT(nombre, 3) AS primeros_3
FROM productos;

-- Obtener los últimos 4 caracteres del nombre.
SELECT nombre, RIGHT(nombre, 4) AS ultimos_4
FROM productos;

-- Concatenar nombre y precio separados por un guión.
SELECT CONCAT(nombre, ' - ', precio) AS nombre_precio
FROM productos;

-- Concatenar nombre, tipo y ubicación separados por comas
-- con separador de miles (puntos) en el precio.
SELECT CONCAT(
    nombre, ', ',
    tipo, ', ',
    COALESCE(ubicacion, 'Sin ubicación')
) AS detalle_producto
FROM productos;

-- Buscar si la cadena "Café" se encuentra en cualquier parte del nombre.
SELECT nombre
FROM productos
WHERE nombre LIKE '%Café%';


-- -------------------------- Trabajo de funciones de agrupamiento --------------------------


-- ¿Cuántas ventas se han realizado por cada cliente?
SELECT c.nombre, COUNT(v.id_venta) AS total_ventas
FROM clientes c
JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.nombre;

-- ¿Cuántas ventas se han realizado por cada producto?
SELECT p.nombre, COUNT(v.id_venta) AS total_ventas
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.nombre;

-- ¿Cuál es el total vendido por cada cliente?
SELECT c.nombre, SUM(v.precio_total) AS total_compras
FROM clientes c
JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.nombre;

-- ¿Cuál es el total vendido por cada producto?
SELECT p.nombre, SUM(v.precio_total) AS total_vendido
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.nombre;

-- ¿Cuál es el promedio de gasto por cliente?
SELECT c.nombre, AVG(v.precio_total) AS promedio_gasto
FROM clientes c
JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.nombre;

-- ¿Cuál es el producto más caro vendido?
SELECT p.nombre, MAX(v.precio_total) AS venta_maxima
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.nombre;


-- ¿Cuál es el producto más barato vendido?
SELECT p.nombre, MIN(v.precio_total) AS venta_minima
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.nombre;


-- ¿Cuántas ventas se hicieron por tipo de producto? 
SELECT p.tipo, COUNT(v.id_venta) AS total_ventas
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.tipo;

-- ¿Cuál es el total vendido por tipo de producto?

SELECT p.tipo, SUM(v.precio_total) AS total_vendido
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.tipo;

-- ¿Cuál es el promedio de ventas por tipo de producto?

SELECT p.tipo, AVG(v.precio_total) AS promedio_ventas
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.tipo;

-- ¿Cuántas ventas se hicieron por ubicación del producto?
SELECT p.ubicacion, COUNT(v.id_venta) AS total_ventas
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.ubicacion;

-- ¿Cuál es el total vendido por ubicación del producto?
SELECT p.ubicacion, SUM(v.precio_total) AS total_vendido
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.ubicacion;


-- ¿Cuántos productos distintos ha comprado cada cliente?
SELECT c.nombre, COUNT(DISTINCT v.id_producto) AS productos_distintos
FROM clientes c
JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.nombre;


-- ¿Cuál es el cliente que más ha gastado?
SELECT c.nombre, SUM(v.precio_total) AS total_gastado
FROM clientes c
JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.nombre
ORDER BY total_gastado DESC
LIMIT 1; 

-- ¿Cuál es el producto más vendido en cantidad?
SELECT p.nombre, SUM(v.cantidad) AS cantidad_total
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.nombre
ORDER BY cantidad_total DESC
LIMIT 1;

-- ¿Cuál es el mes con más ventas?
SELECT MONTH(v.fecha_venta) AS mes, COUNT(*) AS total_ventas
FROM ventas v
GROUP BY MONTH(v.fecha_venta)
ORDER BY total_ventas DESC
LIMIT 1;

-- ¿Cuál es el año con más ventas?

SELECT YEAR(v.fecha_venta) AS año, COUNT(*) AS total_ventas
FROM ventas v
GROUP BY YEAR(v.fecha_venta)
ORDER BY total_ventas DESC
LIMIT 1; -- Limit muestra el numero de filas que se quieran, este caso la primera

-- ¿Cuál es el promedio de cantidad comprada por cliente?

SELECT c.nombre, AVG(v.cantidad) AS promedio_cantidad
FROM clientes c
JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.nombre;

-- ¿Cuál es el producto con mayor ingreso total?
SELECT p.nombre, SUM(v.precio_total) AS ingreso_total
FROM productos p
JOIN ventas v ON p.id_producto = v.id_producto
GROUP BY p.nombre
ORDER BY ingreso_total DESC
LIMIT 1; -- Limit muestra el numero de filas que se quieran, este caso la primera

-- ¿Qué clientes han comprado más de 3 productos distintos?
SELECT c.nombre, COUNT(DISTINCT v.id_producto) AS productos_distintos
FROM clientes c
JOIN ventas v ON c.id_cliente = v.id_cliente
GROUP BY c.nombre
HAVING productos_distintos > 3;