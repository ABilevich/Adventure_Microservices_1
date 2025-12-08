This project was made to learn the basics regarding microservices.

The simple architecture is as follows

- Order Service: Used to create orders
  - MySQL database
- Inventory Service: keeps track of inventory (MySQL database)
- Product Service: Manages product creation and fetching (MongoDB Database)

The idea is to test both event based and synchronous communication between microservices.

- Api Gateway:
  - Acts as an entrypoint to the requests (Spring Cloud Gateway)
  - Implements security with KeyCloak

Technologies

Keycloak

- Manages security on the API gateway
- Allows us to implement OAuth very simply
