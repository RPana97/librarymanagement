package org.example;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private int pages;
    private String category;
    private boolean isOnLoan;
    private LocalDate loanDate;

    public Book(String title, String author, int publicationYear, int pages, String category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.pages = pages;
        this.category = category;
        this.isOnLoan = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public int getPages() { return pages; }
    public String getCategory() { return category; }
    public LocalDate getLoanDate() { return loanDate; }
    public boolean isOnLoan() { return isOnLoan; }

    public void setOnLoan(boolean isOnLoan, LocalDate loanDate) {
        this.isOnLoan = isOnLoan;
        this.loanDate = loanDate;
    }

    @Override
    public String toString() {
        return "Book [" + title + " by " + author + ", published: " + publicationYear +
                ", category: " + category + ", is loaned: " + isOnLoan +
                ", loan date: " + (loanDate != null ? loanDate : "N/A") + "]";
    }
}
