# Tienda de Vinilos - Arquitectura de Microservicios

## Contexto del Proyecto
Sistema distribuido para la gestion de una tienda de vinilos online, implementado con una arquitectura basada en microservicios utilizando Spring Boot, comunicacion REST (Feign) y un API Gateway centralizado.

## Integrantes
* Vicente Vergara
* Ignacia Aravena
* Jaime Lopez
* Roselin Verdu


- eureka (Puerto 8079)
- api-gateway (Puerto 8080)

## Microservicios Implementados
1. cliente-service (Puerto 8081)
2. producto-service (Puerto 8082)
3. pedido-service (Puerto 8083)
4. Pago-service (Puerto 8084)
5. envio-service (Puerto 8085)
6. inventario-service (Puerto 8086)
7. catalogo-service (Puerto 8087)
8. carrito-service (Puerto 8088)
9. resena-service (Puerto 8089)
10. notificacion-service (Puerto 8090)

## Rutas Principales del API Gateway
* Clientes: http://localhost:8080/api/clientes/**
* Productos: http://localhost:8080/api/productos/**
* Pedidos: http://localhost:8080/api/pedidos/**
* Pagos: http://localhost:8080/api/pagos/**
* Envios: http://localhost:8080/api/envios/**

## Documentacion Swagger / OpenAPI
* Local (Ejemplo Cliente): http://localhost:8081/swagger-ui.html
* Remota: https://glistening-patience-production-2de5.up.railway.app/swagger-ui.html

## Cobertura de Pruebas Unitarias
El proyecto cuenta con pruebas unitarias implementadas con JUnit y Mockito, asegurando la validacion de reglas de negocio en la capa Service y superando el 80% de cobertura exigido.

## Instrucciones de Ejecucion Local
1. Clonar el repositorio.
2. Abrir los proyectos en el IDE.
3. Ejecutar comando `mvn clean install` en cada microservicio.
4. Levantar los microservicios de negocio.
5. Levantar el `api-gateway` al final.

## Instrucciones de Ejecucion Remota
1. Los servicios estan desplegados en contenedores usando Docker y la plataforma Railway.
2. URL Base del Gateway remoto: https://tienda-vinilos-ev3-production.up.railway.app
