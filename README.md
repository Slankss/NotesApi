# 🗒️ Notes API

A Spring Boot-based RESTful API to manage notes.

This API allows users to create, update, delete, and retrieve notes. It supports filtering by priority or creation date, and includes auditing and Lombok for cleaner code.

## 📌 Base URL
localhost:8080/notes/api

## 📦 Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Spring Auditing (`@CreatedDate`, `@LastModifiedDate`)
- Lombok
- H2 in-memory database
- Gradle

## 🔍 End Points

### 🔁 Standard Response Format
All endpoints return a `Response<T` object

```json
{
  "status": true,
  "data": ...,
  "errorMsg": null
}
```
---

### 🔵  GET /notes/api/

**Description:**  
Retrieve all notes with optional filtering and sorting.

#### 🔸 Query Parameters:

| Parameter        | Required | Description                                 | Default |
|------------------|----------|---------------------------------------------|---------|
| `filter`         | No       | `priority`, `date` or `id`                     | `id`  |
| `sortDirection`  | No       | `asc` for ascending or `desc` for descending | `desc`  |

#### 📥 Example Response:

  ```json
  {
    "status": true,
    "data": [
      {
        "id": 1,
        "header": "Learn Spring",
        "content": "Study controllers and services",
        "priority": 1,
        "createdDate": "2025-07-09T14:00:00",
        "updatedDate": "2025-07-09T14:00:00"
      }
    ],
    "errorMsg": null
  }
```
---

### 🔵  GET /notes/api/{id}

**Description:**  
Fetch a single note by its ID.

#### 🔸 Path Parameter:

| Parameter    | Type  | Required |                                 
|--------------|-------|----------|
| `id`         | Long  | Yes      |


#### 📥 Example Response:

  ```json
   {
    "status": true,
    "data": {
      "id": 1,
      "header": "Learn Spring",
      "content": "Study controllers and services",
      "priority": 1,
      "createdDate": "2025-07-09T14:00:00",
      "updatedDate": "2025-07-09T14:30:00"
    },
    "errorMsg": null
  }
```

---

### 🟢   POST /notes/api/

**Description:**  
Create a new note.

#### 📥 Example Request Body:

  ```json
  {
  "header": "Read Clean Code", // Required
  "content": "Chapter 2: Meaningful Names", // Required
  "priority": 2 // Not required
  }
  ```

---

### 🟡  PATCH /notes/api/

**Description:**  
Update an existing note's header and content by its ID.

#### 📥 Example Request Body:

  ```json
  {
  "id": 1, // Required
  "header": "Updated Header", // Not Required
  "content": "Updated note content", // Not Required
  "priority": 5 // Not Required
  }
```

#### 📥 Example Response:

```json
  {
  "status": true,
  "data": null,
  "errorMsg": null
}
```

---

### 🔴  DELETE /notes/api/{id}

**Description:**  
Delete a specific note by its ID.

#### 📥 Example Request:

| Parameter | Type | Required |
| --------- | ---- | -------- |
| `id`      | Long | Yes      |


#### 📥 Example Response:

```json
  {
  "status": true,
  "data": null,
  "errorMsg": null
}
```

---

### 🔴  DELETE /notes/api/all

**Description:**  
Delete all notes in the database.

#### 📥 Example Response:

```json
  {
  "status": true,
  "data": null,
  "errorMsg": null
}
```

---

## ⚙️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/notes-api.git
   cd notes-api

2. Build and run the proeject
   ```bash
   ./gradlew bootRun
3. Visit
   ```bash
   http://localhost:8080/notes/api
   
