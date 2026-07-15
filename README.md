# SpringDataJPADemo

A Spring Boot REST API demo application using Spring Data JPA for database access. Manages **Users** and **Orders** with a one-to-many relationship.

## Tech Stack

- Java 17
- Spring Boot 4.1.0
- Spring Data JPA / Hibernate
- PostgreSQL
- Lombok
- Maven

## Prerequisites

- JDK 17+
- PostgreSQL running locally
- Maven (or use `./mvnw`)

## Getting Started

1. **Create the database:**

   ```sql
   CREATE DATABASE testdb;
   ```

2. **Configure credentials** in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.username=postgres
   spring.datasource.password=pass
   ```

3. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

   The app starts on port **8080**.

## API Endpoints

### Users — `/api/v1/users`

| Method   | Endpoint                    | Description               |
|----------|-----------------------------|---------------------------|
| `POST`   | `/api/v1/users`             | Create a user             |
| `GET`    | `/api/v1/users`             | Get all users             |
| `GET`    | `/api/v1/users/paginated`   | Get users (paginated)     |
| `GET`    | `/api/v1/users/{id}`        | Get user by ID            |
| `PUT`    | `/api/v1/users/{id}`        | Update a user             |
| `PATCH`  | `/api/v1/users/{id}`        | Partially update a user   |
| `DELETE` | `/api/v1/users/{id}`        | Delete a user             |

**Pagination params:** `page`, `pageSize`, `direction` (asc/desc), `sortBy`

### Orders — `/api/v1/users/{userId}/orders`

| Method | Endpoint                          | Description          |
|--------|-----------------------------------|----------------------|
| `POST` | `/api/v1/users/{userId}/orders`   | Create an order      |
| `GET`  | `/api/v1/users/{userId}/orders`   | Get orders for user  |

## Project Structure

```
src/main/java/com/example/SpringDataJPADemo/
├── entities/       # JPA entity classes (Users, Orders)
├── repository/     # Spring Data JPA repositories
├── services/       # Business logic layer
├── controllers/    # REST API controllers
└── dto/            # Request/Response DTOs
```

## Database Schema

- **users** — `id` (BIGINT, PK), `name` (VARCHAR), `email` (VARCHAR)
- **orders** — `id` (BIGINT, PK), `product_name` (VARCHAR), `user_id` (BIGINT, FK → users)

Hibernate auto-updates the schema on startup (`ddl-auto=update`).
