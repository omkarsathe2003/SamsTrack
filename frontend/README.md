# 📘 Attendo

**Attendo** is a Spring Boot REST API project designed to streamline and automate the management of student attendance in educational institutions. It allows faculty members to record attendance for specific subjects and provides endpoints for viewing and managing these records. The application focuses on reducing paperwork, improving accuracy, and enhancing administrative workflows.

---

## 🎯 Objective

* ✅ Automate attendance tracking through RESTful APIs to streamline integration with existing educational systems.
* ✅ Ensure data accuracy with automated validation and structured storage of attendance records.
* ✅ Facilitate seamless integration with institutional platforms to support faculty and administrative operations.

---

## 🔍 Scope of the Project

Attendo provides a robust backend system to manage:

* Student records
* Subject allocation
* Faculty details
* Attendance tracking and reporting

The system is designed with a modular, scalable architecture using a layered design pattern.

---

## 🏗️ Project Architecture

Attendo follows a **Layered Architecture**, separating concerns across three core layers:

### 1. **Controller Layer**

* Handles incoming HTTP requests
* Validates inputs and delegates processing to the service layer
* Formats and returns HTTP responses

### 2. **Service Layer**

* Implements business logic and application rules
* Coordinates interactions between controllers and DAOs
* Manages data validation, transformation, and transactions

### 3. **DAO (Data Access Object) Layer**

* Handles all direct interactions with the database
* Performs CRUD operations
* Translates method calls to SQL queries using Hibernate
* Abstracts database logic from other layers

---

## ⚙️ Technology Stack

| Component            | Technology        |
| -------------------- | ----------------- |
| Backend Framework    | Spring Boot 2.5.6 |
| Database             | MySQL 8           |
| ORM                  | Hibernate 5.6     |
| Programming Language | Java (JDK 1.8)    |
| Build Tool           | Maven             |
| IDE                  | Eclipse           |
| API Testing          | Postman           |

---

## 📦 Modules in the Project

1. **Student Module** – Manage student data
2. **Subject Module** – Define and assign subjects
3. **Faculty Module** – Manage faculty details and their assigned subjects
4. **Attendance Module** – Record and retrieve attendance per subject
5. **User Module** – Manage API access and authentication (if implemented)

---

## 🚀 Getting Started

To run the project locally:

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/attendo.git
   cd attendo
   ```

2. Configure your `application.properties` with your local MySQL credentials.

3. Use **Postman** or any REST client to test the endpoints once the app is running.

---

## 📝 License

This project is open-source and free to use for educational purposes.

---



This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.2.8.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.
