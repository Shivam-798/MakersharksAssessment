# User Registration API

This project is a RESTful API for user registration and fetching user details. The API is built using Spring Boot and integrates various technologies to provide a robust and secure service.

## Technologies Used
##Java version: 21
- **Spring Boot**: For building the RESTful API.
- **MySQL**: As the database to store user information.
- **Spring Data JPA**: For database operations.
- **Lombok**: To reduce boilerplate code.
- **Spring Security**: For securing endpoints and managing authentication.
- **JSON Web Token (JWT)**: For user authentication.
- **Spring Dev Tools**: For easier coding and hot swapping.
- **Swagger**: For API documentation.

## Endpoints

### Register User

**POST** `/api/user/register`

Registers a new user.

#### Request Body

```json
{
  "username": "johndoe",
  "email": "johndoe@example.com",
  "password": "password123"
}
