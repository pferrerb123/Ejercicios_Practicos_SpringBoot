-- Limpiar la tabla antes de insertar datos (opcional)
DROP TABLE IF EXISTS cliente;

-- Crear la tabla cliente
CREATE TABLE cliente (
    id VARCHAR (10) PRIMARY KEY,
    nombres VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    edad INT,
    tipo_cliente ENUM('REGULAR', 'VIP') NOT NULL
);

-- Crear la tabla prestamo
CREATE TABLE prestamos (
    id VARCHAR(255) PRIMARY KEY,
    monto DOUBLE NOT NULL,
    cliente_id VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    estado ENUM('PENDIENTE', 'PAGADO') NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- Insertar algunos registros iniciales de clientes
INSERT INTO clientes (id, nombre, email, edad, tipo_cliente)
VALUES
('1', 'Juan Perez', 'juan.perez@mail.com', 30, 'REGULAR'),
('2', 'Maria Lopez', 'maria.lopez@mail.com', 25, 'VIP'),
('3', 'Carlos Garcia', 'carlos.garcia@mail.com', 40, 'REGULAR');


-- Insertar algunos registros iniciales de prestamos
INSERT INTO prestamos (id, monto, cliente_id, fecha, estado)
VALUES
('1', 5000, '1', '2024-12-18', 'PENDIENTE'),
('2', 10000, '2', '2024-12-19', 'PENDIENTE'),
('3', 3000, '3', '2024-12-20', 'PAGADO');


