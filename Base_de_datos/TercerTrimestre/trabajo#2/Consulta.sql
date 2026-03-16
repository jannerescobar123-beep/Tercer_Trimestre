CREATE TABLE jugadoresDeFutbol (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(15),
apellido VARCHAR(15),
edad INT,
equipo VARCHAR (50),
posicion VARCHAR(20)
);

INSERT INTO jugadoresDeFutbol (nombre, apellido, edad, equipo, posicion) VALUES
('Lionel', 'Messi', 38, 'Inter Miami', 'Delantero'),
('Kylian', 'Mbappe', 27, 'Real Madrid', 'Delantero'),
('Erling', 'Haaland', 25, 'Manchester City', 'Delantero'),
('Kevin', 'De Bruyne', 34, 'Manchester City', 'Mediocampista'),
('Luka', 'Modric', 40, 'Real Madrid', 'Mediocampista'),
('James', 'Rodriguez', 34, 'Sao Paulo', 'Mediocampista'),
('Virgil', 'Van Dijk', 34, 'Liverpool', 'Defensa'),
('Ronald', 'Araujo', 27, 'Barcelona', 'Defensa'),
('Alisson', 'Becker', 33, 'Liverpool', 'Portero'),
('Emiliano', 'Martinez', 33, 'Aston Villa', 'Portero');
SELECT * FROM jugadoresDeFutbol;
-- ord(caracter): 
select ORD('@');  -- Retorna 64
select ORD('\\'); -- Retorna 92
select ORD('ñ'); -- Imprime 50097

-- char(x,..):
select char(65,66,67); -- Retorna ABC
select char(64,92,39); -- Retorna @\'

-- concat(cadena1,cadena2,...)
select concat('Hola,',' ','que tal?') as saludo; -- Imprime Hola, que tal
Select concat(nombre,'-',apellido) from jugadoresDeFutbol -- en este caso concatena los nombres ya pellidos

-- concat_ws(separador,cadena1,cadena2,...):
select concat_ws('-','Lionel','James','Alisson'); -- Salida: Lionel-James-Alisson

-- find_in_set(cadena,lista de cadenas)

-- Observe la cantidad de , que es un separador de parametros, cada , es una cadena de palabras
select find_in_set('LA','En,un,lugar,de,la,Mancha,de cuyo nombre no quiero acordarme');
-- El resultado es 5, debido aque la palabra 'LA' se encuentra en la  posición 5 de la lista de cadenas, además no altera el resultado que este en mayúscula sostenida

-- length(cadena):
Select length("La vida es bella"); -- Esta cadena tiene 16 caracteres.

-- En una tabla seria: IMPORTANTE 
Select length(nombre)
from jugadoresDeFutbol;

-- lpad(cadena,longitud,cadenarelleno) | rpad:
-- lpad, con l de Izquierda
select lpad('123',10,'0'); -- Salida: 0000000123

-- Grantizar que todos los registros tengan la misma cantidad de caracteres en un campo:
Select lpad(edad,3,'@')
From jugadoresDeFutbol;

-- Ahora la r de la derecha
Select rpad(edad,7,' años')
From jugadoresDeFutbol;

-- right(cadena,longitud) | Left: 

-- De la tabla ciudades, el campo nombre, de la letra cuatro en adelante muestra el nombre
Select RIGHT(nombre,4)
From jugadoresDeFutbol;

-- De la tabla ciudades las primeras 6 letras del campo ciudad 
select LEFT(nombre,3)
From jugadoresDeFutbol;

-- TRIM([{BOTH | LEADING | TRAILING} [remstr] FROM] str), TRIM([remstr FROM] str)
-- Retorna la misma cadena pero quitando un prefijo, un sufijo o ambos.
-- Si no se indica leading, trailing o both, se asume both (elimina al inicio y al final).
-- no se especifica qué texto eliminar, entonces elimina los espacios en blanco.
SELECT TRIM('  bar   ');
-- 'bar'
SELECT TRIM(LEADING 'x' FROM 'xxxbarxxx');
-- 'barxxx' ELIMINA IZQUIERDA
SELECT TRIM(BOTH 'x' FROM 'xxxbarxxx');
-- 'bar' ELIMINA IZQUIERDA Y DERECHA
SELECT TRIM(TRAILING 'xyz' FROM 'barxxyz');
-- 'barx' ELIMINA DERECHA

-- Replace(cadena,cadenareemplazo,cadenareemplazar)

Select replace('xxx.astiango.co','x','w');
-- PALABRA A CAMBIAR + EL SIGNO QUE QUIERAS CAMBIAR + POR LA QUE DESEAS CAMBIAR

-- reverse(cadena):
-- Devuelve la cadena invirtiendo el orden de los caracteres
Select reverse(nombre) from jugadoresDeFutbol;

-- Funciones para pasar a Mayúsculas, Minúsculas
select Lcase(apellido) from jugadoresDeFutbol; -- Minúscula
select Ucase(apellido) from jugadoresDeFutbol; -- Mayúscula
select lower(apellido) from jugadoresDeFutbol; -- Minúscula
select upper(apellido) from jugadoresDeFutbol; -- Mayúscula

-- funciones de la pagina de de MySQL

-- 0 = iguales, -1 = primera es menor, 1 si la primera es mayor
SELECT STRCMP('Messi','Ronaldo');

-- esto repite el nombre de cada jugador 2 veces
SELECT nombre, repeat(nombre,2)FROM jugadoresdefutbol;
-- se repite el nombre del jugador lionel
SELECT nombre, repeat(nombre,2)FROM jugadoresdefutbol WHERE nombre ='Lionel';


-- CHAR_LENGTH() Devuelve la cantidad de caracteres del nombre,pero mas exacto con caracteres especiales
SELECT nombre, CHAR_LENGTH(nombre) FROM jugadoresdefutbol;

-- SUBSTRING_INDEX() Devuelve parte del texto antes de un delimitador (la primera palabra del equipo).
SELECT SUBSTRING_INDEX(nombre,' ',1) FROM jugadoresDeFutbol;

-- REGEXP Permite buscar usando expresiones regulares, Se usa mucho para validaciones y filtros avanzados (más potente que LIKE).
SELECT nombre FROM jugadoresDeFutbol WHERE nombre REGEXP '^[A-L]';

-- REGEXP_REPLACE() Reemplaza texto usando patrones avanzados, Muy útil para limpieza de datos.
SELECT REGEXP_REPLACE(nombre, '[aeiou]', '*') FROM jugadoresDeFutbol;

-- REGEXP_SUBSTR() Extrae parte del texto que cumpla un patrón específico.
SELECT REGEXP_SUBSTR(equipo, '[A-Z][a-z]+')FROM jugadoresDeFutbol;

-- REGEXP_INSTR() Devuelve la posición donde se cumple un patrón.
SELECT REGEXP_INSTR(nombre, '[aeiou]') FROM jugadoresDeFutbol;

--  FORMAT() Formatea números con decimales.Se usa mucho en reportes financieros.
SELECT FORMAT(edad, 2) FROM jugadoresdefutbol;

-- FIELD() Devuelve la posición de un valor dentro de una lista.
SELECT nombre, FIELD(posicion,'Portero','Defensa','Mediocampista','Delantero') FROM jugadoresdefutbol;

-- ELT() Devuelve el elemento en la posición indicada.
SELECT ELT(2,'Portero','Defensa','Mediocampista');

-- POSITION() Busca la posición de un texto dentro de otro (forma alternativa a LOCATE).
SELECT POSITION('a' IN apellido) FROM jugadoresDeFutbol;







