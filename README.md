# RPG Mission System

RPG Mission System is a backend application designed to manage quests, characters, and progression for tabletop RPG-style campaigns.  
It provides a structured way to handle missions, track player progress, and distribute rewards such as experience and gold.

The system is built with Spring Boot and follows a layered architecture to ensure scalability and maintainability.

---

## Features

- User authentication using JWT
- Role-based access control (ADMIN / USER)
- Character management per player
- Mission creation and configuration
- Quest lifecycle management:
  - PENDING
  - IN_PROGRESS
  - COMPLETED
  - FAILED
- Reward system based on missions (experience and gold)
- Character progression tracking

---

## Tech Stack

- Java 11+
- Spring Boot
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- MySQL / MariaDB
- Lombok
- Maven

---

## Architecture

The project follows a standard layered architecture:

Controller → Service → Repository → Database
DTOs → Mappers → Response Layer


Business logic is handled inside the service layer, while controllers are responsible only for handling requests and responses.

---

## Domain Overview

### Player
Represents a user of the system. A player can own multiple characters.

### Character
Represents the in-game entity controlled by a player.  
Tracks level, experience, and rewards.

### Mission
Defines a quest that can be assigned to characters.  
Includes level restrictions and rewards.

### QuestLog
Tracks the state of a character inside a mission:

- PENDING
- IN_PROGRESS
- COMPLETED
- FAILED

### Rewards
Rewards are applied when a mission is completed successfully.  
They can include experience points and gold.

---

## Main Flow

1. An ADMIN creates missions
2. A ADMIN assigns a character to a mission
3. A QuestLog entry is created with status `PENDING`
4. The mission is started (`IN_PROGRESS`)
5. The mission is completed or failed
6. Rewards are applied automatically on completion

---

## API Endpoints

### Authentication
POST /auth/register
POST /auth/login

### Players
...

### Missions / Quest System

POST /quest-log/add-character-to-mission
PUT /quest-log/start-mission
PUT /quest-log/complete-mission
PUT /quest-log/end-mission

## Setup

### 1. Clone repository

https://github.com/rg-developer/RPG_Mission_System


### 2. Configure database
Update `application.properties`:

You need to create a empty database.
BD name: rpg_mission_system

spring.datasource.url=jdbc:mysql://localhost:3306/rpg_mission_system
spring.datasource.username=root
spring.datasource.password=your_password

### 3. Run project

mvn clean install
mvn spring-boot:run

---
### 4. Run Swagger Documentation

In explorer: http://localhost:8080/swagger-ui/index.html

## Security

The application uses JWT-based authentication (Bearer Tokens).

- Tokens are required for protected endpoints
- ADMIN role is required for administrative actions
- USER role can access gameplay-related endpoints

0. Key and Secret Included in github in this case.
---

## Future Improvements

- Advanced reward system (items, equipment)
- Skill system for characters
- Quest dependencies (chain missions)
- Frontend application (Angular)
- Full test coverage (unit + integration tests)

---

## Project Purpose

This project was built as a learning-focused backend system to simulate real-world application design using Spring Boot, with an emphasis on clean architecture, security, and scalable game-like logic.

