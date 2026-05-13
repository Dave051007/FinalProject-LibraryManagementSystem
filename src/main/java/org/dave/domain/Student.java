package org.dave.domain;

import org.dave.util.Constants;

public class Student extends BorrowingUser {

    public Student(String name) {
        super(name);
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
        if (associatedLibrary == null) {
            throw new IllegalStateException("User is not registered to a library");
        }

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
        item.setStatus(Item.Status.BORROWED);

        return true;
    }
}
