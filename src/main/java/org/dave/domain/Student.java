package org.dave.domain;

import org.dave.interfaces.Borrower;
import org.dave.util.Constants;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements Borrower {
    private List<Item> borrowedItems;

    public Student(String name) {
        super(name);
        this.borrowedItems = new ArrayList<>();
    }

    /**
     * Adds a book in Student's borrowedItems list.
     * If item is not a book, or if Student's exceeds borrow limits,
     * throw an exception
     * @param item the book to be added
     * @return true if added the book successfully,
     * false if borrowed book is already in borrowedItems list
     */
    @Override
    public boolean borrowItem(Item item) {
        if (!associatedLibrary.hasItem(item)) {
            throw new IllegalArgumentException("Book is not available");
        }

        if (!(item instanceof Book)) {
            throw new IllegalArgumentException("Item is not Book");
        }

        if (borrowedItems.size() >= Constants.MAX_BORROWABLE_BOOKS_STUDENTS) {
            throw new IllegalArgumentException("Too many borrowed items");
        }

        if (borrowedItems.contains(item)) {
            return false;
        }

        borrowedItems.add(item);
        associatedLibrary.getItems().remove(item);
        return true;
    }

    /**
     * Removes book from borrowedItem list, then adds book back
     * in library items list
     * @param item the item to be removed
     * @return true if returned item successfully,
     * false if item is not in borrowedItems list or if item is not a book
     */
    @Override
    public boolean returnItem(Item item) {
        if (!borrowedItems.contains(item) || !(item instanceof Book)) {
            return false;
        }

        borrowedItems.remove(item);
        associatedLibrary.addItem(item);
        return true;
    }
}
