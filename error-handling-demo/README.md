# Error Handling Application - Stub Backend Only

This application compares the same Create Book operation with and without frontend error handling.

## Architecture

The application is stub-only.

- No PostgreSQL / MySQL / H2 database
- No JPA entities
- No Spring Data repositories
- No JDBC
- No datasource configuration
- No Flyway migrations
- No persistent store
- Stub backend runs on port **8082**
- Frontend runs on port **5173**

The Spring Boot controller returns deterministic responses based on request values.

## Application navigation

Open:

`http://localhost:5173`

The index page lists the error-handling categories. Every category has exactly two application pages:

- **Without Error Handling**
- **With Error Handling**

There is no intermediate instruction/comparison page. Each category card links directly to the two application pages.

## Categories

- Client-Side Validation
- Backend Validation - HTTP 400
- Business Conflict - HTTP 409
- Authentication Error - HTTP 401
- Authorization Error - HTTP 403
- Not Found - HTTP 404
- Server Error - HTTP 500
- Loading State
- Network Failure
- Success Feedback

## Common API contract

Except for Network Failure, both pages use:

`POST /rest/demo/books`

Network Failure uses an intentionally unreachable endpoint so that no HTTP response is received.

## Stub trigger values

- Missing title -> HTTP 400
- Invalid accession such as `BAD-2103` -> HTTP 400
- `ACC-0001` -> HTTP 409
- Title `SESSION EXPIRED` -> HTTP 401
- Title `FORBIDDEN BOOK` -> HTTP 403
- Title `MISSING BOOK` -> HTTP 404
- Title `SERVER ERROR` -> HTTP 500
- Title `SLOW BOOK` -> delayed HTTP 201
- Other valid values using `ACC-0000` format -> HTTP 201

## Run the backend

```bash
cd error-handling-demo/backend
mvn spring-boot:run
```

Backend: `http://localhost:8082`

## Run the frontend

```bash
cd error-handling-demo/frontend
npm install
npm run dev
```

Frontend: `http://localhost:5173`

Vite proxies `/rest` to the Spring Boot stub backend on port **8082**.
