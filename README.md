# 🧠 Text Analyzer (Coding Task)

A simple application that analyzes text input for vowels and consonants. The app supports both **online** (via REST API) and **offline** (client-side) modes. Implemented using **Angular**, **Spring Boot**, and tested with **JUnit**.

---

## ✨ Features

- Analyze text for:
  - Number of vowels
  - Number of consonants
- Toggle between **online** and **offline** analysis mode
- Display of previous results
- RESTful backend API (Spring Boot)
- Unit tests for core backend logic

---

## 🧱 Technologies

### Backend (Java, Spring Boot)

- Java 21+
- Spring Boot (REST API)
- Maven
- JUnit 5

### Frontend (Angular)

- Angular 19
- TypeScript
- Reactive Forms
- Material UI

---

## 📦 How to Run

### ✅ Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run
```

Runs on: `http://localhost:8080`

#### REST Endpoint
`POST /api/analyze`

Example Requests:
```json
{
  "inputText": "Hello world!",
  "analyzerMode": "VOWELS"
}
```
```json
{
  "inputText": "Hello world!",
  "analyzerMode": "CONSONANTS"
}
```

Expected Response:
```json
{
  "inputText": "Hello world!",
  "analyzerMode": "VOWELS",
  "report": {
    "A": 0,
    "E": 1,
    "I": 0,
    "O": 2,
    "U": 0
  }
}
```
```json
{
  "inputText": "Hello world!",
  "analyzerMode": "CONSONANTS",
  "report": {
    "H": 1,
    "L": 3,
    "W": 1,
    "R": 1,
    "D": 1
  }
}
```

### ✅ Frontend (Angular)

```bash
cd frontend
npm install
ng serve
```

Runs on: `http://localhost:4200`

---

## 🧪 Running Tests

### Backend
```bash
cd backend
./mvn test
```

Tests include:

- Vowel/consonant detection logic
- Enum behavior
- Word frequency detection
- Edge cases (empty string, case insensitivity)

---

## 📁 Project Structure

```
backend/
│
├── controller/         # REST Controller
├── dto/                # Request/Response DTOs & Enums
├── service/            # Delegates offline/online calls, Text analysis logic
└── tests/              # Unit tests

frontend/
│
├── components/         # UI components
├── services/           # API integration
├── environment/        # Set config values
└── app.module.ts
```

---

## 📌 Note

In **online mode**, the frontend sends the input to the backend via REST. There the analysis happens through the refactored logic in `LocalTextAnalyzer.java` provided by the `TextAnalyzer.java`.  
In **offline mode**, analysis happens directly on the frontend using a local TypeScript copy of the logic from `TextAnalyzer.java`.

