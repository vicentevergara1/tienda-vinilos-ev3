CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rut VARCHAR(12) NOT NULL UNIQUE,
    nombre_completo VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    direccion VARCHAR(255) NOT NULL
);

INSERT INTO clientes (rut, nombre_completo, email, direccion) VALUES 
('11111111-1', 'Juan Perez', 'juan@gmail.com', 'Av. Siempre Viva 123'),
('22222222-2', 'Maria Gonzalez', 'maria@gmail.com', 'Calle Falsa 456');