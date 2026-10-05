\# 🎓 Smart Online Examination and Assessment System



A web-based examination platform built with \*\*Java, Spring Boot, Spring Security, Thymeleaf, and MySQL\*\*. The system provides separate Admin and Student portals for managing examinations, questions, online tests, and results.



\## 🚀 Project Overview



The \*\*Smart Online Examination and Assessment System\*\* is designed to digitize the complete examination process.



Instead of conducting examinations manually, administrators can create subjects, examinations, and question banks, while students can securely attend examinations online and immediately view their results.



\## ✨ Key Features



\### 👨‍💼 Admin Module



\* Admin authentication and authorization

\* Dashboard with examination statistics

\* Create and manage subjects

\* Create and manage examinations

\* Configure examination duration and total marks

\* Create questions with multiple options

\* Configure marks and negative marking

\* Select correct answers

\* View all examination results

\* Delete subjects, examinations, and questions



\### 👨‍🎓 Student Module



\* Student registration

\* Secure student login

\* Student dashboard

\* View available examinations

\* Attend online examinations

\* Multiple-choice questions

\* Examination timer

\* Automatic answer evaluation

\* Positive and negative marking

\* Automatic score calculation

\* View examination results

\* View previous examination attempts



\### 🔐 Security



\* Spring Security authentication

\* Role-based authorization

\* Separate ADMIN and STUDENT access

\* BCrypt password encryption

\* Protected admin routes

\* Protected student routes



\## 🛠️ Technology Stack



| Technology      | Purpose                        |

| --------------- | ------------------------------ |

| Java 21         | Backend programming            |

| Spring Boot 4   | Application framework          |

| Spring MVC      | Web architecture               |

| Spring Data JPA | Database persistence           |

| Spring Security | Authentication \& authorization |

| Thymeleaf       | Server-side UI                 |

| MySQL 8         | Database                       |

| Maven           | Build \& dependency management  |

| HTML5           | Frontend structure             |

| CSS3            | Frontend styling               |

| IntelliJ IDEA   | Development environment        |



\## 🏗️ System Architecture



```text

&#x20;                   ┌──────────────────────────┐

&#x20;                   │        Web Browser       │

&#x20;                   └────────────┬─────────────┘

&#x20;                                │

&#x20;                                ▼

&#x20;                   ┌──────────────────────────┐

&#x20;                   │   Spring MVC Controllers │

&#x20;                   └────────────┬─────────────┘

&#x20;                                │

&#x20;                   ┌────────────▼─────────────┐

&#x20;                   │      Service Layer       │

&#x20;                   │                          │

&#x20;                   │ Exam Service             │

&#x20;                   │ Question Service          │

&#x20;                   │ Registration Service      │

&#x20;                   │ Result Service            │

&#x20;                   └────────────┬─────────────┘

&#x20;                                │

&#x20;                   ┌────────────▼─────────────┐

&#x20;                   │    Spring Data JPA       │

&#x20;                   └────────────┬─────────────┘

&#x20;                                │

&#x20;                   ┌────────────▼─────────────┐

&#x20;                   │       MySQL Database     │

&#x20;                   └──────────────────────────┘

```



\## 🗄️ Database Design



The application uses the following main tables:



```text

users

&#x20;  │

&#x20;  ├── exam\_results

&#x20;  │

subjects

&#x20;  │

&#x20;  └── exams

&#x20;         │

&#x20;         ├── questions

&#x20;         │       │

&#x20;         │       └── question\_options

&#x20;         │

&#x20;         └── exam\_results

```



\### Main Tables



\* `users` — Admin and student accounts

\* `subjects` — Examination subjects

\* `exams` — Examination information

\* `questions` — Examination questions

\* `question\_options` — Multiple-choice options

\* `exam\_results` — Student examination results



\## 📁 Project Structure



```text

onine-examination-system/

│

├── .mvn/

├── src/

│   ├── main/

│   │   ├── java/

│   │   │   └── com/exam/onine/

│   │   │

│   │   └── resources/

│   │       ├── static/

│   │       │   └── css/

│   │       │

│   │       ├── templates/

│   │       │   ├── admin/

│   │       │   └── student/

│   │       │

│   │       └── application.properties

│   │

│   └── test/

│

├── pom.xml

├── mvnw

├── mvnw.cmd

└── README.md

```



\## ⚙️ Requirements



Before running the project, install:



\* Java 21

\* MySQL 8

\* Maven 3.10+

\* IntelliJ IDEA or another Java IDE



\## 🗃️ Database Setup



Create the database in MySQL:



```sql

CREATE DATABASE online\_exam\_db;

```



The application uses:



```text

Database: online\_exam\_db

Username: root

```



The database password should be supplied through the `DB\_PASSWORD` environment variable.



\## 🔑 Environment Configuration



Do \*\*not\*\* store your real database password in GitHub.



Configure the environment variable locally:



\### Windows PowerShell



```powershell

$env:DB\_PASSWORD="YOUR\_MYSQL\_PASSWORD"

```



The application configuration uses:



```properties

spring.datasource.password=${DB\_PASSWORD}

```



\## ▶️ Running the Application



Clone the repository:



```bash

git clone https://github.com/Manikanta-23-ai/SMART-ONLINE-EXAMINATION-SYSTEM.git

```



Enter the project:



```bash

cd SMART-ONLINE-EXAMINATION-SYSTEM

```



Set the database password:



```powershell

$env:DB\_PASSWORD="YOUR\_MYSQL\_PASSWORD"

```



Run the application:



```bash

mvn spring-boot:run

```



Or on Windows:



```powershell

.\\mvnw.cmd spring-boot:run

```



Open:



```text

http://localhost:8080

```



\## 👥 User Roles



\### ADMIN



Admin users can:



\* Manage subjects

\* Manage examinations

\* Manage questions

\* View examination results

\* Monitor the examination system



\### STUDENT



Student users can:



\* Register

\* Login

\* View examinations

\* Attend examinations

\* Submit answers

\* View scores

\* View previous results



\## 📊 Examination Evaluation



The system automatically evaluates submitted answers.



```text

Correct Answer

&#x20;     ↓

Add Question Marks



Wrong Answer

&#x20;     ↓

Subtract Negative Marks



No Answer

&#x20;     ↓

Count as Unanswered



Final Score

&#x20;     ↓

Store Result in Database

```



\## 🔮 Future Enhancements



Planned improvements include:



\* Examination scheduling

\* Question randomization

\* Automatic question selection

\* Advanced analytics dashboard

\* Student performance charts

\* Email notifications

\* PDF result generation

\* Export results to Excel

\* Examination attempt restrictions

\* Improved anti-cheating mechanisms

\* REST API

\* Online deployment

\* Docker support



\## 🎯 Academic Purpose



This project demonstrates practical implementation of:



\* Object-Oriented Programming

\* Java Web Development

\* Spring Boot

\* Spring Security

\* MVC Architecture

\* Database Management

\* JPA/Hibernate

\* Authentication and Authorization

\* CRUD Operations

\* Online Assessment Logic



\## 👨‍💻 Developer



\*\*Manikanta Banavathu\*\*



Gen AI Engineer | Freelance Web Developer | Student



GitHub:

https://github.com/Manikanta-23-ai



LinkedIn:

https://www.linkedin.com/in/mani-manikanta-796907385/



LeetCode:

https://leetcode.com/u/Manikanta109876/



\## 📄 License



This project is currently intended for educational and portfolio purposes.



