package org.dave.domain;

import org.dave.util.Constants;

public class Teacher extends BorrowingUser {

    public Teacher(String name) {
        super(name);
    }

    /**
     * Adds an item in Teacher's borrowedItems list.
     * If Teacher's borrowedItems list exceeds borrow limits,
     * throw an exception
     * @param item the item to be added
     * @return true if added the book successfully,
     * false if borrowed book is already in borrowedItems list
     */
    @Override
    public boolean borrowItem(Item item) {
        if (associatedLibrary == null) {
            throw new IllegalStateException("User is not registered to a library");
        }

        if (!associatedLibrary.hasItem(item)) {
            throw new IllegalArgumentException(String.format("%s is not available", item.getTitle()));
        }

        if (borrowedItems.size() >= Constants.MAX_BORROWABLE_ITEMS_TEACHERS) {
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
