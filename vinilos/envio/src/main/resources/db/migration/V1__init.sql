CREATE TABLE envios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_venta BIGINT NOT NULL,
    direccion_destino VARCHAR(255) NOT NULL,
    estado_despacho VARCHAR(50) NOT NULL,
    codigo_seguimiento VARCHAR(50) UNIQUE
);

INSERT INTO envios (id_venta, direccion_destino, estado_despacho, codigo_seguimiento) 
VALUES (1, 'Av. Siempre Viva 123', 'PREPARANDO', 'TRACK-1001');