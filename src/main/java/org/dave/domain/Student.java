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
    public boolean borrow(Item item) {
        if (!(item instanceof Book)) {
            throw new IllegalArgumentException("Item is not Book");
        } else if (borrowedItems.size() == Constants.MAX_BORROWABLE_BOOKS_STUDENTS) {
            throw new IllegalArgumentException("Too many borrowed items");
        }

        if (borrowedItems.contains(item)) {
            return false;
        }

        borrowedItems.add(item);
        return true;
    }
}
