# Final Project - Library Management System

### Author: Lauren Dave Fermin
### ID: 6375254

---

## Overview
This project demonstrates a Library Management System in Java. The system will utilize 
object-oriented programming concepts,including inheritance, polymorphism, and abstract classes/interfaces.
It is designed to manage library users, items, borrowing operations, searching, and CSV based data persistance.
The system supports:

- Managing library members
- Registering and removing library items
- Borrowing and returning items
- Searching items using stream and recursion implementations
- Exporting/importing data from CSV files
- Generating reports for administrators
- Unit testing for system validation

---

## Features

## User Management

The system supports three user types:

### Student
- Can borrow books only
- Has borrow limit of 5
- Can return borrowed books
- Can lose borrowed books randomly

### Teacher
- Can borrow any item type
- Higher borrow limit than students
- Can return borrowed items
- Can lose borrowed items randomly

### Admin
- Can generate reports of library inventory

---

## Item Management

The system supports three item types:

### Book
Includes:
- ISBN validation (13 digits)
- Author
- Genre

### DVD
Includes:
- Director
- Duration

### Magazine
Includes:
- Issue number
- Publisher

Each item has:
- Unique ID
- Title
- Status:
    - `IN_STORE`
    - `BORROWED`
    - `LOST`

---

## Search System
- Search case-insensitively
- Filter available items only
- Remove duplicate titles
- Sort alphabetically

Two search implementations are provided:
###  `Stream Search` 
### `Recursive Search`

---

# Project Structure
```
📂 LibraryManagementSystem
 ┣ 📂 src/main/java/com/library
 ┃ ┣ 📂 domain
 ┃ ┃ ┣ 📜 Item.java           (Abstract class for library items)
 ┃ ┃ ┣ 📜 Book.java           (Extends Item)
 ┃ ┃ ┣ 📜 DVD.java            (Extends Item)
 ┃ ┃ ┣ 📜 Magazine.java       (Extends Item)
 ┃ ┃ ┣ 📜 User.java           (Abstract class for users)
 ┃ ┃ ┣ 📜 BorrowingUser.java  (Extends User)
 ┃ ┃ ┣ 📜 Student.java        (Extends BorrowingUser)
 ┃ ┃ ┣ 📜 Teacher.java        (Extends BorrowingUser)
 ┃ ┃ ┣ 📜 Admin.java          (Extends User)
 ┃ ┃ ┣ 📜 Library.java        (For the Library)
 ┃ ┣ 📂 util
 ┃ ┃ ┣ 📜 Constants.java      (Holds static constants (Optional))
 ┃ ┣ 📂 interfaces
 ┃ ┃ ┣ 📜 Reportable.java     (Interface for reporting functionality)
 ┃ ┃ ┣ 📜 Borrower.java       (Interface for borrowing functionality)
 ┃ ┣ 📜 Main.java             (Main class to run the system)
 ┣ 📂 src/main/resources
 ┃ ┣ 📜 book.csv
 ┃ ┣ 📜 dvd.csv
 ┃ ┣ 📜 magazine.csv
 ┃ ┣ 📜 user.csv
 ┣ 📂 src/test/java/com/library
 ┃ ┣ 📜 BorrowingUserTest
 ┃ ┣ 📜 LibraryTest
 ┃ ┣ 📜 StudentTest
 ┃ ┣ 📜 TeacherTest
 ┣ 📜 pom.xml                 (Maven dependencies)
 ┗ 📜 README.md
```
---

# Core Methods

## Library

- `registerItem()`
- `removeItem()`
- `addMember()`
- `removeMember()`
- `searchStream()`
- `searchRecursion()`
- `initUsers()`
- `initItems()`
- `export()`

## BorrowingUser

- `borrowItem()`
- `returnItem()`
- `loseRandomBorrowedItem()`

## Admin

- `report()`

---

# Testing

Unit tests cover:

- Registration logic
- Member removal
- Borrowing constraints
- Returning items
- Lost item behavior
- Search correctness
- Duplicate handling

