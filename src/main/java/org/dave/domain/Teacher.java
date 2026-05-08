package org.dave.domain;

import org.dave.interfaces.Borrower;
import org.dave.util.Constants;

import java.util.List;

public class Teacher extends User implements Borrower {
    private List<Item> borrowedItems;

    public Teacher(String name) {
        super(name);
        this.borrowedItems = borrowedItems;
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
        if (borrowedItems.size() == Constants.MAX_BORROWABLE_ITEMS_TEACHERS) {
            throw new IllegalArgumentException("Too many borrowed items");
        }

        if (borrowedItems.contains(item)) {
            return false;
        }

        borrowedItems.add(item);
        return true;
    }
}
