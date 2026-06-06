![Spring Boot](https://repository-images.githubusercontent.com/149085612/76e31b00-782c-11e9-8fdc-eec6e93d055b)

# GYM E-COMMERCE WEB-APPLICATION

> A full-stack E-commerce web application built using Java 17 and the Spring Boot ecosystem.

## Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.5
* **Security:** Spring Security 6 (BCrypt, Role-based Auth)
* **Frontend:** Thymeleaf (with Layout Dialect), HTML5, CSS3
* **Database:**
    * **Production:** PostgreSQL
    * **Testing:** H2 In-Memory Database
* **ORM:** Spring Data JPA (Hibernate)
* **Tools:** Maven, Lombok, Spring DevTools
* **Testing:** JUnit 5, Mockito, Spring Security Test




### Installation

1.  **Clone the repository**
    ```bash
    git clone [https://github.com/hassamk122/EcommerceWeb.git](https://github.com/hassamk122/EcommerceWeb.git)
    cd EcommerceWeb
    ```

2.  **Configure the Database**
    Open `src/main/resources/application.properties` and update your PostgreSQL credentials:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
    spring.datasource.username=your_postgres_user
    spring.datasource.password=your_postgres_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Build the Project**
    ```bash
    mvn clean install
    ```

4.  **Run the Application**
    ```bash
    mvn spring-boot:run
    ```

The application will be available at `http://localhost:8080`.
