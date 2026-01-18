# 📚 Book Library Management System (REST API)

## 📌 Project Overview
The Book Library Management System is a RESTful API developed using Spring Boot, Spring Data JPA, and an H2 in-memory database.  
It allows librarians to manage a library's book collection by performing CRUD operations and searching books based on different criteria.

This project is created as part of the **Module 12 Assignment**.

---

## 🛠️ Technologies Used
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- Lombok

---

## 📘 Book Entity Structure

| Field Name         | Type    | Description |
|-------------------|---------|-------------|
| id                | Long    | Primary key (Auto-generated) |
| title             | String  | Book title |
| author            | String  | Author name |
| publication       | String  | Publication name |
| publicationYear   | Integer | Year of publication |
| availableCopies   | Integer | Number of available copies |~~~~

---

## 🚀 REST API Endpoints

### 🔹 Basic CRUD Operations

| HTTP Method | Endpoint | Description |
|------------|----------|-------------|
| POST | `/api/books` | Add a new book |
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| PUT | `/api/books/{id}` | Update book details |
| DELETE | `/api/books/{id}` | Delete a book |

---

### 🔹 Custom Search Endpoints

| HTTP Method | Endpoint | Description |
|------------|----------|-------------|
| GET | `/api/books/author/{authorName}` | Get books by author |
| GET | `/api/books/genre/{genre}` | Get books by genre |
| GET | `/api/books/publication/{publication}` | Get books by publication |

---

## 🧪 H2 Database Console
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

---

## ▶️ How to Run the Project

1. Clone the repository:
