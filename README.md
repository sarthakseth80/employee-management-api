# Employee Management API

A simple Spring Boot REST API built to practice **Hibernate ORM**, **Spring Data JPA**, **Entity Mapping (One-to-One & One-to-Many)**, **Validation**, and **Exception Handling**.

---

## Tech Stack

- Java 17
- Spring Boot 3.3.0
- Spring Data JPA (Hibernate as the JPA provider)
- H2 (in-memory database)
- Spring Boot Validation (Bean Validation / Jakarta Validation)
- Lombok
- Maven

---

## Entity Relationships

| Relationship | Type | Description |
|---|---|---|
| `Employee` → `Department` | Many-to-One | Many employees can belong to one department |
| `Department` → `Employee` | One-to-Many | One department can have many employees |
| `Employee` → `Address` | One-to-One | Each employee has exactly one address |

- **Employee** is the owning side of both relationships (it holds `department_id` and `address_id` as foreign keys).
- `cascade = CascadeType.ALL` on `Employee.address` means saving/deleting an Employee automatically saves/deletes its Address.
- `@JsonIgnore` is used on the inverse sides (`Department.employees`, `Address.employee`) to prevent infinite recursion during JSON serialization.

---

## Project Structure

```
src/main/java/com/sarthak/empmanagement/
├── entity/          # JPA entities: Employee, Department, Address
├── repository/       # Spring Data JPA repositories
├── service/           # Business logic layer
├── controller/         # REST controllers (API endpoints)
├── exception/           # Custom exceptions + global exception handler
├── dto/                   # Error response DTO
└── EmployeeManagementApiApplication.java   # Main entry point
```

---

## How to Run

### Prerequisites
- Java 17 or higher installed
- Maven installed (or use your IDE's built-in Maven support)
- Internet connection (first run only, to download dependencies)

### Steps
1. Extract the project zip.
2. Open the folder in IntelliJ IDEA / Spring Tool Suite / VS Code as a Maven project.
3. Run `EmployeeManagementApiApplication.java` (or `mvn spring-boot:run` from terminal).
4. The server starts on **http://localhost:8080**

### H2 Database Console
Visit **http://localhost:8080/h2-console** in your browser to view the database directly.

| Field | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:empdb` |
| Username | `sa` |
| Password | *(leave blank)* |

> Note: The database is in-memory — all data resets every time you restart the app. Sample data is automatically reloaded from `data.sql` on each startup.

---

## API Endpoints

### Employee

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| POST | `/api/employees` | Create a new employee |
| PUT | `/api/employees/{id}` | Update an existing employee |
| DELETE | `/api/employees/{id}` | Delete an employee |

### Department

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/departments` | Get all departments |
| GET | `/api/departments/{id}` | Get department by ID |
| POST | `/api/departments` | Create a new department |
| PUT | `/api/departments/{id}` | Update an existing department |
| DELETE | `/api/departments/{id}` | Delete a department |

---

## Sample Requests

### Create a Department
```http
POST /api/departments
Content-Type: application/json

{
  "name": "Marketing",
  "location": "Bangalore"
}
```

### Create an Employee (with Address + existing Department)
```http
POST /api/employees
Content-Type: application/json

{
  "name": "Rahul Verma",
  "email": "rahul@example.com",
  "salary": 52000,
  "department": { "id": 1 },
  "address": {
    "street": "12 Gandhi Marg",
    "city": "Nagpur",
    "state": "Maharashtra",
    "pincode": "440010"
  }
}
```

### Validation Error Example
If you send invalid data (e.g. blank `name` or invalid `email`), the API responds with a `400 Bad Request` listing exactly which fields failed:

```json
{
  "timestamp": "2026-06-30T10:15:30",
  "status": 400,
  "error": "Validation Failed",
  "path": "/api/employees",
  "fieldErrors": {
    "name": "Name is required",
    "email": "Email should be valid"
  }
}
```

### Not Found Error Example
Requesting a non-existent employee returns a `404`:

```json
{
  "timestamp": "2026-06-30T10:16:02",
  "status": 404,
  "error": "Not Found",
  "message": "Employee not found with id: 99",
  "path": "/api/employees/99"
}
```

---

## Key Concepts Demonstrated

- **Hibernate ORM** — entities mapped to tables automatically via `@Entity`, `@Table`, `@Column`
- **Spring Data JPA** — `JpaRepository` gives full CRUD without writing any SQL
- **Entity Mapping** — `@OneToOne`, `@OneToMany`, `@ManyToOne`, `@JoinColumn`, `mappedBy`
- **Validation** — `@NotBlank`, `@Email`, `@Positive` with `@Valid` on controller methods
- **Exception Handling** — custom `ResourceNotFoundException` + `@RestControllerAdvice` global handler for consistent error responses

---

## Author

Sarthak Seth — Built as part of Java/Spring Boot internship training, Week 7.
