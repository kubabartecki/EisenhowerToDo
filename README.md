# EisenhowerToDo

EisenhowerToDo is a task management application built with Spring Boot (Java) for the backend and React with TypeScript for the frontend.

## Setup Instructions

### Backend Setup

1. **Database Setup**:
   - Open `backend/task/src/main/resources/application.properties`.
   - Configure the PostgreSQL database connection details:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     spring.datasource.driver-class-name=org.postgresql.Driver
     ```

2. **Start Backend**:
   - Navigate to the `backend/task` directory.
   - Build and run the Spring Boot application.

     Maven:
     ```
     mvn spring-boot:run
     ```

   - The backend server will start on `http://localhost:8080`.

### Backend Tests
To run tests:
```
mvn test
```

### Frontend Setup

1. **Navigate to Frontend Directory**:
   - Open a terminal or command prompt.
   - Change directory to `frontend/todo-list`:
     ```
     cd frontend/todo-list
     ```

2. **Install Dependencies**:
   - Install required npm packages:
     ```
     npm install
     ```

3. **Start Frontend**:
   - Start the React development server:
     ```
     npm run start
     ```

   - The frontend development server will start on `http://localhost:3000`.

4. **Access Application**:
   - Open your web browser and go to `http://localhost:3000` to access the EisenhowerToDo application.

## Technologies Used

- **Backend**:
  - Spring Boot
  - Lombok
  - PostgreSQL

- **Frontend**:
  - React
  - TypeScript
  - MUI
