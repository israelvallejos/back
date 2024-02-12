News Platform with Angular and Spring Boot

Description

This project is a news platform that allows users to view, search, and save their favorite news articles. It consists of an Application developed with Angular for the web interface (hosted in the pruebaFront repository) and a Spring Boot microservice for backend operations (hosted in the pruebaback repository).

Prerequisites

JDK 11 or newer
Maven 3.6.3 or newer
Node.js and npm
Setup and Installation

Backend Setup
Clone the backend repository:
sh
Copy code
git clone https://github.com/israelvallejos/pruebaback.git
Navigate to the backend project directory:
sh
Copy code
cd pruebaback
Run the Spring Boot application:
sh
Copy code
mvn spring-boot:run
Frontend Setup
Clone the frontend repository:
sh
Copy code
git clone https://github.com/israelvallejos/pruebaFront.git
Navigate to the frontend project directory:
sh
Copy code
cd pruebaFront
Install the dependencies:
sh
Copy code
npm install
Start the Angular application:
sh
Copy code
ng serve
API Documentation

The API documentation for the Spring Boot microservice is available at http://localhost:8080/swagger-ui.html, detailing the endpoints, request bodies, and responses.

Technologies Used

Frontend: Angular/TypeScript
Backend: Java with Spring Boot, H2 Database
Documentation: Swagger/OpenAPI
