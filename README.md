# Habits Tracker API

Tracker for your habits

## Stack

Java21, Spring Boot 4.1.0, Spring Data JPA, PostgreSQL 16, Docker, Maven.

## Start

1. Clone repository
2. Copy `.env.example` to `.env` and set the password
3. Set the same value as DB_PASSWORD environment variable in your IDE run configuration
4. `docker compose up -d` - up postgres
5. `./mvnw spring-boot:run` or start from IDE
6. App is available on http://localhost:8081

## API

| Method | Path                     | Description     | Status        |
|--------|--------------------------|-----------------|---------------|
| POST   | /api/habits              | Make habit      | 201, 400      |
| GET    | /api/habits              | Get habits      | 200           |
| GET    | /api/habits/{/id}        | Get habit by id | 200, 404      |
| PUT    | /api/habits              | Update habit    | 200, 400, 404 |
| DELETE | /api/habits              | Delete habit    | 204, 404      |
| POST   | /api/habits/{id}/entries | Add hanit entry | 201, 404, 409 |

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