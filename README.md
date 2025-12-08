# Microservices Project

This project was made to learn the basics regarding microservices.
The idea is to test both event based and synchronous communication between microservices.

## Architecture

The simple architecture is as follows

- Order Service: Used to create orders
  - Database: MySQL
- Inventory Service: keeps track of inventory
  - Database: MySQL
- Product Service: Manages product creation and fetching
  - Database: MongoDB
- Api Gateway (Spring Cloud Gateway):
  - Acts as an entrypoint to the requests.
  - Implements security with KeyCloak using barer tokens (Oauth2)

## Technologies

### Keycloak

- Manages security on the API gateway
- Allows us to implement OAuth very simply
- Requires us to generate a barer token using oauth2 before requesting to our api gateway

## Things I learned

- Spring boot
  - New Annotations
    - @Configuration
    - @Bean
  - Api gateways using Spring Boot Cloud
  - Spring security with Keycloak
  - Using MongoDB using MongoRepository
  - JPA repository semantic functions for MySQL
- Testing
  - Test containers to spin up a test database and service with random ports
  - WireMock to mock the response of an http connected microservice
  - Integration testing using RestAssured
- Security
  - Oauth/Oauth2
  - Keycloak
  - JWT / Barer tokens
- Microservices Concepts
  - Sync / Async communication
  - API Gateway
