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
SELECT * FROM productos WHERE  ubicacion = 'pasillo 2'

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









