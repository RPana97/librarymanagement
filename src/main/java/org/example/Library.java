package org.example;

import java.time.LocalDate; // Import LocalDate class for handling dates
import java.time.temporal.ChronoUnit; // Import ChronoUnit for calculating date differences
import java.util.ArrayList; // Import ArrayList for handling dynamic lists
import java.util.List; // Import List interface for handling lists of books
import java.util.Optional; // Import Optional for handling potentially absent values
import java.util.stream.Collectors; // Import Collectors for working with streams

public class Library {
    private List<Book> books = new ArrayList<>(); // A list to store the books in the library
    private List<User> users = new ArrayList<>(); // A list to store the registered users of the library

    // Method to add a book to the library's collection
    public void addBook(Book book) {
        books.add(book); // Add the book to the list of books
    }

    // Method to remove a book from the library's collection
    public void removeBook(Book book) {
        books.remove(book); // Remove the book from the list of books
    }

    // Method to find books by their publication year
    public List<Book> findBooksByYear(int year) {
        return books.stream() // Create a stream of books
                .filter(book -> book.getPublicationYear() == year) // Filter books by the specified year
                .collect(Collectors.toList()); // Collect the results into a list
    }

    // Method to find books by a specific author
    public List<Book> findBooksByAuthor(String author) {
        return books.stream() // Create a stream of books
                .filter(book -> book.getAuthor().equalsIgnoreCase(author)) // Filter books by the specified author (case insensitive)
                .collect(Collectors.toList()); // Collect the results into a list
    }

    // Method to find the book with the most pages
    public Optional<Book> findBookWithMostPages() {
        return books.stream() // Create a stream of books
                .max((book1, book2) -> Integer.compare(book2.getPages(), book1.getPages())); // Find the book with the maximum number of pages
    }

    // Method to find books with more than a specified number of pages
    public List<Book> findBooksMoreThanPages(int pages) {
        return books.stream() // Create a stream of books
                .filter(book -> book.getPages() > pages) // Filter books that have more than the specified number of pages
                .collect(Collectors.toList()); // Collect the results into a list
    }

    // Method to print the titles of all books in the library in alphabetical order
    public void printBookTitlesAlphabetically() {
        books.stream() // Create a stream of books
                .map(Book::getTitle) // Map each book to its title
                .sorted() // Sort the titles alphabetically
                .forEach(System.out::println); // Print each title
    }

    // Method to find books by their category
    public List<Book> findBooksByCategory(String category) {
        return books.stream() // Create a stream of books
                .filter(book -> book.getCategory().equalsIgnoreCase(category)) // Filter books by the specified category (case insensitive)
                .collect(Collectors.toList()); // Collect the results into a list
    }

    // Method to loan a book to a user
    public boolean loanBook(String title, User user) {
        Optional<Book> bookToLoan = books.stream() // Create a stream of books
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && !book.isOnLoan()) // Filter books by the specified title and ensure the book is not already on loan
                .findFirst(); // Find the first matching book

        if (bookToLoan.isPresent()) { // Check if the book was found
            Book book = bookToLoan.get(); // Get the book
            book.setOnLoan(true, LocalDate.now()); // Set the book as on loan with the current date
            user.loanBook(book); // Add the book to the user's loaned books
            return true; // Return true indicating the loan was successful
        }
        return false; // Return false indicating the loan was unsuccessful
    }

    // Method to return a book from a user
    public boolean returnBook(String title, User user) {
        Optional<Book> bookToReturn = user.getLoanedBooks().stream() // Create a stream of the user's loaned books
                .filter(book -> book.getTitle().equalsIgnoreCase(title)) // Filter books by the specified title
                .findFirst(); // Find the first matching book

        if (bookToReturn.isPresent()) { // Check if the book was found
            Book book = bookToReturn.get(); // Get the book
            book.setOnLoan(false, null); // Set the book as not on loan and clear the loan date
            user.returnBook(book); // Remove the book from the user's loaned books
            return true; // Return true indicating the return was successful
        }
        return false; // Return false indicating the return was unsuccessful
    }

    // Method to register a user with the library
    public void registerUser(User user) {
        users.add(user); // Add the user to the list of registered users
    }

    // Method to calculate late fees for a user based on overdue books
    public double calculateLateFees(User user) {
        return user.getLoanedBooks().stream() // Create a stream of the user's loaned books
                .filter(book -> book.getLoanDate().plusWeeks(2).isBefore(LocalDate.now())) // Filter books that are overdue (loaned more than 2 weeks ago)
                .mapToDouble(book -> ChronoUnit.DAYS.between(book.getLoanDate().plusWeeks(2), LocalDate.now()) * 1.0) // Calculate the number of overdue days and multiply by the daily late fee (assumed $1.0 per day)
                .sum(); // Sum the late fees for all overdue books
    }
}
