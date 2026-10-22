# Employee Management System

A Spring Boot CRUD application for managing employees (name, email, phone, department, salary).
Data is stored in a MySQL database.  

## Features

- Add, view, edit and delete employees through a web page (Thymeleaf)
- Input validation (required fields, valid email, 10-digit phone, positive salary)
- Duplicate email detection
- Search employees by name
- REST API at `/api/employees` for testing with Postman

## Technologies

Java 17+, Spring Boot, Spring Data JPA (Hibernate), MySQL / MariaDB (XAMPP), Thymeleaf,
Lombok, Maven

## Prerequisites

- JDK 17 or later
- XAMPP (MySQL module) or MySQL Server 8.x
- Git, and Postman for API testing

## How to run

1. Clone the repository:
```bash
   git clone https://github.com/Varushiny/employee-manager-springboot.git
   cd employee-manager-springboot/employee-manager
```
2. Start MySQL (XAMPP control panel, MySQL module) and create the database:
```sql
   CREATE DATABASE employee_db;
```
3. Copy `secrets.properties.example` to `secrets.properties` and set your MySQL username
   and password (leave the password empty for a default XAMPP install).
4. Run the application:
```bash
   ./mvnw spring-boot:run
```
   On Windows PowerShell use `.\mvnw spring-boot:run`.
5. Open http://localhost:8081 (or the port set in `application.properties`).

## REST API

| Method | URL | Purpose |
|---|---|---|
| GET | `/api/employees` | List all employees |
| GET | `/api/employees/{id}` | Get one employee |
| POST | `/api/employees` | Create an employee |
| PUT | `/api/employees/{id}` | Update an employee |
| DELETE | `/api/employees/{id}` | Delete an employee |

Example request body:

```json
{
  "fullName": "Nimal Perera",
  "email": "nimal@example.com",
  "phone": "0771234567",
  "department": "Information Technology",
  "salary": 70000
}
```

## Author

Varushiny Gunaretnam (EC/2022/078)