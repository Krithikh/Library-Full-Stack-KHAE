# Frontend-backend-Baseline

This branch is the student fork baseline. It contains the presenter-owned application foundation and the documents used to keep all 60 student tracks synchronized.

## Runtime flow

REST Controller -> Service -> DTO/DO Mapper -> DAO -> PostgreSQL

The REST service implementations in this baseline are presenter STUBs. Students replace only the service assigned to their track while partner endpoints remain usable through the fixed `/rest/...` contract.

The backend also contains presenter-owned Thymeleaf list and Read-by-ID pages under `/library`. These pages read the real database through Presenter View Service -> Mapper -> DAO -> PostgreSQL so students can see a running server-rendered application alongside the separate Vite frontend.

## Start PostgreSQL

Create databases `library_full_stack` and `library_full_stack_test`, then update credentials in `backend/backend.lib.mgmt/src/main/resources/application.properties` if required.

## Start backend

From `backend/backend.lib.mgmt` run the Spring Boot application in Eclipse, or use the Maven wrapper.

Backend: `http://localhost:8080`
Thymeleaf: `http://localhost:8080/library`

## Start frontend

From `frontend/frontend.lib.mgmt`:

```text
npm install
npm run dev
```

Vite: `http://localhost:5173`

The Vite dev server proxies `/rest` to the backend, so no browser CORS configuration is required for this baseline.

## Important baseline rule

Generic presenter-owned application exception handling is present in this accepted baseline so student services can return the standardized controlled response codes used by the exercises. The shared infrastructure covers the agreed controlled-error categories, while students still implement the business validation and error decisions inside their assigned service.

Students must not duplicate or replace the shared exception infrastructure unless the exercise explicitly requires it. Their task is to implement the assigned service behavior against the fixed `/rest/...` contract and the shared error-response mechanism.
