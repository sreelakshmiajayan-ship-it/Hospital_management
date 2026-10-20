# 🏥 Hospital Management System

A desktop-based **Hospital Management System** developed using Java, Java Swing, JDBC, and MySQL. The application simplifies hospital operations by providing role-based access and dedicated functionalities for administrators, doctors, and patients.

## 📌 Project Overview

The Hospital Management System is designed to manage essential hospital activities through a graphical user interface. It supports doctor registration, appointment scheduling, patient admissions, room allocation, prescriptions, medicine inventory, and billing.

The project follows Object-Oriented Programming (OOP) principles and uses a MySQL database for persistent data storage.

## ✨ Features

### 🔐 Role-Based Login
- Separate access for Admin, Doctor, and Patient.
- Role-specific dashboards and functionalities.
- Doctor login using registered credentials.

### 👨‍💼 Admin Dashboard
- Register and manage doctors.
- View and manage appointments.
- Update appointment status.
- Manage patient admissions and room allocation.
- View prescriptions.
- Manage medicines and inventory.
- Create and manage patient bills.

### 🩺 Doctor Dashboard
- View appointments assigned to the logged-in doctor.
- View patient admission details.
- Create prescriptions for patients.
- Prescribe available, unexpired medicines.

### 🧑‍⚕️ Patient Dashboard
- Access patient-specific information.
- Book, reschedule, and cancel appointments.
- View admission details.
- View prescribed medicines and prescriptions.
- View billing information.

### 📅 Appointment Management
- Schedule appointments between patients and doctors.
- Reschedule or cancel appointments.
- Allow doctors to view their own appointments.
- Allow administrators to view and update appointment statuses.

### 🛏️ Admission and Room Management
- Admit patients through the Admin dashboard.
- Allocate available rooms.
- Track admission details and status.
- Allow doctors and patients to view relevant admission information.

### 💊 Prescription and Medicine Management
- Create prescriptions associated with patients and doctors.
- Include medicine, dosage, frequency, duration, and instructions.
- Manage medicine stock and expiry dates.
- Prevent the selection of unavailable or expired medicines.

### 💳 Billing Management
- Create and manage patient bills.
- Track billing and payment status.
- Maintain billing information in the database.

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core application logic and OOP |
| Java Swing | Graphical user interface |
| JDBC | Java–MySQL connectivity |
| MySQL | Relational database |
| MySQL Connector/J | JDBC driver |
| VS Code | Development environment |
| Git and GitHub | Version control and collaboration |

## 🏗️ Project Structure

```text
Hospital_managment/
│
├── src/
│   └── main/
│       └── java/
│           ├── app/
│           │   └── Main.java
│           ├── dao/
│           ├── exception/
│           ├── gui/
│           ├── model/
│           ├── service/
│           └── util/
│               ├── DBConnection.java
│               └── InputValidator.java
│
├── database/
│   └── hospital_management.sql
│
├── lib/
│   └── MySQL Connector/J
│
├── out/                 # Generated compiled classes
├── .gitignore
└── README.md
```

*The structure above describes the main project packages. Individual files may vary as development progresses.*

## ⚙️ Installation and Setup

### Prerequisites

Install the following software:

- Java Development Kit (JDK)
- MySQL Server 8.0 or a compatible version
- Visual Studio Code
- Java extensions for VS Code
- MySQL Connector/J

### 1. Clone the Repository

Open a terminal and run:

```bash
git clone https://github.com/sreelakshmiajayan-ship-it/Hospital_management.git
```

Navigate into the project folder:

```bash
cd Hospital_management
```

### 2. Create the Database

Open MySQL Workbench or your MySQL command-line client.

Import the SQL file located at:

```text
database/hospital_management.sql
```

Alternatively, from a terminal, run:

```bash
mysql -u root -p < database/hospital_management.sql
```

Enter your MySQL password when prompted. Ensure that the SQL file creates the `hospital_management` database and its required tables.

### 3. Configure the Database Connection

Open `DBConnection.java` and verify the database configuration:

```java
jdbc:mysql://localhost:3306/hospital_management
```

The application uses the `DB_PASSWORD` environment variable for the MySQL password.

In Windows PowerShell, set the password for the current terminal session:

```powershell
$env:DB_PASSWORD = "your_mysql_password"
```

Replace the placeholder with your actual MySQL password. Do not commit passwords or other credentials to GitHub.

### 4. Compile the Java Source Files

From the project root, run these commands in PowerShell:

```powershell
New-Item -ItemType Directory -Force -Path out
```

```powershell
$sources = Get-ChildItem -Path src\main\java -Recurse -Filter *.java |
    ForEach-Object { $_.FullName }

javac -cp "lib/*" -d out $sources
```

Ensure that the MySQL Connector/J JAR file is available inside the `lib` directory.

### 5. Run the Application

After successful compilation, run:

```powershell
java -cp "out;lib/*" app.Main
```

The Hospital Management System should launch if the database connection and application configuration are correct.

## 🗄️ Database

The application uses MySQL to store hospital-related information. The database includes tables for the following entities:

- Users and administrators
- Patients and doctors
- Appointments
- Admissions
- Rooms
- Prescriptions
- Medicines
- Bills

The database schema is provided in `database/hospital_management.sql`.

## 🧠 OOP Concepts

The project demonstrates important Object-Oriented Programming concepts:

- **Encapsulation:** Protecting data using private fields and public methods.
- **Inheritance:** Reusing common person-related attributes through model classes.
- **Abstraction:** Defining common behavior and hiding implementation details.
- **Polymorphism:** Supporting extensible behavior through inheritance and method overriding where applicable.
- **Singleton Design Pattern:** Providing a shared database connection utility where implemented.

## 🔒 Security and Validation

- Role-based access through the application's login system.
- Input validation for user-provided data.
- JDBC prepared statements for parameterized SQL operations where implemented.
- Environment-based database password configuration.

For production use, passwords should be securely hashed, authorization must be enforced consistently, and sensitive data must be protected.

## 🎯 Project Objectives

- Digitize essential hospital management activities.
- Reduce manual work and simplify record management.
- Improve appointment scheduling and admission workflows.
- Maintain organized patient, prescription, medicine, and billing records.
- Apply Java OOP concepts and database connectivity in a practical project.


## 👩‍💻 Development

This project was developed as an academic project to apply Java programming, Object-Oriented Programming, GUI development, database management, and software engineering concepts.

**Repository:** [Hospital Management System on GitHub](https://github.com/sreelakshmiajayan-ship-it/Hospital_management)

