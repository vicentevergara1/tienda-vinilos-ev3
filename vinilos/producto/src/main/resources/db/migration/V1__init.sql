CREATE TABLE productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    artista VARCHAR(255) NOT NULL,
    precio DOUBLE NOT NULL,
    genero VARCHAR(100) NOT NULL
);

INSERT INTO productos (titulo, artista, precio, genero) VALUES 
('Dark Side of the Moon', 'Pink Floyd', 25000.0, 'Rock Progresivo'),
('Abbey Road', 'The Beatles', 22000.0, 'Rock Clásico'),
('Rumours', 'Fleetwood Mac', 18000.0, 'Pop Rock');