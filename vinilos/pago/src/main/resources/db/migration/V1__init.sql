CREATE TABLE pagos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_venta BIGINT NOT NULL,
    monto DOUBLE NOT NULL,
    metodo_pago VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    fecha_pago DATETIME NOT NULL
);

INSERT INTO pagos (id_venta, monto, metodo_pago, estado, fecha_pago) 
VALUES (1, 25000.0, 'WEBPAY', 'APROBADO', NOW());