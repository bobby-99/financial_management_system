# 💸 Financial Management System

> A backend-focused financial tracking application built with **Java & Spring Boot** — designed as a hands-on deep dive into real-world backend architecture, security, and analytics.

---

## ✨ Features

### 🔐 Authentication & Security
- User registration & login with **JWT-based authentication**
- Protected APIs via **Spring Security**
- Stateless auth flow with per-user data isolation

### 💳 Transaction Management
- Create, track, and categorize **income & expenses**
- Fields: Amount · Description · Type (INCOME/EXPENSE) · Category · Timestamp
- Users access only their own transaction history

### 🏷️ Custom Categories
Create personal categories (Food, Travel, Shopping, Salary, etc.) linked to transactions for rich reporting.

### 📊 Financial Analytics
| Endpoint | Description |
|---|---|
| Financial Summary | Total Income, Total Expenses, Current Balance |
| Category Breakdown | Grouped spending insights via JPQL aggregations |

---

## 🛠️ Tech Stack

| Layer | Technologies |
|---|---|
| **Backend** | Java, Spring Boot, Spring Security, Spring Data JPA, Hibernate, Maven |
| **Database** | PostgreSQL, Flyway Migrations |
| **Auth** | JWT (JSON Web Tokens) |
| **DevOps** | Docker, Docker Compose |
| **Tools** | Postman, IntelliJ IDEA, Git & GitHub |

---

## 🏗️ Architecture

Follows a clean **layered architecture**:

```
Controller  →  Service  →  Repository  →  Database
    ↑              ↑
  DTOs          Entities
```

- **Controller** — API endpoints, HTTP request/response handling
- **Service** — Business logic, validation, analytics, ownership checks
- **Repository** — JPQL queries, aggregations, persistence
- **Entity** — JPA-mapped database tables
- **DTO** — Clean data transfer between layers and API boundaries

---

## 🔒 Auth Flow

```
Login → JWT Generated → Token sent in Authorization header
     → JWT Filter validates → User identity extracted → Access granted
```

All transaction and category data is fully isolated per user.

---

## 🗄️ Database Schema

| Table | Description |
|---|---|
| `users` | Registered accounts |
| `transactions` | Income & expense records |
| `categories` | User-defined categories |
| `flyway_schema_history` | Migration tracking |

Relationships use **UUID identifiers** with user ownership mapping.

---

## 🚀 Roadmap

<details>
<summary><strong>Backend</strong></summary>

- [ ] Request validation & global exception handling
- [ ] Pagination & sorting
- [ ] Date range filtering & monthly analytics
- [ ] Budget tracking system
- [ ] Export reports (CSV / PDF)
- [ ] Swagger / OpenAPI documentation
- [ ] Unit & integration testing
- [ ] Cloud deployment

</details>

<details>
<summary><strong>Frontend (React + Vite)</strong></summary>

- [ ] Login / Register
- [ ] Dashboard
- [ ] Transactions view
- [ ] Categories management
- [ ] Analytics charts
- [ ] Profile / Settings

</details>

---

## 👨‍💻 Author

Built by **Soumyaranjan Dandapat** as a backend learning and portfolio project — focused on understanding real-world API design, authentication, database relationships, and scalable Spring Boot architecture from the ground up.
