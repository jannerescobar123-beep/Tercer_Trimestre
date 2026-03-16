-- Se crea la tabla con sus caracteristicas
CREATE TABLE pedidos (
codigo int AUTO_INCREMENT PRIMARY key,
Nombre ENUM('Empanada','pizza')  DEFAULT 'Empanada',
Tipo VARCHAR (20) NOT null ,
Precio DECIMAL (4,2) DEFAULT 1 CHECK (Precio > 0 AND Precio < 99.99) ,
Cantidad int DEFAULT 12 CHECK (Cantidad >1 AND Cantidad < 200),
Domicilio VARCHAR(100) NOT null
);
-- elimine tabla que quedo mal
DROP TABLE pedidos;
-- ver tablas
SHOW TABLES;
-- contenido de tablas
DESCRIBE pedidos;
-- inserto datos
insert into pedidos (Nombre,Tipo,Precio,Cantidad,Domicilio)
values('pizza','muzarela',4.00,3,'Sarmiento 235');

insert into pedidos (Tipo,Precio,Cantidad,Domicilio)
values('arabe',1.00,24,'Urquiza 296');

insert into pedidos (Nombre,Tipo,Domicilio)
values('Empanada','salteña','Colon 309');

insert into pedidos (Tipo,Domicilio)
values('arabe','San Martin 444');

insert into pedidos (Nombre,Tipo,Precio,Domicilio)
values('pizza','especial',4.00,'Avellaneda 395');
SELECT * FROM pedidos;