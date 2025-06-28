
---

# HostelEase - Backend API

HostelEase is a backend system for managing hostel operations such as user registration, login, complaints, and payments. Built with **Spring Boot**, it provides secure JWT-based authentication, modular architecture, and easy integration with frontend clients like React.

---

## 🔧 Technologies Used

- Java 17+
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- ModelMapper
- Lombok

---



## 📁 Project Structure
```



HostelEase/
├── config/             # Security, CORS, and Mapper configurations
├── controller/         # REST controllers for endpoints
├── dto/                # Data Transfer Objects
├── entity/             # JPA entity models
├── filter/             # JWT authentication filter
├── repo/               # Spring Data JPA repositories
├── service/            # Business logic and interfaces
├── util/               # Standard response models
└── application.properties

```



---



## ⚙️ Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL (running on port `3307`)



### Setup

1. Clone the repository:
   
```
   git clone https://github.com/your-username/hostelease-backend.git
   cd hostelease-backend
````

2. Configure MySQL:

   ```sql
   CREATE DATABASE hostel_ease;
   ```

3. Edit `src/main/resources/application.properties` if needed.

4. Run the app:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

   The API will be available at: `http://localhost:8090/api/v1`

---

## 🔐 Authentication Flow

* **Sign Up**: `POST /user/signup`
* **Sign In**: `POST /user/signin`
* **JWT Token**: Returned on login, sent with each request via `Authorization: Bearer <token>`

Spring Security ensures secure access to protected endpoints.

---

## 📡 API Endpoints

| Method | Endpoint       | Description             | Auth Required |
| ------ | -------------- | ----------------------- | ------------- |
| POST   | `/user/signup` | Register a new user     | ❌ No          |
| POST   | `/user/signin` | Login and receive token | ❌ No          |
| GET    | `/complaints`  | View complaints         | ✅ Yes         |
| POST   | `/payments`    | Make a payment          | ✅ Yes         |

> More endpoints can be added for admin and hostel features.

---

## 🌐 CORS Configuration

CORS is configured to allow frontend apps (like React) to connect from `http://localhost:5173`.

---

## 📄 application.properties

```properties
server.port=8090
server.servlet.context-path=/api/v1

spring.datasource.url=jdbc:mysql://localhost:3307/hostel_ease?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=123123

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🧰 Developer Info

* Passwords are hashed using `BCrypt`
* JWT is used for stateless authentication
* Security filters are added via `JWTFilter`
* Layered architecture: Controller → Service → Repo

---

## 📌 To-Do (Future Enhancements)

### 🚀 Planned for v2.0
- 🔗 **WhatsApp Integration** for complaint submission and updates, offering a familiar interface for users
- 🤖 **AI Assistant Integration** to guide users, answer hostel-related queries, and automate support using NLP
- - Add **Admin role** with dashboard access and management capabilities
- Integrate **Swagger/OpenAPI** documentation for API exploration
- Add **Email/SMS notifications** for real-time communication
- **Dockerize** the backend for easier deployment
- Write **unit and integration tests** for better reliability


---

## 🤝 Contributing

Contributions are welcome! Please fork this repo and submit a pull request.

---


## 🙋‍♂️ Author

**Sachintha Nimesh**

Email  : [Sachinthanimesh370@gmail.com](mailto:Sachinthanimesh370@gmail.com)

GitHub : [SachinthaNimesh370](https://github.com/SachinthaNimesh370)


