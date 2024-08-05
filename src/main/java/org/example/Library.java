package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> findBooksByYear(int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public Optional<Book> findBookWithMostPages() {
        return books.stream()
                .max((book1, book2) -> Integer.compare(book2.getPages(), book1.getPages()));
    }

    public List<Book> findBooksMoreThanPages(int pages) {
        return books.stream()
                .filter(book -> book.getPages() > pages)
                .collect(Collectors.toList());
    }

    public void printBookTitlesAlphabetically() {
        books.stream()
                .map(Book::getTitle)
                .sorted()
                .forEach(System.out::println);
    }

    public List<Book> findBooksByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public boolean loanBook(String title, User user) {
        Optional<Book> bookToLoan = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && !book.isOnLoan())
                .findFirst();

        if (bookToLoan.isPresent()) {
            Book book = bookToLoan.get();
            book.setOnLoan(true, LocalDate.now());
            user.loanBook(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(String title, User user) {
        Optional<Book> bookToReturn = user.getLoanedBooks().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (bookToReturn.isPresent()) {
            Book book = bookToReturn.get();
            book.setOnLoan(false, null);
            user.returnBook(book);
            return true;
        }
        return false;
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public double calculateLateFees(User user) {
        return user.getLoanedBooks().stream()
                .filter(book -> book.getLoanDate().plusWeeks(2).isBefore(LocalDate.now()))
                .mapToDouble(book -> ChronoUnit.DAYS.between(book.getLoanDate().plusWeeks(2), LocalDate.now()) * 1.0)
                .sum();
    }
}
