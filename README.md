# Spring Boot CRUD Application 
![banner](./assets/banner.jpg)

## Introduction

### CRUD Application
I am using **Spring Boot** in implementing a CRUD ( **C**reate, **R**ead, **U**pdate, **D**elete) REST application where i am Adding Users & Doing operations on them *


## Project Structure
 Here is the Project Structure Explained.

The following are the base folders in which the project is organized and the purpose of each:
- [📁 application](User_app/src/main/java/com): contains the main class, annotated with `@SpringBootApplication`, which is responsible for booting up the application;
- [📁 configuration](User_app/src/main/java/com/User_app/configuration): contain the Security configuration classes annotated with `@Configuration`;
- [📁 controller](src/main/java/com/User_app/controller): contain classes annotated with `@RestController` responsible for processing incoming REST API requests;
- [📁 exception](src/main/java/com/User_app/exception): contain custom exceptions for handling specific data consistent and/or business rule violations;
- [📁 entity](src/main/java/com/User_app/entity): contain POJO classes (a.k.a. **P**lain **O**ld **J**ava **O**bject) annotated with `@Entity` representing database entities i.e., classes mapping database tables;
- [📁 repository](src/main/java/com/User_app/repository): contain classes annotated with `@Repository` responsible for providing the mechanism for storage, retrieval, search, update and delete operation on objects usually present in a database;
- [📁 service](src/main/java/com/User_app/service): contains class annotated with `@Service` in which business logic is implemented;
- [📁 securit](src/main/java/com/User_app/securities): contains classes which are responsible for JWT Filters, Entry points & Authentications;

## Booting Up the Application
<ol>
<li>Clone the repository:</li>
  <code>git clone https://github.com/deepak8299/User_app.git</code>
<li>Navigate to the folder:</li>
  <code>src/main/java/com/User_app</code>
<li>Run the application:</li>
  <code>mvn spring-boot:run</code>
</ol>


## Testing the Application
1. `Create New User`
- URL: http://127.0.0.1:8080/api/v1/apps
- HTTP Method: POST
- Body:
  ````json
  {
    "appName": "netflix",
    "appVersion": "0.0.0",
    "devName": "David Archanjo"
  }
  ````
  ![createNewApp](./assets/createNewApp.jpg)
 

2. `Get App by ID`
- URL: http://127.0.0.1:8080/api/v1/apps/{appId} 
- HTTP Method: GET
  ![getAppById](./assets/getAppById.jpg)
 

3. `Update App`
- HTTP Method: PUT
- URL: http://127.0.0.1:8080/api/v1/apps/{appId}
- Body:
  ````json
  {
    "appName": "netflix",
    "appVersion": "1.0.0",
    "devName": "David Archanjo"
  }
  ````
  ![updateApp](./assets/updateApp.jpg)

4. `Delete App`
- HTTP Method: DELETE
- URL: http://127.0.0.1:8080/api/v1/apps/{appId}
  ![banner](./assets/deleteApp.jpg)
  
  If we try to look up the deleted application by its id we will get an HTTP 404 status code response:
  ![banner](./assets/getAppById404.jpg)


## Unit Test
To Unit Test my Application i have Used combination of **JUnit 5** & **Mockito** + some [controller](./src/main/java/com/User_app/controller) and [Service](./src/main/java/com/User_app/service) tests were implemented.

