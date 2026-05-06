package org.dave.domain;

public class Book extends Item {
    private String ISBN;
    private String author;
    private String genre;

    public Book(String title, Status status, String ISBN, String author, String genre) {
        super(title, status);
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
    }
}
