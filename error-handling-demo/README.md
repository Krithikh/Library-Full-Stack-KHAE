# Error Handling Demo - Stub Backend Only

This private classroom application compares the same **Create Book** scenario with and without frontend error handling.

## Important architecture rule

The demo is intentionally **stub-only**.

- No PostgreSQL / MySQL / H2 database
- No JPA entities
- No Spring Data repositories
- No JDBC
- No datasource configuration
- No Flyway migrations
- No persistent in-memory data store

The Spring Boot controller returns deterministic teaching responses based only on the request values.

## Presenter navigation - no URL copying

Start both applications, then open only the frontend home page:

`http://localhost:5173`

The presenter should not copy or type separate scenario URLs.

The frontend home page now shows an **Error Handling Techniques** index. The classroom navigation is:

1. Choose an error-handling technique from the index.
2. The technique page explains the error/condition and expected handled result.
3. Click **Without Error Handling**.
4. Run the preloaded scenario and discuss the poor user experience.
5. Return to the technique page.
6. Click **With Error Handling**.
7. Run the same preloaded scenario and compare the corrected behaviour.
8. Use **Switch to ...** if a direct A/B comparison is useful.
9. Use **Error Handling Techniques** to return to the index.

The internal links preserve the selected technique automatically. Students and presenters do not need to know the query-string URLs.

## Techniques shown on the index

- Client-Side Validation
- Backend Validation - HTTP 400
- Business Conflict Handling - HTTP 409
- Authentication Error Handling - HTTP 401
- Authorization Error Handling - HTTP 403
- Not-Found Handling - HTTP 404
- Unexpected Server Error Handling - HTTP 500
- Loading State and Double-Submit Protection
- Network Failure Handling
- Success Feedback and Stable UI

## Common API contract

Except for the deliberately unreachable network-failure exercise, both comparison pages use the same normal endpoint:

`POST /rest/demo/books`

The scenario data is preloaded by the selected technique page. The backend contract stays the same; the frontend handling changes.

For the network-failure exercise, both pages intentionally call the same unreachable teaching endpoint so no HTTP response is received.

## Stub trigger values

- Missing title -> HTTP 400 if the request reaches the backend; the handled client-validation page stops before the request
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

Frontend home page: `http://localhost:5173`

Vite proxies `/rest` to the Spring Boot stub backend.
