-- se crea tabla de personas 
CREATE TABLE personas (
    idPersona INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL
) ENGINE=INNODB;

-- se inserta datos 
INSERT INTO personas (nombre, apellido) VALUES
('Ana', 'Andrade'),
('Bertulio', 'Barco'),
('Carlos', 'Cardenas'),
('Delio', 'Diaz'),
('Ernesto', 'Erazo');
-- se consulta si se insertaron los datos correctamente 
SELECT * FROM personas;

-- se crea tabla de telefonos
CREATE TABLE telefonos (
    idTelefono INT AUTO_INCREMENT PRIMARY KEY,
    numero CHAR(12),
    idPersona INT,
    FOREIGN KEY (idPersona) REFERENCES personas(idPersona)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=INNODB;
SELECT * FROM telefonos;

-- se insertan dats en la tabla telefonos
INSERT INTO telefonos (numero, idPersona) VALUES
('111111111111', 1), -- Ana Andrade
('222222222222', 1), -- Ana Andrade
('333333333333', 2), -- Bertulio Barco
('444444444444', 3), -- Carlos Cardenas
('555555555555', 5); -- Ernesto Erazo

-- se crea tabla de redes sociales con llave foranea
CREATE TABLE redes_sociales (
    idRed INT AUTO_INCREMENT PRIMARY KEY,
    plataforma VARCHAR(30),
    usuario VARCHAR(50),
    idPersona INT,
    FOREIGN KEY (idPersona) REFERENCES personas(idPersona)
        ON DELETE SET NULL
        ON UPDATE RESTRICT
) ENGINE=INNODB;

-- se verifica si  se insertaron los datos
SELECT * FROM redes_sociales;

-- se agregan datos a la tabla de redes
INSERT INTO redes_sociales (plataforma, usuario, idPersona) VALUES
('Twitter', 'ana_andrade', 1),
('Facebook', 'bertulio_b', 2),
('Instagram', 'carlos_c', 3),
('LinkedIn', 'delio_d', 4),
('Twitter', 'ernesto_e', 5);

-- Elimine el registro: Ana Andrade en la tabla personas:Observe que pasa con sus teléfonos.
DELETE FROM personas WHERE `personas`.`idPersona` = 1
-- Observe que pasa con sus teléfonos.
SELECT * FROM telefonos;

-- Elimine a Bertulio Barco en la tabla personas:
DELETE FROM personas WHERE `personas`.`idPersona` = 2

-- Observe que pasa en la tabla `redes_sociales`. Null
SELECT * FROM redes_sociales;

-- Modificar el `idPersona` de Carlos Cardenas en `personas`: 
UPDATE `personas` SET `idPersona` = '18' WHERE `personas`.`idPersona` =  3

-- Se muestra un error ya que en la tabla redes sociales existe una restriccion

-- Cree una tabla llamada emails que almacene direcciones de correo electrónico asociadas a cada persona.
CREATE TABLE emails (
 idEmail INT AUTO_INCREMENT PRIMARY KEY,
 correo VARCHAR(100),
 idPersona INT,
 FOREIGN KEY (idPersona) REFERENCES personas(idPersona)
     ON DELETE NO ACTION
     ON UPDATE NO ACTION
 ) ENGINE=InnoDB;
 
-- se insertan datos de prueba en la tabla emails
INSERT INTO emails (correo, idPersona) VALUES
('ana@correo.com', 1),
('bertulio@correo.com', 2),
('carlos@correo.com', 3);

-- se consultan los datos para verificar la inserción
SELECT * FROM emails;

-- se intenta eliminar la persona Ana
DELETE FROM personas WHERE idPersona = 1;

--MySQL genera un error porque existe una relación con la tabla emails
-- esto ocurre porque NO ACTION no permite eliminar registros que están relacionados


-- CASCADE sirve para que cuando se elimine o actualice un registro de la tabla principal,
-- los registros relacionados también se eliminen o actualicen automáticamente.

-- SET NULL se usa cuando se quiere conservar el registro relacionado,
-- pero quitando la relación con la tabla principal dejando el campo en NULL.

-- RESTRICT y NO ACTION evitan que se eliminen o modifiquen registros
-- si hay datos relacionados, para no dañar la integridad de la base de datos.

-- MySQL no soporta SET DEFAULT en claves foráneas,
-- por lo que no puede asignar automáticamente un valor por defecto.

-- ENGINE=InnoDB es el motor que permite usar claves foráneas
-- y mantener la integridad referencial.

