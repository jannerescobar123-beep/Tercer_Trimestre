USE tienda;
-- =========================
-- TABLA USUARIOS
-- =========================
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50),
    rol VARCHAR(20)
);

-- Usuario por defecto (admin)
INSERT INTO usuario (username, password, rol)
VALUES ('admin', '123', 'admin');

-- =========================
-- TABLA COMPRAS
-- =========================
CREATE TABLE compra (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    edad INT,
    telefono VARCHAR(20),
    tipo VARCHAR(1),
    producto VARCHAR(100),
    precio DOUBLE,
    cantidad INT,
    total DOUBLE,
    descuento DOUBLE,
    total_final DOUBLE
);