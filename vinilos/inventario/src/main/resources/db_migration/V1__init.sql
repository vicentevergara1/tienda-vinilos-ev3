CREATE TABLE IF NOT EXISTS inventarios (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    sku VARCHAR(50) NOT NULL UNIQUE,
                    stock_disponible INT NOT NULL DEFAULT 0,
                    ubicacion_bodega VARCHAR(100) NOT NULL,
                    ultima_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
                    );

-- Datos iniciales para pruebas coordinadas con producto-service
INSERT INTO inventarios (sku, stock_disponible, ubicacion_bodega)
VALUES ('VIN-PINK-FLOYD-001', 15, 'BODEGA-A1');

INSERT INTO inventarios (sku, stock_disponible, ubicacion_bodega)
VALUES ('VIN-BEATLES-002', 8, 'BODEGA-B2');

INSERT INTO inventarios (sku, stock_disponible, ubicacion_bodega)
VALUES ('VIN-FLEETWOOD-MAC-003', 8, 'BODEGA-C3');