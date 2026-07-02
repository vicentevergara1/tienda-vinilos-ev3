CREATE TABLE IF NOT EXISTS notificaciones (
                                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            destinatario VARCHAR(150) NOT NULL,
                                            tipo_notificacion VARCHAR(50) NOT NULL,
                                            asunto VARCHAR(200) NOT NULL,
                                            mensaje TEXT NOT NULL,
                                            estado VARCHAR(30) NOT NULL DEFAULT 'PENDIENTE',
                                            fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                            fecha_envio TIMESTAMP NULL
                                            );

INSERT INTO notificaciones (destinatario, tipo_notificacion, asunto, mensaje, estado, fecha_envio)
VALUES ('cliente1@duocuc.cl', 'EMAIL', 'Confirmación de Compra',
        'Tu pedido de vinilos ha sido procesado con éxito.', 'ENVIADO', NOW());