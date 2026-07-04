# Spond Club Registration

## Stack

- Backend: Java 21, Spring Boot, Spring Data JPA, Flyway, PostgreSQL, MapStruct
- Frontend: React, TypeScript, Vite, MUI, Zod, Playwright, React form hook
- Local infrastructure: Docker Compose

## Technology Choices
 - The backend is built with Spring Boot. It has all the boilerplate like server setup, config, and DI out of the box.
 - Using Flyway for db migration and Spring Boot supports it out of the box.
 - API is defined by OpenAPI spec. API Interface and DTOS were generated during the maven build. This allowed me to start the development with API first approach.
 - Mapstruct is used for mapping which allows me to write less code to map JPA entities and API model.
 - React with typescript used for frontend. MUI is used as component library which supports responsiveness. Used Zod validation for client side validation.

## Run Entire Project With Docker

From the project root:

```bash
docker compose up --build
```

Services:

- Frontend: `http://localhost:3000/?formId=B171388180BC457D9887AD92B6CCFC86`
- Backend: `http://localhost:8080`
- PostgreSQL: `localhost:5432`

Reset the database:

```bash
docker compose down -v
docker compose up --build
```

## Run Each Service Separately

Start PostgreSQL:

```bash
docker compose up -d postgres
```

Run backend:

```bash
cd backend
mvn spring-boot:run
```

Run frontend:

```bash
cd frontend
yarn install
yarn dev
```

Local frontend URL:

```text
http://localhost:5173/?formId=B171388180BC457D9887AD92B6CCFC86
```

## Backend Configuration

located in `backend/src/main/resources/application.yaml`.

Local backend values:

```text
DB_URL=jdbc:postgresql://localhost:5432/membership
DB_USERNAME=user
DB_PASSWORD=test
SERVER_PORT=8080
```

Docker Compose uses the same database credentials, but the backend connects to PostgreSQL by service name:

```text
DB_URL=jdbc:postgresql://postgres:5432/membership
DB_USERNAME=user
DB_PASSWORD=test
SERVER_PORT=8080
```

## Frontend Configuration

```bash
VITE_API_BASE_URL=http://localhost:8080
```

## Other Commands

Run backend unit tests:

```bash
cd backend
mvn -Dtest=SignupFormServiceImplTest,SignupRegistrationServiceImplTest test
```

Run all backend tests:

```bash
cd backend
mvn test
```

Frontend build:

```bash
cd frontend
yarn build
```

Run E2E tests:

```bash
cd frontend
yarn test:e2e
```
## API

The API contract is defined in:

```text
backend/src/main/resources/api/open-api.yaml
```

OpenAPI Generator runs as part of the Maven build and generates the Spring API interfaces and API models.

Get signup form:

```http
GET /api/forms/{formId}
```

Submit registration:

```http
POST /api/forms/{formId}/submissions
```

Sample form ids:

- Past/open form: `B171388180BC457D9887AD92B6CCFC86`
- Future form: `F171388180BC457D9887AD92B6CCFC87`

## Database

Flyway migrations are in:

```text
backend/src/main/resources/db/migration
```

## Swagger URL
```http
http://localhost:8080/swagger-ui/index.html
```

## Suggested Improvements

- Increase test coverage of backend test, specially around validation scenarios.
- Add frontend unit tests.
- Adjust the constraints in entity classes ( ex: max length)
- In a real project set up, fine tune the API spec.
- Revisit the folder structure (ex: splitting exceptions per service and API layer)
- CI / CD integration.
