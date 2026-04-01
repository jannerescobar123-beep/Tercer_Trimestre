USE tienda_db;

-- Tabla de usuarios para el login
-- rol ENUM: solo acepta 'admin' o 'usuario'
CREATE TABLE usuarios (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(60)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol      ENUM('admin','usuario') NOT NULL DEFAULT 'usuario',
    activo   TINYINT(1) NOT NULL DEFAULT 1
);

-- Tabla de clientes, vinculada a un usuario
-- tipo ENUM: A=40%, B=20%, C=10%, NULL=sin descuento
CREATE TABLE clientes (
    id         INT Abackend`backend_cristian`backendtienda_dbclientescomprasproductosusuarioscomprasUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(120) NOT NULL,
    apellido   VARCHAR(120) NOT NULL,
    edad       INT,
    telefono   VARCHAR(20),
    tipo       ENUM('A','B','C') DEFAULT NULL,
    email      VARCHAR(180) NOT NULL UNIQUE,
    id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE productos (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(120)  NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock  INT NOT NULL DEFAULT 0
);


-- Tabla de compras: relaciona cliente con producto
-- Guarda el descuento aplicado y el total final con descuento
CREATE TABLE compras (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente     INT NOT NULL,
    id_producto    INT NOT NULL,
    cantidad       INT           NOT NULL DEFAULT 1,
    valor_unitario DECIMAL(10,2) NOT NULL,
    total_sin_desc DECIMAL(10,2) NOT NULL,
    descuento      DECIMAL(10,2) NOT NULL DEFAULT 0,
    total_final    DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_cliente)  REFERENCES clientes(id),
    FOREIGN KEY (id_producto) REFERENCES productos(id)
);

-- Usuarios de prueba
INSERT INTO usuarios (username, password, rol) VALUES
  ('admin', 'admin123', 'admin'),
  ('juan',  'user123',  'usuario'),
  ('maria', 'user123',  'usuario'),
  ('carlos','user123',  'usuario');
 
-- Productos de prueba
INSERT INTO productos (nombre, precio, stock) VALUES
  ('Nevera Samsung 300L',  1200000, 10),
  ('Lavadora LG 12kg',      850000,  8),
  ('Microondas Haceb 30L',  280000, 15),
  ('Televisor Sony 55"',   2100000,  5),
  ('Licuadora Oster 2L',     95000, 20);
 
-- Clientes con tipo de afiliacion (A, B, C o NULL)
INSERT INTO clientes (nombre, apellido, edad, telefono, tipo, email, id_usuario) VALUES
  ('Juan',   'Pérez',  28, '3001234567', 'A', 'juan@email.com',   2),
  ('María',  'Gómez',  35, '3109876543', 'B', 'maria@email.com',  3),
  ('Carlos', 'Ruiz',   22, '3205551234', NULL,'carlos@email.com', 4);
  
  SELECT * FROM clientes;
  
  SELECT * FROM productos;

SELECT * FROM usuarios
