-- Tienda de Vinilos - Arquitectura de Microservicios

-- Contexto del Proyecto
Sistema distribuido para la gestion de una tienda de vinilos online, implementado con una arquitectura basada en microservicios utilizando Spring Boot, comunicacion REST (Feign) y un API Gateway centralizado.

-- Integrantes
* [Tu Nombre y Apellido]
* [Nombre y Apellido Compañero 2]
* [Nombre y Apellido Compañero 3]

-- Microservicios Implementados
1. api-gateway (Puerto 8080)
2. cliente-service (Puerto 8081)
3. producto-service (Puerto 8082)
4. pedido-service (Puerto 8083)
5. pago-service (Puerto 8084)
6. envio-service (Puerto 8085)
7. inventario-service (Puerto 8086)
8. catalogo-service (Puerto 8087)
9. carrito-service (Puerto 8088)
10. resena-service (Puerto 8089)
11. notificacion-service (Puerto 8090)

-- Rutas Principales del API Gateway
* Clientes: http://localhost:8080/api/clientes/**
* Productos: http://localhost:8080/api/productos/**
* Pedidos: http://localhost:8080/api/pedidos/**
* Pagos: http://localhost:8080/api/pagos/**
* Envios: http://localhost:8080/api/envios/**

-- Documentacion Swagger / OpenAPI
* Local (Ejemplo Cliente): http://localhost:8081/swagger-ui.html
* Remota: [URL_REMOTA_AQUI]/swagger-ui.html

-- Cobertura de Pruebas Unitarias
El proyecto cuenta con pruebas unitarias implementadas con JUnit y Mockito, asegurando la validacion de reglas de negocio en la capa Service y superando el 80% de cobertura exigido.

-- Instrucciones de Ejecucion Local
1. Clonar el repositorio.
2. Abrir los proyectos en el IDE.
3. Ejecutar comando `mvn clean install` en cada microservicio.
4. Levantar los microservicios de negocio.
5. Levantar el `api-gateway` al final.

-- Instrucciones de Ejecucion Remota
1. Los servicios estan desplegados en contenedores usando Docker y la plataforma [Railway/Render].
2. URL Base del Gateway remoto: [URL_GATEWAY_REMOTA_AQUI]