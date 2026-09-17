
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100),
    rol VARCHAR(20) DEFAULT 'CLIENTE'
);
CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL CHECK (stock >= 0)
);

CREATE TABLE ordenes (
    id SERIAL PRIMARY KEY,
    usuario_id INT REFERENCES usuarios(id),
    producto_id INT REFERENCES productos(id),
    monto_total DECIMAL(10, 2),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pagos (
    id SERIAL PRIMARY KEY,
    orden_id INT REFERENCES ordenes(id),
    metodo_pago VARCHAR(20), 
    estado VARCHAR(20) 
);

INSERT INTO usuarios (username, email, rol) VALUES 
('USR-ADMIN-01', 'admin@utp.edu.pe', 'ADMIN'),
('USR-10023', 'juan.perez@gmail.com', 'CLIENTE'),
('USR-20045', 'ana.gomez@gmail.com', 'CLIENTE'),
('BOT_SCRIPT_01', 'bot@malicioso.com', 'BLOQUEADO'), 
('USR-GUEST-99', 'invitado@temp.com', 'CLIENTE');


INSERT INTO productos (nombre, precio, stock) VALUES 
('Smartphone Pro', 999.00, 100),
('Laptop Gamer', 1500.00, 50),
('Auriculares BT', 150.00, 10),
('Monitor 4K', 400.00, 0),
('Teclado Mecanico', 80.00, 200);


INSERT INTO ordenes (usuario_id, producto_id, monto_total) VALUES 
(2, 1, 999.00), 
(3, 2, 1500.00), 
(2, 3, 150.00);  

INSERT INTO pagos (orden_id, metodo_pago, estado) VALUES 
(1, 'TARJETA', 'EXITOSO'),
(2, 'PAYPAL', 'EXITOSO'),
(3, 'TRANSFERENCIA', 'EXITOSO');
