# Error Handling Demo - Stub Backend Only

This private classroom application compares the same **Create Book** operation with and without frontend error handling.

## Important architecture rule

The demo is intentionally **stub-only**.

- No PostgreSQL / MySQL / H2 database
- No JPA entities
- No Spring Data repositories
- No JDBC
- No datasource configuration
- No Flyway migrations
- No persistent in-memory data store

The Spring Boot controller returns deterministic teaching responses based only on the request values. This makes every error reproducible during the presentation.

## Common API contract

Both frontend screens call the same normal endpoint:

`POST /rest/demo/books`

The frontend behaviour changes; the backend URL does not.

## Direct presenter URLs

Run the frontend at `http://localhost:5173` and open a pair of URLs for each scenario.

| Scenario | Without handling | With handling |
|---|---|---|
| Success | `http://localhost:5173/?mode=without&scenario=success` | `http://localhost:5173/?mode=handled&scenario=success` |
| Client validation | `http://localhost:5173/?mode=without&scenario=client-validation` | `http://localhost:5173/?mode=handled&scenario=client-validation` |
| Backend validation 400 | `http://localhost:5173/?mode=without&scenario=backend-validation` | `http://localhost:5173/?mode=handled&scenario=backend-validation` |
| Duplicate 409 | `http://localhost:5173/?mode=without&scenario=duplicate` | `http://localhost:5173/?mode=handled&scenario=duplicate` |
| Session expired 401 | `http://localhost:5173/?mode=without&scenario=session-expired` | `http://localhost:5173/?mode=handled&scenario=session-expired` |
| Forbidden 403 | `http://localhost:5173/?mode=without&scenario=forbidden` | `http://localhost:5173/?mode=handled&scenario=forbidden` |
| Not found 404 | `http://localhost:5173/?mode=without&scenario=not-found` | `http://localhost:5173/?mode=handled&scenario=not-found` |
| Server error 500 | `http://localhost:5173/?mode=without&scenario=server-error` | `http://localhost:5173/?mode=handled&scenario=server-error` |
| Slow response | `http://localhost:5173/?mode=without&scenario=slow` | `http://localhost:5173/?mode=handled&scenario=slow` |
| Network failure | `http://localhost:5173/?mode=without&scenario=network` | `http://localhost:5173/?mode=handled&scenario=network` |

For the network case, both screens intentionally call the same unreachable teaching URL `http://localhost:65530/rest/demo/books`, so no HTTP response is received.

## Stub trigger values

- Missing title -> HTTP 400
- Invalid accession such as `BAD-2103` -> HTTP 400
- `ACC-0001` -> HTTP 409 duplicate
- Title `SESSION EXPIRED` -> HTTP 401
- Title `FORBIDDEN BOOK` -> HTTP 403
- Title `MISSING BOOK` -> HTTP 404
- Title `SERVER ERROR` -> HTTP 500
- Title `SLOW BOOK` -> three-second delay, then HTTP 201
- Other valid values using `ACC-0000` format -> HTTP 201

## Run the backend

```bash
cd error-handling-demo/backend
mvn spring-boot:run
```

Backend: `http://localhost:8080`

## Run the frontend

```bash
cd error-handling-demo/frontend
npm install
npm run dev
```

Frontend: `http://localhost:5173`

Vite proxies `/rest` to the Spring Boot stub backend.
