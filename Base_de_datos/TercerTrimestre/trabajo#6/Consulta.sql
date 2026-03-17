 -- Se crea la base de datos
CREATE DATABASE meteo;
-- Se define la base a usar 
USE meteo;

-- Se crea la tabla estación
CREATE TABLE estacion (
identificador MEDIUMINT UNSIGNED NOT NULL,
latitud VARCHAR(14) NOT NULL,
longitud VARCHAR(15) NOT NULL,
altitud MEDIUMINT NOT NULL,
PRIMARY KEY (identificador)
) ENGINE=INNODB;

-- ver todo lo que contiene la tabla
SELECT * FROM estacion;

INSERT INTO estacion (identificador, latitud, longitud, altitud) VALUES
(1, '4.609710', '-74.081750', 2640),
(2, '6.251840', '-75.563590', 1495),
(3, '3.451647', '-76.531985', 1018);

-- Se crea la tabla muestra:
CREATE TABLE muestra (
identificadorestacion MEDIUMINT UNSIGNED NOT NULL,
fecha DATE NOT NULL,
temperaturaminima TINYINT,
temperaturamaxima TINYINT,
precipitaciones SMALLINT UNSIGNED,
humedadminima TINYINT UNSIGNED,
humedadmaxima TINYINT UNSIGNED,
velocidadminima SMALLINT UNSIGNED,
velocidadmaxima SMALLINT UNSIGNED,
KEY (identificadorestacion),
FOREIGN KEY (identificadorestacion)
REFERENCES estacion(identificador)
ON DELETE NO ACTION
ON UPDATE CASCADE
) ENGINE=INNODB;

INSERT INTO muestra 
(identificadorestacion, fecha, temperaturaminima, temperaturamaxima, precipitaciones, humedadminima, humedadmaxima, velocidadminima, velocidadmaxima) 
VALUES
-- Estación 1
(1, '2026-03-01', 10, 22, 5, 60, 85, 3, 15),
(1, '2026-03-02', 11, 23, 0, 58, 82, 4, 18),
(1, '2026-03-03', 9, 21, 12, 65, 90, 2, 14),
(1, '2026-03-04', 10, 24, 3, 55, 80, 3, 16),
(1, '2026-03-05', 12, 25, 0, 50, 78, 5, 20),

-- Estación 2
(2, '2026-03-01', 18, 30, 2, 55, 80, 6, 22),
(2, '2026-03-02', 19, 31, 0, 52, 77, 5, 21),
(2, '2026-03-03', 17, 29, 8, 60, 85, 4, 19),
(2, '2026-03-04', 18, 32, 0, 50, 75, 7, 24),
(2, '2026-03-05', 20, 33, 1, 48, 73, 6, 23),

-- Estación 3
(3, '2026-03-01', 5, 16, 15, 70, 95, 2, 12),
(3, '2026-03-02', 6, 17, 10, 68, 92, 3, 13),
(3, '2026-03-03', 4, 15, 20, 72, 96, 2, 11),
(3, '2026-03-04', 5, 18, 5, 65, 90, 4, 14),
(3, '2026-03-05', 7, 19, 0, 60, 88, 5, 16);

SELECT * FROM muestra;

-- Luego en un archivo sql, intente en insertar una estación cuyo código se repita,
-- pegue el error que muestra Mysql

INSERT INTO estacion (identificador, latitud, longitud, altitud) VALUES
(1, '4.609710', '-74.081750', 2640);
-- Error de SQL (1062): Duplicate entry '1' for key 'estacion.PRIMARY'uery


-- Agregue una muestra tomada en la estación 1, en la fecha: 2020-02-30, 
-- pegue el mensaje que mysql genera

INSERT INTO muestra 
(identificadorestacion, fecha, temperaturaminima, temperaturamaxima, precipitaciones, humedadminima, humedadmaxima, velocidadminima, velocidadmaxima) 
VALUES
-- Estación 1
(1, '2020-02-30', 10, 22, 5, 60, 85, 3, 15);
-- Error de SQL (1292): Incorrect date value: '2020-02-30' for column 'fecha' at row 1uery


-- Practique las 3 situaciones que se mostraron en la sesión anterior: LLaves foraneas.
-- ----- 1 -------
-- Modificar el identificador de una estación EL IDENTIFICADOR 1 SE CAMBIA A 10
UPDATE estacion
SET identificador = 10
WHERE identificador = 1;

-- ----- 2 -------
-- de la tabla muestra, en la columna velocidadminima se van a borrar los que sean igual a 3
DELETE FROM muestra
WHERE velocidadminima = 3;

-- ----- 3 -------
-- modifica  la altitud  de una muestra con el identificador = 2 
UPDATE estacion
SET altitud = 2700
WHERE identificador = 2;

-- Realice mínimo 10 consultas a la base meteo, 
-- donde indique que hace la consulta y luego el script correspondiente

-- ----- 1 -------
-- Mostrar todas las estaciones registradas
SELECT * FROM estacion;

-- ----- 2 -------
-- Mostrar todas las muestras registradas
SELECT * FROM muestra;

-- ----- 3 -------
-- Mostrar las fechas y temperaturas máximas registradas
SELECT fecha, temperaturamaxima
FROM muestra;

-- ----- 4 -------
-- Mostrar las muestras donde la temperatura máxima sea mayor a 30 grados
SELECT *
FROM muestra
WHERE temperaturamaxima > 30;

-- ----- 5 -------
-- Mostrar las muestras donde hubo precipitaciones
SELECT *
FROM muestra
WHERE precipitaciones > 0;

-- ----- 6 -------
-- Mostrar las muestras de la estación 2
SELECT *
FROM muestra
WHERE identificadorestacion = 2;

-- ----- 7 -------
-- Mostrar las temperaturas mínimas y máximas registradas
SELECT temperaturaminima, temperaturamaxima
FROM muestra;

-- ----- 8 -------
-- Mostrar las muestras ordenadas por temperatura máxima de mayor a menor
SELECT *
FROM muestra
ORDER BY temperaturamaxima DESC;

-- ----- 9 -------
-- Contar cuántas muestras hay registradas
SELECT COUNT(*) AS total_muestras
FROM muestra;

-- ----- 10 -------
-- Mostrar las estaciones junto con sus datos de muestra usando JOIN
SELECT estacion.identificador, estacion.latitud, estacion.longitud, muestra.fecha, muestra.temperaturamaxima
FROM estacion
INNER JOIN muestra
ON estacion.identificador = muestra.identificadorestacion;

