CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente BIGINT NOT NULL,
    fecha_pedido DATETIME NOT NULL,
    total DOUBLE NOT NULL,
    estado VARCHAR(50) NOT NULL
);

INSERT INTO pedidos (id_cliente, fecha_pedido, total, estado) 
VALUES (1, NOW(), 45000.0, 'CREADO');