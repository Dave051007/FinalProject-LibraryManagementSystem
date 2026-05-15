# Library Management System Guide

# Introduction

This guide explains how to use the Library Management System, including creating users, managing items, borrowing, returning, searching, and exporting data.

---

# Creating a Library

Create a new library instance:

```java
Library library = new Library();
```

This initializes:

* Item storage
* Member storage

---

# Adding Users

## Create a Student

```java
Student student = new Student("John", library);
```

## Create a Teacher

```java
Teacher teacher = new Teacher("Alice", library);
```

## Create an Admin

```java
Admin admin = new Admin("Dave", library);
```

Users are automatically registered to the library.

---

# Adding Items

## Add a Book

```java
Book book = new Book(
    "Java Basics",
    "9781234567890",
    "Author Name",
    "Programming",
    library
);
```

## Add a DVD

```java
DVD dvd = new DVD(
    "Movie Title",
    "Director Name",
    120,
    library
);
```

## Add a Magazine

```java
Magazine magazine = new Magazine(
    "Tech Weekly",
    42,
    "Publisher",
    library
);
```

Items are automatically registered to the library.

---

# Borrowing Items

## Student borrowing

Students can borrow **books only**:

```java
student.borrowItem(book);
```

Restrictions:

* Cannot borrow DVDs or magazines
* Borrow limit enforced

---

## Teacher borrowing

Teachers can borrow any item:

```java
teacher.borrowItem(dvd);
```

Restrictions:

* Borrow limit enforced

---

# Returning Items

Return borrowed item:

```java
student.returnItem(book);
```

This:

* Removes item from borrowed list
* Changes item status to `IN_STORE`

---

# Losing Borrowed Items

Randomly lose an item:

```java
student.loseRandomBorrowedItem();
```

This:

* Removes item from borrowed list
* Changes status to `LOST`

---

# Searching Items

## Stream search

```java
library.searchStream("java");
```

Features:

* Case-insensitive
* Sorted alphabetically
* Duplicate titles removed
* Only available items shown

---

## Recursive search

```java
library.searchRecursion("java");
```

Same behavior using recursion.

---

# Removing Items

```java
library.removeItem(book);
```

Rules:

* Cannot remove borrowed items
* Removed items become `LOST`

---

# Removing Members

```java
library.removeMember(student);
```

This unregisters the user from the library.

---

# Generating Reports (Admin)

```java
admin.report();
```

Returns items grouped by:

* IN_STORE
* BORROWED
* LOST

---

# Importing Existing Data

Load users:

```java
library.initUsers();
```

Load items:

```java
library.initItems();
```

---

# Exporting Data

Export all library data:

```java
library.export();
```

Creates/updates:

* users.csv
* books.csv
* dvd.csv
* magazine.csv

---

# Best Practices

* Always initialize library first
* Use export after modifications
* Run tests after code changes

---

# End of Guide
