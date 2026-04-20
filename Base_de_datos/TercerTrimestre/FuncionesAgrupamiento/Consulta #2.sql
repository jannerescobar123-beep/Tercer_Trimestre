create table cursos(
  codigo tinyint unsigned auto_increment,
  tema varchar(20) not null,
  horario char(2) not null,
  clases tinyint unsigned default 10,
  fechainicio date,
  costo float(5) unsigned,
  primary key(codigo)
 );
 
 -- Ingrese los siguientes registros:
insert into cursos (tema,horario,clases,fechainicio,costo)  values
('PHP básico','AM',10,'2024-08-07',20000),
('PHP básico','PM',default,'2024-08-14',20000),
('PHP básico','AM',default,'2024-08-05',20000),
('PHP avanzado','AM',20,'2006-08-01',35000),
('JavaScript básico','PM',15,'2024-09-11',15000),
('Páginas web','PM',15,'2024-08-08',20000),
('Páginas web','AM',15,'2024-08-12',20000),
('Páginas web','AM',15,'2024-08-21',20000),
('HTML avanzado','AM',20,'2024-09-18',18000),
('HTML avanzado','PM',20,'2024-09-25',18000),
('JavaScript avanzado','PM',25,'2024-09-18',15000);

-- Lista de temas de los cursos sin repetición:
select distinct tema
from cursos;

-- Seleccione los cursos donde el tema incluya "PHP", sin repetir horario ni tema:
select distinct horario, tema from cursos
where tema like '%PHP%';

-- Cuente la cantidad de cursos DISTINTOS agrupados por horario:
select horario, count(distinct tema) AS "Total de temas"
from cursos
group by horario;