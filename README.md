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

