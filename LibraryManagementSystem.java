import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ======================= Book Class ===========================
class Book {
    private final String bookId; // Changed to String to preserve formatting (e.g., 001)
    private String title;
    private String author;
    private boolean available;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true; // Default: new book is available
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "[" + bookId + "] " + title + " by " + author + (available ? " (Available)" : " (Issued)");
    }
}

// ======================= User Class ===========================
class User {
    private final String userId; // Changed to String
    private String name;
    private List<Book> borrowedBooks;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Borrowed Books: " + borrowedBooks.size();
    }
}

// ======================= Library Class ===========================
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Add book
    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getBookId().equals(book.getBookId())) {
                System.out.println("Oops, Book with ID " + book.getBookId() + " already exists!");
                return;
            }
        }
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // Add user
    public void addUser(User user) {
        for (User u : users) {
            if (u.getUserId().equals(user.getUserId())) {
                System.out.println("Oops, User with ID " + user.getUserId() + " already exists!");
                return;
            }
        }
        users.add(user);
        System.out.println("User added successfully!");
    }

    // Display available books
    public void displayAvailableBooks() {
        boolean found = false;
        System.out.println("\nAvailable Books:");
        for (Book b : books) {
            if (b.isAvailable()) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Oops, No books are currently available.");
        }
    }

    // Issue book
    public void issueBook(String userId, String bookId) {
        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (user == null) {
            System.out.println("Oops, User not found!");
            return;
        }
        if (book == null) {
            System.out.println("Oops, Book not found!");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Oh-no, Book is already issued!");
            return;
        }

        book.setAvailable(false);
        user.borrowBook(book);
        System.out.println("Book issued successfully to " + user.getName());
    }

    // Return book
    public void returnBook(String userId, String bookId) {
        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (user == null) {
            System.out.println("Oops, User not found!");
            return;
        }
        if (book == null) {
            System.out.println("Oops, Book not found!");
            return;
        }
        if (!user.getBorrowedBooks().contains(book)) {
            System.out.println("This user did not borrow this book!");
            return;
        }

        book.setAvailable(true);
        user.returnBook(book);
        System.out.println("Book returned successfully by " + user.getName());
    }

    // Helper: find user
    public User findUserById(String userId) {
        for (User u : users) {
            if (u.getUserId().equals(userId))
                return u;
        }
        return null;
    }

    // Helper: find book
    public Book findBookById(String bookId) {
        for (Book b : books) {
            if (b.getBookId().equals(bookId))
                return b;
        }
        return null;
    }

    public boolean hasAvailableBooks() {
        for (Book b : books) {
            if (b.isAvailable())
                return true;
        }
        return false;
    }
}

// ======================= Main Application ===========================
public class LibraryManagementSystem {

    private static Scanner sc = new Scanner(System.in);

    // Utility delay
    private static void delay() {
        try {
            Thread.sleep(600); // short delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("\n-------------------------------------------------");
    }

    public static void main(String[] args) {

        Library library = new Library();

        while (true) {
            System.out.println("\n<<< Library Management System >>>");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. View Available Books");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Oops, Invalid input. Please enter a number.");
                delay();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bookId = sc.nextLine().trim();
                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine().trim();
                    System.out.print("Enter Book Author: ");
                    String author = sc.nextLine().trim();
                    library.addBook(new Book(bookId, title, author));
                    delay();
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    String userId = sc.nextLine().trim();
                    System.out.print("Enter User Name: ");
                    String name = sc.nextLine().trim();
                    library.addUser(new User(userId, name));
                    delay();
                    break;

                case 3:
                    library.displayAvailableBooks();
                    delay();
                    break;

                case 4:
                    System.out.print("Enter User ID: ");
                    String uId = sc.nextLine().trim();
                    User user = library.findUserById(uId);

                    if (!library.hasAvailableBooks()) {
                        System.out.println("Oops, No books available to issue.");
                        delay();
                        break;
                    }

                    if (user == null) {
                        System.out.println("Oops, User not found!");
                        delay();
                        break;
                    }

                    System.out.print("Enter Book ID: ");
                    String bId = sc.nextLine().trim();
                    library.issueBook(uId, bId);
                    delay();
                    break;

                case 5:
                    System.out.print("Enter User ID: ");
                    String ruId = sc.nextLine().trim();
                    User rUser = library.findUserById(ruId);
                    if (rUser == null) {
                        System.out.println("Oops, User not found!");
                        delay();
                        break;
                    }
                    if (rUser.getBorrowedBooks().isEmpty()) {
                        System.out.println("This user has not borrowed any books yet.");
                        delay();
                        break;
                    }
                    System.out.print("Enter Book ID: ");
                    String rbId = sc.nextLine().trim();
                    library.returnBook(ruId, rbId);
                    delay();
                    break;

                case 6:
                    System.out.println("Exiting Library System. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select between 1-6.");
                    delay();
            }
        }
    }
}
