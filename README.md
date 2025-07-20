
# 🍕 Food Delivery Application

A basic full-stack food delivery application using HTML, CSS, JavaScript (frontend) and Spring Boot (backend). It includes core functionalities like user registration, login, logout, and profile management. This project aims to simulate a simple but scalable production ready food ordering system.

---

## 🔧 Tech Stack

- **Frontend**: HTML, CSS, JavaScript
- **Backend**: Java, Spring Boot, Spring Security
- **Database**: MySQL
- **Tools**: Eclipse, Postman, Git, GitHub, Spring Suit tool.

---

## ✅ Features Completed

- User Registration
- Login/Logout with session handling
- Fetch and display user profile from database
- Basic UI for login and registration
- Basic UI for Dashboard and Home

---

## 🚧 Planned Features

- Menu listing (food items)
- Cart functionality
- Order placement and confirmation
- Admin dashboard to manage orders
- Payment integration
- Email notifications

---

## 📁 Project Structure

```
/src
  /main
    /java
    /resources
      /templates       --> HTML files
      /static          --> CSS/JS
/application.properties
```

---

## ▶️ Getting Started Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/deep2555/food-delivery-app.git
   ```

2. Open the project in IntelliJ or Spring Tool Suite.

3. Set up your MySQL database and create schema.

4. Update your `application.properties`:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/your_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

5. Run the application and open in your browser.

---

## 🧠 Challenges Faced

- **Frontend-Backend Sync**: Initially faced difficulties connecting the HTML forms with Spring Boot controllers, but overcame this by understanding Spring MVC form binding and request mapping.
- **Database Connection Errors**: Faced MySQL connection timeouts. Solved by checking firewall rules and JDBC URL formatting.
- **Session Management**: Implementing logout and user session handling was challenging, especially with Spring Security. Learned and applied `HttpSession` and CSRF handling for forms.

---

## 🏁 Results (So Far)

- Built a modular base for a food delivery system.
- Functional login, registration, and profile fetch features.
- Clean and responsive UI for basic user actions.

---



## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

---

## 📫 Contact

Reach me at: [deepanshuprashar83@gmail.com](mailto:deepanshuprashar83@gmail.com)

