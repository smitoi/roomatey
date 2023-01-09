### Description

A simple backend for a minimal collaborative to do application, made as a project for the Java Web Technologies laboratory at FMI @ Unibuc.

### User Stories

#### Note: crossed out stories are ones that will not be implemented for the MVP 

- [x] As a guest I want to register.
- [x] As a user I want to log in.
- [x] As a user I want to create a group.
- [x] As a user I want to invite a guest to join a group I belong to.
- [x] As a user I want to invite another user to a group I belong to.
- [ ] ~~As a guest I want to accept the invite by registering.~~
- [ ] ~~As a user I want to accept an invitation.~~
- [x] As a user I want to post a note to a group I belong to.
- [ ] As a user I want to post a task to a group I belong to.
- [ ] As a user I want to mark a task as complete.
- [ ] ~~As a user I want to post an event to a group I belong to.~~
- [x] As a user I want to search through notes.
- [ ] As a user I want to search through tasks.
- ~~[ ] As a user I want to search through events.~~

### Goals

- [x] One repository per entity
- [x] At least 5 endpoints (for all the features in the MVP)
- [ ] Beans for services, one service per feature.
- [x] At least one interface implemented by minimum two classes.
- [x] Data persisted in the database, at least 6 entities, at least 3 relations.
- [ ] Unit tests for all REST endpoints.
- [ ] Swagger documentation.
- [x] Custom exceptions, handle exception in ControllerAdvice.
- [x] Endpoints should return a ```ResponseEntity<Object>``` with relevant statuses.
- [x] Custom validation for fields.

### Resources

- DB Diagram: https://dbdiagram.io/d/63b7404b7d39e42284e93711
- Security in Spring Boot and JWT example: https://github.com/ali-bouali/spring-boot-3-jwt-security