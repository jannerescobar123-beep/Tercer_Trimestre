
CREATE TABLE libros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    precio DECIMAL(8,2),
    cantidad INT);

INSERT INTO libros (titulo, precio, cantidad) VALUES
('Cien Años de Soledad', 45000.00, 12),
('Don Quijote de la Mancha', 52000.50, 8),
('El Principito', 30000.00, 20),
('La Odisea', 41000.75, 5),
('Crimen y Castigo', 47000.00, 7),
('Orgullo y Prejuicio', 38000.90, 15),
('La Divina Comedia', 60000.00, 4),
('El Hobbit', 55000.00, 10),
('Harry Potter y la Piedra Filosofal', 65000.00, 18),
('El Señor de los Anillos', 90000.00, 6);

-- multiplicación de valor unitario por cantidad
select titulo, precio,cantidad,(precio*cantidad) as Total from libros;

-- Mostrar el precio con IVA (19%) incluido
SELECT titulo,precio,(precio*0.19) AS precioConIVA FROM libros;

-- Concatenar título y precio en una sola columna
SELECT CONCAT(titulo,' = ', precio) FROM libros;

-- Calcular el promedio de precio por libro en función de la cantidad (valor / cantidad)
SELECT titulo,precio,cantidad,
       (precio * cantidad) AS valor,
       (precio * cantidad) / cantidad AS promedio FROM libros;
       
-- Mostrar el doble del precio 
SELECT titulo,precio,cantidad, (precio*2)   AS doblePrecio FROM libros;

-- Obtener el valor de descuento del 10% aplicado al precio
select titulo, precio,(precio*0.1),precio+(precio*0.1) from libros;

-- Mostrar la cantidad resultante si se venden 50 unidades de cada libro
SELECT titulo,precio,cantidad,(precio*50) FROM libros;

-- Mostrar el título en mayúsculas junto con el autor en minúsculas
SELECT Ucase (titulo) from libros;
SELECT Lcase (titulo) from libros;

-- Crear una columna calculada que muestre el costo por cada 100 unidades
select titulo, precio,cantidad,(precio*100) as Total from libros;


-- Ejecute el siguiente código analice y comente el mensaje:
SELECT titulo, cantidad,
       CONCAT(titulo, ' - ', cantidad) AS libro_cantidad,
       LENGTH(libro_cantidad) AS longitud_texto-- marca error
FROM libros;
 
 -- Calcular el valor total y luego aplicar un descuento  a su gusto
select titulo, precio,(precio*cantidad)as total,(precio*cantidad)+(precio*0.1) AS total_descuento from libros;



