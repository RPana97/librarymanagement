package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books to the library
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 180, "Fiction"));
        library.addBook(new Book("A Brief History of Time", "Stephen Hawking", 1988, 212, "Science"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960, 281, "Fiction"));
        library.addBook(new Book("1984", "George Orwell", 1949, 328, "Fiction"));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 214, "Fiction"));

        // Register users
        User user1 = new User("Alice", "001");
        User user2 = new User("Bob", "002");
        library.registerUser(user1);
        library.registerUser(user2);

        // Loan out books to Alice
        System.out.println("Loaning out books to Alice...");
        library.loanBook("1984", user1);
        library.loanBook("To Kill a Mockingbird", user1);

        // Display Alice's loaned books
        System.out.println("Alice's loaned books: " + user1.getLoanedBooks());

        // Simulate overdue books for Alice by setting loan dates in the past
        for (Book book : user1.getLoanedBooks()) {
            book.setOnLoan(true, LocalDate.now().minusWeeks(3));
        }

        // Calculate and print overdue days and late fees for Alice
        System.out.println("\nOverdue information for " + user1.getName() + ":");
        for (Book book : user1.getLoanedBooks()) {
            if (book.getLoanDate().plusWeeks(2).isBefore(LocalDate.now())) {
                long overdueDays = ChronoUnit.DAYS.between(book.getLoanDate().plusWeeks(2), LocalDate.now());
                System.out.println(book.getTitle() + " is overdue by " + overdueDays + " days.");
            }
        }

        double lateFeesAlice = library.calculateLateFees(user1);
        System.out.println("Late fees for " + user1.getName() + ": $" + lateFeesAlice);

        System.out.println ("\nBob's loaned books: " + user2.getLoanedBooks());

        // Loan out books to Bob
        System.out.println("\nLoaning out books to Bob...");
        library.loanBook("The Great Gatsby", user2);

        // Display Bob's loaned books
        System.out.println("Bob's loaned books: " + user2.getLoanedBooks());

        // Simulate overdue books for Bob by setting loan dates in the past
        for (Book book : user2.getLoanedBooks()) {
            book.setOnLoan(true, LocalDate.now().minusWeeks(1));
        }

        // Calculate and print overdue days and late fees for Bob
        System.out.println("\nOverdue information for " + user2.getName() + ":");
        for (Book book : user2.getLoanedBooks()) {
            if (book.getLoanDate().plusWeeks(2).isBefore(LocalDate.now())) {
                long overdueDays = ChronoUnit.DAYS.between(book.getLoanDate().plusWeeks(2), LocalDate.now());
                System.out.println(book.getTitle() + " is overdue by " + overdueDays + " days.");
            }
        }

        double lateFeesBob = library.calculateLateFees(user2);
        System.out.println("Late fees for " + user2.getName() + ": $" + lateFeesBob);

        // Return books for Alice
        System.out.println("\nReturning books for Alice...");
        library.returnBook("1984", user1);
        library.returnBook("To Kill a Mockingbird", user1);

        // Display Alice's loaned books after return
        System.out.println("Alice's loaned books after returning: " + user1.getLoanedBooks());

        // Find books by publication year
        System.out.println("\nBooks published in 1949:");
        List<Book> books1949 = library.findBooksByYear(1949);
        books1949.forEach(System.out::println);

        // Find books by author
        System.out.println("\nBooks by George Orwell:");
        List<Book> orwellBooks = library.findBooksByAuthor("George Orwell");
        orwellBooks.forEach(System.out::println);

        // Find books by category
        System.out.println("\nBooks in the Fiction category:");
        List<Book> fictionBooks = library.findBooksByCategory("Fiction");
        fictionBooks.forEach(System.out::println);

        // Find books with more than a specific number of pages
        System.out.println("\nBooks with more than 200 pages:");
        List<Book> booksMoreThan200Pages = library.findBooksMoreThanPages(200);
        booksMoreThan200Pages.forEach(System.out::println);

        // Find the book with the most pages
        System.out.println("\nThe book with the most pages:");
        library.findBookWithMostPages().ifPresent(System.out::println);
    }
}
