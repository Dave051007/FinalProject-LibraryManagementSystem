package org.dave.domain;

import org.dave.interfaces.Borrower;
import org.dave.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends User implements Borrower {
    private List<Item> borrowedItems;

    public Teacher(String name) {
        super(name);
        this.borrowedItems = new ArrayList<>();
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
        return true;
    }

    /**
     * Removes item from borrowedItem list, then adds item back
     * in library items list
     * @param item the item to be removed
     * @return true if returned item successfully,
     * false if item is not in borrowedItems list
     */
    @Override
    public boolean returnItem(Item item) {
        if (!borrowedItems.contains(item)) {
            return false;
        }

        borrowedItems.remove(item);
        associatedLibrary.addItem(item);
        return true;
    }
}
