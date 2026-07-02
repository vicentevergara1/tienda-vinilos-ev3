CREATE TABLE resenas (
                         id DOUBLE AUTO_INCREMENT PRIMARY KEY,
                         puntuacion INT NOT NULL,
                         comentario VARCHAR(255) NOT NULL,
                         fecha_resena DATETIME NOT NULL
);

INSERT INTO resenas (puntuacion, comentario, fecha_resena)
VALUES
    (3, 'Me ha ayudado a descansar en las tardes', NOW()),
    (4, 'Realmente te revitaliza!!!', NOW()),
    (3, 'Su mejor trabajo desde que empezo a publicar', NOW());