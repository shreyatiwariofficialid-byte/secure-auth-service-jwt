🚀 Advanced Authentication System (Spring Boot)

A secure and scalable backend authentication system built using Spring Boot, implementing modern authentication and authorization practices including JWT, role-based access, and token management.

📌 Features
🔐 User Signup & Login API
🔑 JWT-based Authentication
♻️ Refresh Token Mechanism
🚫 Token Blacklisting (Logout Security)
👥 Role-Based Access Control (USER / ADMIN)
⚠️ Global Exception Handling
🧾 Structured API Responses
🛡️ Password Encryption using BCrypt

## ❓ Why This Project?
This project was built to understand and implement real-world authentication and authorization mechanisms used in modern applications.
It focuses on secure user authentication using JWT, role-based access control, and handling edge cases like secure logout using token blacklisting.

## 🏗️ Architecture Diagram
![Architecture](docs/architecture.png) 

🛠️ Tech Stack
Backend: Spring Boot, Spring Security
Database: MySQL
ORM: Hibernate
Authentication: JWT
Build Tool: Maven
Testing Tool: Postman

📂 Project Structure
src/main/java/com/yourpackage
│── controller        # Handles HTTP requests
│── service           # Business logic
│── repository        # Database interaction
│── entity            # JPA entities
│── dto               # Request/Response models
│── security          # JWT & security config
│── exception         # Global exception handling

⚙️ API Endpoints
Method	Endpoint	Description
POST	/api/self/signup	Register a new user
POST	/api/self/login 	Authenticate user
POST	/api/auth/logout	Logout & blacklist token
GET   /api/self/profile  User profile
POST   /api/self/refresh-token  Refreshing the session


🔄 Authentication Flow
User registers using the signup API
User logs in and receives a JWT token
Token is sent in headers for secured APIs
On logout, token is blacklisted
Blacklisted tokens are denied access

## ▶️ How to Run Locally
### 🔧 Prerequisites
Make sure you have the following installed:
- Java 17+
- Maven
- MySQL
---
### 📥 Clone the Repository
```bash```
git clone https://github.com/your-username/secure-auth-service-jwt.git
cd secure-auth-service-jwt

⚙️ Configure Database
Create a MySQL database->Update application.properties
🔐 Configure JWT
▶️ Run the Application


## 🧩 Challenges & Learnings
- Implemented secure logout using token blacklisting instead of simple token removal  
- Understood JWT lifecycle, validation, and expiration handling  
- Designed a clean layered architecture for scalability and maintainability  
- Handled exceptions globally to ensure consistent API responses  
- Improved understanding of Spring Security and authentication flows  


🚀 Future Enhancements
📧 Email Verification
🔄 Password Reset
🐳 Docker Support
☁️ Cloud Deployment
📄 Swagger API Documentation


👩‍💻 Author
Shreya Tiwari
Java Backend Developer | Java | Spring Boot
Open to Backend Opportunities

⭐ Support

If you found this project useful, consider giving it a ⭐ on GitHub!
