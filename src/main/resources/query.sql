use bd_borabora_movil;

-- USUARIOS
-- Inserts para la tabla "usuarios"
INSERT INTO usuarios (nombres, apellidos, contrasena, doc_identidad, email, telefono) VALUES

	('Brigitte', 'Prieto', 'idat', '12345678', 'brigitte@gmail.com', '983456789'),
	('Jefferson', 'Ferre',  'idat', '14345678', 'jefferson@gmail.com', '987654321'),
	('Jhon', 'Flores', 'idat', '12341678', 'jhon@gmail.com', '997634375'),
	('Benjamin', 'Benjamin', 'idat', '12387678', 'benjamin@gmail.com', '946654267'),
	('Carlos', 'Acosta', 'idat', '12310678', 'carlos@gmail.com', '967644328');

-- CATEGORIAS
-- Inserts para la tabla "categorias"
INSERT INTO categorias (id, nombre) VALUES
(1, 'Aceites'),
(2, 'Granos y cereales'),
(3, 'Menestras'),
(4, 'Lacteos'),
(5, 'Licores'),
(6, 'Enlatados');

-- PRODUCTOS
-- Granos y cereales
INSERT INTO productos (nombre, descripcion, marca, precio, stock, fvencimiento, categoria_id) VALUES
('Arroz', 'Arroz Extra Faraón de 5 Kg', 'Faraón', 25.20, 100, '2023-12-24', 2),
('Avena', 'Avena Hojuelas Gruesas Grano de Oro 900 g', 'Grano de Oro', 17.20, 100, '2025-01-15', 2),
('Avena', 'Avena Tradicional Quaker de 900 g', 'Quaker', 15.70, 70, '2024-02-12', 2);

-- Menestras
INSERT INTO productos (nombre, descripcion, marca, precio, stock, fvencimiento, categoria_id) VALUES
('Frijoles', 'Frijol Panamito de 500g', 'Valle del Norte', 6.40, 50, '2024-06-23', 3),
('Lenteja', 'bebé tesoro del campo 500 g', 'Tesoro del campo', 6.30, 70, '2024-09-23', 3),
('Garbanzo', 'Crunch Garbanzo BBQ Dyfferent 100 g', 'Crunch', 7.50, 50 , '2023-12-24', 3);

-- Aceites
INSERT INTO productos (nombre, descripcion, marca, precio, stock, fvencimiento, categoria_id) VALUES
('Aceite de Oliva', 'Aceite Oliva extra virgen 200 ML', 'Olivos del Sur', 18.90, 75, '2024-03-22', 1),
('Aceite vegetal', 'Aceite vegetal Primor de 900 mL', 'Primor', 11.80, 80, '2024-07-15', 1),
('Aceite vegetal', 'Aceite Vegetal de Soya Sao en Botella de 900 mL', 'Sao', 9.20, 60, '2024-01-19', 1);

-- Lacteos
INSERT INTO productos (nombre, descripcion, marca, precio, stock, fvencimiento, categoria_id) VALUES
('Queso crema', 'Queso crema Philadelphia Brick 180 g', 'Philadelphia', 12.50, 50, '2023-10-15', 4),
('Queso', 'Queso fundido LAIVE X 12 TAJADAS', 'Laive', 11.30, 50, '2023-09-10', 4),
('Mantequilla', 'Mantequilla Gloria de 90 g', 'Gloria', 6.20, 100, '2023-09-27', 4);

-- Licores
INSERT INTO productos (nombre, descripcion, marca, precio, stock, fvencimiento, categoria_id) VALUES
('Vino', 'Vino Cabernet Franc El Enemigo 750 mL', 'El Enemigo', 109.90, 10, '2050-01-01', 5),
('Ginebra', 'Beefeater Gin de 700 mL', 'Beefeater ', 84.90, 20, '2050-01-01', 5),
('Vino', 'Vino joven de 750 mL', 'Marques de Vitoria', 34.90, 20, '2050-01-01', 5);

-- Enlatados
INSERT INTO productos (nombre, descripcion, marca, precio, stock, fvencimiento, categoria_id) VALUES
('Atun', 'Filete de atún Florida en agua - 12 unidades', 'Florida', 69.90, 30, '2025-07-23', 6),
('Ginebra', 'Choclo Dulce Desgran VALLE FERTILX432GR', 'Valle Fertil ', 8.90, 50, '2024-12-14', 6),
('Atun', 'Filete de atún artesanal Campomar de 100 g', 'Campomar', 7.50, 90, '2025-10-08', 6);

-- COMPRAS
INSERT INTO compras (total, igv, subtotal, metodopago, fcompra, usuario_id) VALUES 
(100.0, 18.0, 82.0, 'Efectivo', '2022-01-01', 1),
(100.0, 25.0, 75.0, 'Tarjeta', '2022-12-03', 1),
(100.0, 20.0, 80.0, 'Efectivo', '2022-11-03', 1),
(200.0, 36.0, 164.0, 'Tarjeta', '2022-01-02', 2),
(900.0, 162.0, 738.0, 'Efectivo', '2022-01-09', 2),
(200.0, 36.0, 164.0, 'Tarjeta', '2022-01-02', 2),
(800.0, 144.0, 656.0, 'Tarjeta', '2022-01-08', 3),
(300.0, 54.0, 246.0, 'Efectivo', '2022-01-03', 3),
(300.0, 54.0, 246.0, 'Efectivo', '2022-01-03', 3),
(400.0, 72.0, 328.0, 'Tarjeta', '2022-01-04', 4),
(500.0, 90.0, 410.0, 'Efectivo', '2022-01-05', 5),
(600.0, 108.0, 492.0, 'Tarjeta', '2022-01-06', 5),
(700.0, 126.0, 574.0, 'Efectivo', '2022-01-07', 5);

-- COMPRAS PRODUCTOS
INSERT INTO compra_productos (compra_id, producto_id, cantidad) VALUES
(1, 1, 5), 
(2, 2, 3),
(3, 3, 8),
(4, 4, 2),
(5, 5, 4),
(6, 6, 7),
(7, 7, 1),
(8, 8, 6),
(9, 9, 3),
(10, 10, 5),
(11, 11, 2),
(12, 12, 9),
(13, 13, 4);
-- ----------------------
use bd_borabora_movil;
select * from usuarios; 
select * from categorias;
select * from productos;
select * from compras;
select * from compra_productos;