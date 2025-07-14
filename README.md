# 🥤 Vending Machine API

A Spring Boot RESTful application that simulates a vending machine with role-based access, product management, user registration, deposit/buy functionality, and error handling.

---

## 📦 Technologies Used

- **Java 21+**
- **Spring Boot 3+**
- **Spring Security**
- **Spring Data JPA**
- **MySQL** 
- **Lombok**
- **Maven**

---

## 🚀 Features

- User Registration & Role-based Login (BUYER / SELLER)
- Product CRUD for SELLERs
- Deposit, Buy & Reset functions for BUYERs
- Global Exception Handling
- Custom API Logging
- Input Validation with `@Valid`
- Basic Authentication

---

## ⚙️ Prerequisites

- Java 21+
- Maven 3.6+
- IDE (IntelliJ IDEA)
- MySQL

---


## Database Setup
##  MySQL

Update `application.properties`:

## properties
spring.datasource.url=jdbc:mysql://localhost:3306/vending_machine
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update


## Access the app:

API base URL: http://localhost:8080

MySql console: http://localhost:3306/vending_machine_system


## 🧪 Sample Endpoints (via Postman)
Auth Required (Basic Auth)
POST /users/register — Register a new user

GET /users/me — Get logged-in user info

POST /products/create — Create product (SELLER)

GET /products/my — Get seller’s products

POST /deposit — Deposit coins (BUYER)

POST /buy — Buy product (BUYER)

## 🔐 Roles
BUYER: Can deposit, buy, and reset.

SELLER: Can create, update, and delete products.

## 🧾 Notes
Passwords are encoded using BCrypt.

Transactions and exceptions are logged using SLF4J.

@ControllerAdvice handles global error responses.

You must use Basic Auth in Postman with valid username/password for protected endpoints.

## Generative AI chat
https://chatgpt.com/share/6875439c-ea24-800e-895b-f28428d5ab00

## 👨‍💻 Author
Built by Amr Mohamed Salah


