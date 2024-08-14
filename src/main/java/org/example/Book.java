package org.example;

import java.time.LocalDate; // Import LocalDate class for handling dates

public class Book {
    private String title; // The title of the book
    private String author; // The author of the book
    private int publicationYear; // The year the book was published
    private int pages; // The number of pages in the book
    private String category; // The category or genre of the book
    private boolean isOnLoan; // Whether the book is currently on loan
    private LocalDate loanDate; // The date the book was loaned out

    public Book(String title, String author, int publicationYear, int pages, String category) {
        this.title = title; // Initialize the title of the book
        this.author = author; // Initialize the author of the book
        this.publicationYear = publicationYear; // Initialize the publication year of the book
        this.pages = pages; // Initialize the number of pages in the book
        this.category = category; // Initialize the category of the book
        this.isOnLoan = false; // Initialize the book as not being on loan
    }

    // Getter methods for the book's properties
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public int getPages() { return pages; }
    public String getCategory() { return category; }
    public LocalDate getLoanDate() { return loanDate; }
    public boolean isOnLoan() { return isOnLoan; }

    // Setter method to set the book as on loan with a specific loan date
    public void setOnLoan(boolean isOnLoan, LocalDate loanDate) {
        this.isOnLoan = isOnLoan; // Set the onLoan status of the book
        this.loanDate = loanDate; // Set the loan date of the book
    }

    @Override
    public String toString() {
        // Returns a string representation of the book, including its title, author, publication year, category, loan status, and loan date
        return "Book [" + title + " by " + author + ", published: " + publicationYear +
                ", category: " + category + ", is loaned: " + isOnLoan +
                ", loan date: " + (loanDate != null ? loanDate : "N/A") + "]";
    }
}
