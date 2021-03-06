<p align="center">
  <a title="Globant" target="_blank" href= "https://github.com/emae1712/final-project-globant">
    <img width=400px src="https://user-images.githubusercontent.com/68023969/124045972-7ee38200-d9d6-11eb-8e54-14bb8484d61e.png" alt="Globant">
  </a>
</p>

# Back in the Game - Java Project 👩‍💻
### Made by [Arango M.](https://github.com/emae1712) & [Rodas S.](https://github.com/SheillyR)
>CRUD Library
## Index

* [1. Resumen del proyecto](#1-resumen-del-proyecto)
* [2. Definición del producto](#2-definición-del-producto)
* [3. Historias de usuario](#3-historias-de-usuario)
* [4. Diseño de Interfaz de Usuario](#4-diseño-de-interfaz-de-usuario)
* [5. Test de usabilidad](#5-Test-de-usabilidad)
* [6. Objetivos de aprendizaje](#6-Objetivos-de-aprendizaje)

## Overview

## Schema

<p align="center">
  <img width=700px src="https://user-images.githubusercontent.com/68023969/124052723-9b86b680-d9e4-11eb-8ae1-dcd9bb978ddc.JPG" alt="Globant">
</p>

## Architecture
 
 1. **Model:**
 2. **Controller:** is the presentation layer where the end points are located
 3. **Service:** is the service layer where the business logic resides
 4. **Repository:** is the persistence layer where the CRUD repository is located
 5. **Exception:**
 6. **Configuration:**
 
 ## Technologies
 
 <p >
  <img align="right" height="180px" src="https://user-images.githubusercontent.com/68023969/124051684-90328b80-d9e2-11eb-8ad6-0e960fdcb4a5.png">
</p>

1. Spring Boot (spring-boot-starter-web, spring-boot-starter-tomcat, spring-boot-starter-test, spring-boot-starter-data-couchbase)
2. Java 11
3. Tomcat 8.5.x
6. Gradle

## Tests

 1. **Integration Test (for the Controller):** it uses the Spring Boot Test framework with mockMvc and Jupyter
 2. **Unit Test (for the Service):** it uses the Mockito framework with hamcrest matchers, mock and injectMocks annotations 
 
 ## Developed methods
 
 ```
http://localhost:8081/api/book
```
 
 **HTTP Method: GET**
 
 *_Get all books_*

```
http://localhost:8081/api/book/getAllBooks
```
 
 *_Get book by Id_*

```
http://localhost:8081/api/book/getBookById/{bookId}
```
  
 *_Get books by State_*

```
http://localhost:8081/api/book/getBookByState/{state}
```

 **HTTP Method: POST**
 
*_Create book_*

```
http://localhost:8080/api/book/createBook

```
```
{
  "title": "Title A",
  "author": "Anonymous",
  "editorialYear": 2000,
  "state": "AVAILABLE",
  "reservation": null
}
```
 **HTTP Method: PUT**
 
*_Update book by Id_*
```
http://localhost:8080/api/book/updateBook/{bookId}
```
```
{
  "title": "Title B",
  "author": "Somebody",
  "editorialYear": 2021,
  "state": "AVAILABLE",
  "reservation": null
}
```
*_Update reservation by book Id_*
```
http://localhost:8080/api/book/updateReservationByBookId/{bookId}
```
```
{
  "startDate": "10-05-2021",
  "endDate": "26-06-2021"
}
```
**HTTP Method: DELETE**
 
 *_Delete book by Id_*

```
http://localhost:8081/api/book/deleteBookById/{bookId}
```
