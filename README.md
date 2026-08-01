# Habits Tracker API

Tracker for your habits

## Stack

Java21, Spring Boot 4.1.0, Spring Data JPA, PostgreSQL 16, Docker, Maven, Spring Security, JWT (jjwt)

## Start

1. Clone repository
2. Copy `.env.example` to `.env` and set the password
3. Set the same value as DB_PASSWORD environment variable in your IDE run configuration
4. Set the same value as JWT_SECRET environment variable in your IDE run configuration (min 32 symbols)
5. `docker compose up -d` - up postgres
6. `./mvnw spring-boot:run` or start from IDE
7. App is available on http://localhost:8081

## API

| Method | Path                     | Description         | Auth | Status             |
|--------|--------------------------|---------------------|------|--------------------|
| POST   | /api/auth/register       | Register a new user | No   | 201, 400, 409      |
| POST   | /api/auth/login          | Get JWT token       | No   | 200, 400, 401      |
| POST   | /api/habits              | Make habit          | Yes  | 201, 400, 401      |
| GET    | /api/habits              | Get habits          | Yes  | 200, 401           |
| GET    | /api/habits/{/id}        | Get habit by id     | Yes  | 200, 404, 401      |
| PUT    | /api/habits              | Update habit        | Yes  | 200, 400, 404, 401 |
| DELETE | /api/habits              | Delete habit        | Yes  | 204, 404, 401      |
| POST   | /api/habits/{id}/entries | Add hanit entry     | Yes  | 201, 404, 409, 401 |

## Authentication

All endpoints must have Authorization: Bearer "token", you can get token after login, token lives an hour 

## Examples

### Create a habit

Request:
```http
POST /api/habits
Content-Type: application/json

{
  "name": "Reading",
  "description": "30 pages"
}
```

Response `201 Created`:
```json
{
  "id": 1,
  "name": "Reading",
  "description": "30 pages",
  "createdAt": "2026-07-29T05:13:11.133"
}
```