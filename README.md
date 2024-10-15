## ProductPriceService

Descripción General
ProductPriceService es una API RESTful desarrollada con Spring Boot para realizar consultas de precios de productos. La API permite a los usuarios consultar el precio de un producto basado en tres parámetros: fecha de aplicación, ID del producto e ID de la marca.

## Requisitos

- Java 17
- Maven
- JPA / Hibernate

## Clonar el repositorio:

git clone https://github.com/tuusuario/ProductPriceService.git

cd ProductPriceService

## Compilar el proyecto usando Maven:

mvn clean install

## Ejecución de la Aplicación

mvn spring-boot:run

## Base de Datos H2

- Accede a http://localhost:8080/h2-console
- Configura los siguientes valores:
- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (dejar en blanco)

## Endpoints de la API
- URL: /api/prices
- Método: GET
  
- Parámetros de Query:
- productId: ID del producto (Integer)
- brandId: ID de la marca (Integer)
- applicationDate: Fecha y hora para la consulta del precio en el formato yyyy-MM-dd'T'HH:mm:ss

## Ejemplo de Solicitud
GET /api/prices?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00

