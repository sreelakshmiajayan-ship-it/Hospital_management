# Hospital Management System

A Java-based Hospital Management System developed using **Object-Oriented Programming (OOP), Java Swing, JDBC, and MySQL**. The application simplifies hospital operations by managing patients, doctors, appointments, prescriptions, admissions, rooms, and billing through a graphical user interface.

## Features

### 1. User Authentication
- Secure login system.
- Role-based access for Admin, Doctor, and Patient.
- User registration and login.

### 2. Patient Management
- Register new patients.
- Manage patient details.
- View patient information.

### 3. Doctor Management
- Manage doctor profiles and specializations.
- View doctor details.

### 4. Appointment Management
- Book appointments with doctors.
- Reschedule and cancel appointments.
- Manage appointment statuses.

### 5. Prescription Management
- Create and manage patient prescriptions.
- Maintain prescribed medicine details.

### 6. Inpatient Management
- Manage patient admissions.
- Allocate hospital rooms.
- Track room availability.

### 7. Billing Management
- Generate and manage patient bills.
- Maintain billing records.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Core programming language |
| Java Swing | Graphical User Interface |
| JDBC | Database connectivity |
| MySQL | Database management |
| Git & GitHub | Version control |

## OOP Concepts Used

- **Encapsulation:** Data hiding using private attributes and getters/setters.
- **Inheritance:** Reusing common properties through parent classes.
- **Abstraction:** Using abstract classes such as `Person`.
- **Polymorphism:** Supporting flexible object behaviour.
- **Singleton Pattern:** Managing a single shared instance where required.

## Project Structure

```text
Hospital_Management/
│
├── src/
│   ├── model/
│   ├── dao/
│   ├── gui/
│   ├── exceptions/
│   └── ...
│
├── database/
│   └── hospital_management.sql
│
├── .gitignore
└── README.md
```

*The package and folder names may vary depending on the actual project structure.*

## Prerequisites

- Java JDK
- MySQL Server 8.0+
- MySQL JDBC Driver
- Visual Studio Code or any Java IDE

## Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/sreelakshmiajayan-ship-it/Hospital_management.git
```

### 2. Open the Project

```bash
cd Hospital_management
```

### 3. Set Up the Database

1. Start your MySQL server.
2. Create the database and import the SQL file.

```sql
SOURCE database/hospital_management.sql;
```

### 4. Configure Database Credentials

Set the following environment variable before running the application:

```text
DB_PASSWORD=your_mysql_password
```

Make sure the database connection configuration uses your local MySQL username and password.

### 5. Run the Application

Open the project in VS Code or your preferred Java IDE, configure the MySQL JDBC driver, and run the main application class.

## Database

The application uses MySQL with tables for:

- Users
- Admins
- Patients
- Doctors
- Appointments
- Prescriptions
- Medicines
- Admissions
- Rooms
- Bills

## Contributors

Developed as a team project as part of the Object-Oriented Programming course.

## License

This project was developed for educational purposes.
