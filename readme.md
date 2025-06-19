# Insurance Policy API

This project is a simple backend API that allows you to manage insurance policies.  
It has been developed in Java using Spring Boot and supports basic CRUD operations.

## Features

- Create a policy
- List all policies
- Retrieve a policy by ID
- Update an existing policy

## Getting Started

### Clone the repository

```bash
git clone https://github.com/AhmedHORMAL/insurance-api.git
cd insurance-api
```

### Run the application

```bash
docker-compose up --build
```

This will start the application and a PostgreSQL database.

By default, the API is accessible on: `http://localhost:9001`

### Run integration tests

Integration tests are written using [Karate](https://github.com/karatelabs/karate).  
To run them:

```bash
mvn clean install
```

### API contract

The `com.tinubu.web` package (in the `app` module) is auto-generated from the OpenAPI contract file:

```
src/main/resources/static/openapi/contract.yaml
```

## API Examples (cURL)

### Create a policy

```bash
curl --location 'http://localhost:9001/policies' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Test Policy",
  "startDate": "2024-01-01T00:00:00Z",
  "endDate": "2025-01-02T00:00:00Z",
  "status": "ACTIVE"
}'
```

### Update a policy

```bash
curl --location --request PUT 'http://localhost:9001/policies/6466297703565378355' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Assurance 1",
  "status": "ACTIVE",
  "startDate": "2025-01-01T00:00:00Z",
  "endDate": "2026-01-01T00:00:00Z"
}'
```

### Get a policy by ID

```bash
curl --location 'http://localhost:9001/policies/1384793643833249'
```

### List all policies

```bash
curl --location 'http://localhost:9001/policies'
```

## Notes

- The API is public (no authentication required).
- Data is stored in a PostgreSQL database.
- The domain logic is separated using ports & adapters (hexagonal architecture).
