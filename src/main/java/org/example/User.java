package org.example;

import java.util.ArrayList; // Import ArrayList for handling dynamic lists
import java.util.List; // Import List interface for handling lists of books

public class User {
    private String name; // The name of the user
    private String libraryCardNumber; // The user's library card number
    private List<Book> loanedBooks = new ArrayList<>(); // A list to store the books loaned by the user

    public User(String name, String libraryCardNumber) {
        this.name = name; // Initialize the user's name
        this.libraryCardNumber = libraryCardNumber; // Initialize the user's library card number
    }

    // Getter methods for the user's properties
    public String getName() { return name; }
    public String getLibraryCardNumber() { return libraryCardNumber; }
    public List<Book> getLoanedBooks() { return loanedBooks; }

    // Method to add a book to the user's loaned books
    public void loanBook(Book book) {
        loanedBooks.add(book); // Add the book to the list of loaned books
    }

    // Method to remove a book from the user's loaned books
    public void returnBook(Book book) {
        loanedBooks.remove(book); // Remove the book from the list of loaned books
    }

    @Override
    public String toString() {
        // Returns a string representation of the user, including their name, library card number, and loaned books
        return "User [name: " + name + ", libraryCardNumber: " + libraryCardNumber + ", loaned Books: " + loanedBooks + "]";
    }
}
