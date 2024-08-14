package org.example;

import java.time.LocalDate; // Import LocalDate class for handling dates
import java.time.temporal.ChronoUnit; // Import ChronoUnit for calculating date differences
import java.util.List; // Import List interface for handling lists of books

public class Main {
    public static void main(String[] args) {
        Library library = new Library(); // Create a new Library instance

        // Add books to the library
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 180, "Fiction"));
        library.addBook(new Book("A Brief History of Time", "Stephen Hawking", 1988, 212, "Science"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960, 281, "Fiction"));
        library.addBook(new Book("1984", "George Orwell", 1949, 328, "Fiction"));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 214, "Fiction"));

        // Register users
        User user1 = new User("Alice", "001"); // Create a new User instance for Alice
        User user2 = new User("Bob", "002"); // Create a new User instance for Bob
        library.registerUser(user1); // Register Alice in the library
        library.registerUser(user2); // Register Bob in the library

        // Loan out books to Alice
        System.out.println("Loaning out books to Alice...");
        library.loanBook("1984", user1); // Loan the book "1984" to Alice
        library.loanBook("To Kill a Mockingbird", user1); // Loan the book "To Kill a Mockingbird" to Alice

        // Display Alice's loaned books
        System.out.println("Alice's loaned books: " + user1.getLoanedBooks()); // Print Alice's loaned books

        // Simulate overdue books for Alice by setting loan dates in the past
        for (Book book : user1.getLoanedBooks()) { // Loop through Alice's loaned books
            book.setOnLoan(true, LocalDate.now().minusWeeks(3)); // Set the loan date to 3 weeks ago
        }

        // Calculate and print overdue days and late fees for Alice
        System.out.println("\nOverdue information for " + user1.getName() + ":");
        for (Book book : user1.getLoanedBooks()) { // Loop through Alice's loaned books again
            if (book.getLoanDate().plusWeeks(2).isBefore(LocalDate.now())) { // Check if the book is overdue
                long overdueDays = ChronoUnit.DAYS.between(book.getLoanDate().plusWeeks(2), LocalDate.now()); // Calculate the number of overdue days
                System.out.println(book.getTitle() + " is overdue by " + overdueDays + " days."); // Print the overdue days
            }
        }

        double lateFeesAlice = library.calculateLateFees(user1); // Calculate Alice's late fees
        System.out.println("Late fees for " + user1.getName() + ": $" + lateFeesAlice); // Print Alice's late fees

        System.out.println ("\nBob's loaned books: " + user2.getLoanedBooks()); // Print Bob's loaned books (should be empty)

        // Loan out books to Bob
        System.out.println("\nLoaning out books to Bob...");
        library.loanBook("The Great Gatsby", user2); // Loan the book "The Great Gatsby" to Bob

        // Display Bob's loaned books
        System.out.println("Bob's loaned books: " + user2.getLoanedBooks()); // Print Bob's loaned books

        // Simulate overdue books for Bob by setting loan dates in the past
        for (Book book : user2.getLoanedBooks()) { // Loop through Bob's loaned books
            book.setOnLoan(true, LocalDate.now().minusWeeks(1)); // Set the loan date to 1 week ago
        }

        // Calculate and print overdue days and late fees for Bob
        System.out.println("\nOverdue information for " + user2.getName() + ":");
        for (Book book : user2.getLoanedBooks()) { // Loop through Bob's loaned books again
            if (book.getLoanDate().plusWeeks(2).isBefore(LocalDate.now())) { // Check if the book is overdue
                long overdueDays = ChronoUnit.DAYS.between(book.getLoanDate().plusWeeks(2), LocalDate.now()); // Calculate the number of overdue days
                System.out.println(book.getTitle() + " is overdue by " + overdueDays + " days."); // Print the overdue days
            }
        }

        double lateFeesBob = library.calculateLateFees(user2); // Calculate Bob's late fees
        System.out.println("Late fees for " + user2.getName() + ": $" + lateFeesBob); // Print Bob's late fees

        // Return books for Alice
        System.out.println("\nReturning books for Alice...");
        library.returnBook("1984", user1); // Return the book "1984" for Alice
        library.returnBook("To Kill a Mockingbird", user1); // Return the book "To Kill a Mockingbird" for Alice

        // Display Alice's loaned books after return
        System.out.println("Alice's loaned books after returning: " + user1.getLoanedBooks()); // Print Alice's loaned books after return (should be empty)

        // Find books by publication year
        System.out.println("\nBooks published in 1949:");
        List<Book> books1949 = library.findBooksByYear(1949); // Find books published in 1949
        books1949.forEach(System.out::println); // Print each book published in 1949

        // Find books by author
        System.out.println("\nBooks by George Orwell:");
        List<Book> orwellBooks = library.findBooksByAuthor("George Orwell"); // Find books by George Orwell
        orwellBooks.forEach(System.out::println); // Print each book by George Orwell

        // Find books by category
        System.out.println("\nBooks in the Fiction category:");
        List<Book> fictionBooks = library.findBooksByCategory("Fiction"); // Find books in the Fiction category
        fictionBooks.forEach(System.out::println); // Print each book in the Fiction category

        // Find books with more than a specific number of pages
        System.out.println("\nBooks with more than 200 pages:");
        List<Book> booksMoreThan200Pages = library.findBooksMoreThanPages(200); // Find books with more than 200 pages
        booksMoreThan200Pages.forEach(System.out::println); // Print each book with more than 200 pages

        // Find the book with the most pages
        System.out.println("\nThe book with the most pages:");
        library.findBookWithMostPages().ifPresent(System.out::println); // Print the book with the most pages if it exists
    }
}
