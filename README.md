<p align="center">
  <a title="Globant" target="_blank" href= "https://github.com/emae1712/final-project-globant">
    <img width=400px src="https://user-images.githubusercontent.com/68023969/124045972-7ee38200-d9d6-11eb-8e54-14bb8484d61e.png" alt="Globant">
  </a>
</p>

# Back in the Game - Java Project 👩‍💻
### Made by [Arango M.](https://github.com/emae1712) & [Rodas S.](https://github.com/SheillyR)
>CRUD Library
## Index

* [1. Overview](#1-overview)
* [2. Configuration](#2-configuration)
* [3. Schema](#3-schema)
* [4. Architecture](#4-architecture)
* [5. Technologies](#5-technologies)
* [6. Developed methods](#6-Developed-methods)

## 1. Overview

## 2. Configuration

## 3. Schema

<p align="center">
  <img width=700px src="https://user-images.githubusercontent.com/68023969/124052723-9b86b680-d9e4-11eb-8ae1-dcd9bb978ddc.JPG" alt="Globant">
</p>

## 4. Architecture

<p >
  <img align="right" height="220px" src="https://user-images.githubusercontent.com/68023969/124568512-9ffb0700-de0a-11eb-9c21-8d922ca16651.JPG">
</p>
 
 1. **Model:**
 2. **Controller:** is the presentation layer where the end points are located
 3. **Service:** is the service layer where the business logic resides
 4. **Repository:** is the persistence layer where the CRUD repository is located
 5. **Exception:**
 6. **Configuration:**
 
 ## 5. Technologies
 
 <p >
  <img align="right" height="180px" src="https://user-images.githubusercontent.com/68023969/124051684-90328b80-d9e2-11eb-8ad6-0e960fdcb4a5.png">
</p>

- [x] Spring Boot (spring-boot-starter-web, spring-boot-starter-data-jpa, spring-boot-starter-test, spring-boot-starter-validation)
- [x] Java 11
- [x] Gradle
- [x] JPA
- [x] MySQL
- [x] JUnit and Mockito

### Tests

 1. **Integration Test (for the Controller):** it uses the Spring Boot Test framework with mockMvc and Jupyter
 2. **Unit Test (for the Service):** it uses the Mockito framework with hamcrest matchers, mock and injectMocks annotations 
 
 ## 6. Developed methods
 
 ```
http://localhost:8081/api/book
```
 
 <p >
  <img align="left" height= 30px src="https://user-images.githubusercontent.com/68023969/124558685-7c32c380-de00-11eb-84b8-cf2fdbd4f4c0.JPG">
</p>

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

<p >
  <img align="left" height= 30px src="https://user-images.githubusercontent.com/68023969/124558273-09c1e380-de00-11eb-9b3d-8f6e5a093b6a.JPG">
</p>

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

<p >
  <img align="left" height= 30px src="https://user-images.githubusercontent.com/68023969/124559128-f8c5a200-de00-11eb-9db3-180e6e6481c7.JPG">
</p>

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

<p >
  <img align="left" height= 30px src="https://user-images.githubusercontent.com/68023969/124559504-6a055500-de01-11eb-9952-a3a73d9b1d0e.JPG">
</p>

**HTTP Method: DELETE**
 
 *_Delete book by Id_*

```
http://localhost:8081/api/book/deleteBookById/{bookId}
```
