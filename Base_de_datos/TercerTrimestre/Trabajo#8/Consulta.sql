CREATE DATABASE IF NOT EXISTS personas_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
 
USE personas_db;
 
CREATE TABLE IF NOT EXISTS personas (
    id               INT            NOT NULL AUTO_INCREMENT,
    nombre           VARCHAR(100)   NOT NULL,
    email            VARCHAR(150)   NOT NULL,
 
    -- ENUM: solo acepta UNO de estos valores
    genero           ENUM('Masculino', 'Femenino', 'Otro')
                         NOT NULL,
 

    nivel_academico  ENUM('Primaria', 'Secundaria', 'Técnico', 'Universitario', 'Posgrado')
                         NOT NULL,
 

    idiomas          SET('Español', 'Inglés', 'Francés', 'Portugués', 'Alemán')
                         NOT NULL,
 
    habilidades      SET('Java', 'SQL', 'Python', 'HTML', 'CSS', 'JavaScript', 'C++')
                         NOT NULL,
 
    creado_en        TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
 
    PRIMARY KEY (id),
    UNIQUE KEY uk_email (email)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;