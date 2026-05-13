package org.dave.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Book extends Item {
    private String isbn;
    private String author;
    private String genre;

    public Book(String title, Status status, String isbn, String author, String genre) {
        super(title, status);

        if (!isValidISBN(isbn)) {
            throw new IllegalArgumentException("ISBN is invalid");
        }

        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
    }

    /**
     * Checks if ISBN is valid number or not
     * @param isbn the isbn to be checked
     * @return true if isbn is valid, else false
     */
    private static boolean isValidISBN(String isbn) {
        if  (isbn == null) {
            return false;
        }

        return isbn.replaceAll("-", "").matches("\\d{13}");
    }
}
