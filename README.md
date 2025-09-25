# Library Management System (Java Console Application)

A **console-based Library Management System** written in **Java**.\
This program allows users to **add books, add users, issue books, return
books, and view available books** with proper input validation and error
handling. It demonstrates **Object-Oriented Programming (OOP)** concepts
such as **classes, objects, encapsulation, and collections**.

------------------------------------------------------------------------

## Features

-   **Add Book**: Add new books with unique IDs, title, and author.\
-   **Add User**: Add users with unique IDs and name.\
-   **View Available Books**: Displays all books currently available in
    the library.\
-   **Issue Book**:
    -   Only issues if books are available.\
    -   Validates user existence before proceeding.\
    -   Updates book status and adds it to user's borrowed list.\
-   **Return Book**:
    -   Validates user existence.\
    -   Skips asking for book ID if user has not borrowed any books.\
    -   Updates availability when a book is returned.\
-   **Input Validation**:
    -   Ensures IDs remain as entered (e.g., `001` is preserved).\
    -   Prevents issuing when no books are available.\
    -   Prevents returning when user has no borrowed books.\
    -   Handles invalid or malformed inputs gracefully.\
-   **User-Friendly Interface**:
    -   Menu-driven CLI\
    -   Delay and "Press Enter to continue" prompt for smooth UX

------------------------------------------------------------------------

## Project Structure

    LibraryManagementSystem/
    |- LibraryManagementSystem.java   # Main program with menu-driven CLI
    |- README.md                      # Project documentation

------------------------------------------------------------------------

## How to Run

1.  **Clone or download** the project.

2.  Open a terminal and navigate to the project folder.

3.  Compile and run the program:

    ``` bash
    javac LibraryManagementSystem.java
    java LibraryManagementSystem
    ```

------------------------------------------------------------------------

## Example Usage

``` bash
<<< Library Management System >>>
1. Add Book
2. Add User
3. View Available Books
4. Issue Book
5. Return Book
6. Exit
Enter your choice: 1

--- Add Book ---
Enter Book ID: 001
Enter Book Title: Java Programming
Enter Book Author: James Gosling
Book added successfully!

Press Enter to continue...
```

**Issuing a Book Example:**

``` bash
Enter User ID: U001
Enter Book ID: 001
Book issued successfully to Alice
Press Enter to continue...
```

**Returning a Book Example:**

``` bash
Enter User ID: U001
Enter Book ID: 001
Book returned successfully by Alice
Press Enter to continue...
```

**Error Handling Example -- No Available Books:**

``` bash
Oops, No books available to issue.
Press Enter to continue...
```

**Error Handling Example -- User Not Found:**

``` bash
Oops, User not found!
Press Enter to continue...
```

------------------------------------------------------------------------

## Concepts Showcased

-   **Object-Oriented Programming (OOP)**:
    -   `Book`, `User`, and `Library` classes
    -   Encapsulation via getters/setters
    -   Composition (`User` has a list of borrowed `Book`s, `Library`
        manages collections)\
-   **Collections**:
    -   `ArrayList` used for storing books and users\
-   **Input Handling**:
    -   `Scanner` for CLI inputs\
    -   Error handling with try--catch

------------------------------------------------------------------------

## Future Enhancements

-   Add persistence (save data to file/DB).\
-   Add search functionality for books and users.\
-   Implement fine calculation for late returns.\
-   Enhance UI with a GUI-based interface.
