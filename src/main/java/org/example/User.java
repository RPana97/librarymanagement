package org.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String libraryCardNumber;
    private List<Book> loanedBooks = new ArrayList<>();

    public User(String name, String libraryCardNumber) {
        this.name = name;
        this.libraryCardNumber = libraryCardNumber;
    }

    public String getName() { return name; }
    public String getLibraryCardNumber() { return libraryCardNumber; }
    public List<Book> getLoanedBooks() { return loanedBooks; }

    public void loanBook(Book book) {
        loanedBooks.add(book);
    }

    public void returnBook(Book book) {
        loanedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "User [name: " + name + ", libraryCardNumber: " + libraryCardNumber + ", loaned Books: " + loanedBooks + "]";
    }
}
