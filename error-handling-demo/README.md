# Error Handling Demo - Library Management

This private teaching demo compares the same **Create Book** workflow in two frontend screens.

## Screen 1 - Without Error Handling

This screen intentionally demonstrates poor frontend behaviour. It sends the form directly to the backend without frontend validation, without a loading state, without field-level messages, and without converting backend failures into useful user feedback. Students can observe that the browser console receives technical information while the user receives little or no guidance.

## Screen 2 - With Error Handling

This screen demonstrates the recommended implementation. It validates required fields, disables the Save button while the request is running, shows loading feedback, maps the backend standard error response to field/global messages, displays success feedback, logs technical details for developers, and restores the UI in a `finally` block.

## Backend scenarios

The Spring Boot endpoint is `POST /rest/demo/books`.

- Valid data -> HTTP 201 and a success response.
- Missing title or accession number -> HTTP 400 with a standard field error.
- Accession number `ACC-0001` -> HTTP 409 duplicate error.
- Title `SERVER ERROR` -> HTTP 500 simulated backend failure.
- Title `SLOW BOOK` -> delayed response so students can observe loading feedback.

The backend uses an in-memory teaching store so the demo is self-contained. It still exposes a consistent API error contract that mirrors how a real Library Management backend should communicate validation, conflict, and server failures.

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

Vite proxies `/rest` to the Spring Boot backend.

## Suggested classroom demonstration

1. Open **Without Error Handling** and submit an empty form. Ask students what the user learns from the screen.
2. Submit accession number `ACC-0001`. Observe the generic result and browser console.
3. Open **With Error Handling** and repeat the same inputs. Compare field highlighting and meaningful messages.
4. Use title `SLOW BOOK` to show the disabled button and loading message.
5. Use title `SERVER ERROR` to compare a raw failure with a safe user message.
6. Stop the backend and compare the network-failure experience on both screens.

This branch is for private classroom demonstration and is not intended for public classroom publication without an explicit promotion decision.
