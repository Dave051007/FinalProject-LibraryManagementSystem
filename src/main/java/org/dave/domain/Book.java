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

    @Setter private static int nextId = 1;

    public Book(String title, String isbn, String author, String genre, Library library) {
        super(title, library);

        if (!isValidISBN(isbn)) {
            throw new IllegalArgumentException("ISBN is invalid");
        }
        this.id = String.format("B%05d", nextId++);
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
