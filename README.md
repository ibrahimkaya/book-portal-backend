# BOOK PORTAL BACK-END
Simple book portal back-end project,

- Java & Spring boot & MySQL & hibernate
- Frontend server is [Book Portal Frontend](https://github.com/ibrahimkaya/book-portal)


## Specifications
- authentication and authorization
- Login & Register
- Display Pageable Books, Authors and Users (admin only) 
- Add books to favorites and read list
- Search 
- Add, delete and Edits User & Book & Authors is admin only.
- Role based securiy with Spring security
- Paticular Books favorites or reads list

#### Enitity relations
- Users and Roles many to many [here](https://github.com/ibrahimkaya/book-portal-backend/blob/a65c817ab0cf183e3a99c5e5454c7d3af43f14f5/src/main/java/tr/com/obss/jss/week3spring/entity/User.java#L22) and [here](https://github.com/ibrahimkaya/book-portal-backend/blob/a65c817ab0cf183e3a99c5e5454c7d3af43f14f5/src/main/java/tr/com/obss/jss/week3spring/entity/Role.java#L19)
- Book and Users has many to many relations through to favorites and reads lists [here](https://github.com/ibrahimkaya/book-portal-backend/blob/a65c817ab0cf183e3a99c5e5454c7d3af43f14f5/src/main/java/tr/com/obss/jss/week3spring/entity/User.java#L29) and [here](https://github.com/ibrahimkaya/book-portal-backend/blob/a65c817ab0cf183e3a99c5e5454c7d3af43f14f5/src/main/java/tr/com/obss/jss/week3spring/entity/Book.java#L25)
- Book and Authors one to many [here](https://github.com/ibrahimkaya/book-portal-backend/blob/a65c817ab0cf183e3a99c5e5454c7d3af43f14f5/src/main/java/tr/com/obss/jss/week3spring/entity/Book.java#L19) and [here](https://github.com/ibrahimkaya/book-portal-backend/blob/a65c817ab0cf183e3a99c5e5454c7d3af43f14f5/src/main/java/tr/com/obss/jss/week3spring/entity/Author.java#L16)


### Api Documentation
- [Swagger ui api doc](http://localhost:8081/swagger-ui.html#/) (required run the project locally)

### Tech
Library uses a couple of open source projects to work properly:
* [Spring Framework](https://spring.io/) - Dependency Injection Container
* [Maven](https://maven.apache.org/) - Project Management Tool
* [MySQL](https://www.mysql.com/) - Relational Database Management System

#### Security and Cors control
- [web security config](https://github.com/ibrahimkaya/book-portal-backend/blob/master/src/main/java/tr/com/obss/jss/week3spring/config/WebSecurityConfig.java)
- Edit [this](https://github.com/ibrahimkaya/book-portal-backend/blob/a65c817ab0cf183e3a99c5e5454c7d3af43f14f5/src/main/java/tr/com/obss/jss/week3spring/config/WebSecurityConfig.java#L87) cors config method for more specialize request domain permission.


### Installation
 - Create MySQL User & Database
```sh

$ CREATE DATABASE jss CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

```
Or Change These lines
* [Connection String](https://github.com/ibrahimkaya/book-portal-backend/blob/edb62c83a6fffec13c90b15ab513584d66d8dc09/src/main/resources/application.yml#L9)
* [DB Username](https://github.com/ibrahimkaya/book-portal-backend/blob/edb62c83a6fffec13c90b15ab513584d66d8dc09/src/main/resources/application.yml#L7)
* [DB Password](https://github.com/ibrahimkaya/book-portal-backend/blob/edb62c83a6fffec13c90b15ab513584d66d8dc09/src/main/resources/application.yml#L8)


### Run
```sh
$ mvn clean spring-boot:run
```

### Login Page
- Pre defined user didn't included, add manualy or [add here to user create code](https://github.com/ibrahimkaya/book-portal-backend/blob/edb62c83a6fffec13c90b15ab513584d66d8dc09/src/main/java/tr/com/obss/jss/week3spring/config/DataLoader.java#L30) with 'ROLE_ADMIN' 
- this is form login, if you try with postman select  x-wwww-form-urlencoded or add request heder "Content-Type": "application/x-www-form-urlencoded".
[http://localhost:8081/login](http://localhost:8081/login)

### Authors
* [İbrahim Kaya](https://github.com/ibrahimkaya)


