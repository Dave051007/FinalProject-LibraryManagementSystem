package org.dave.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Library {
    private List<Item> items;
    private List<User> members;

    public Library() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    /**
     * Check if item is inside List of items
     * @param item the item
     * @return true if item is inside of items list
     */
    public boolean hasItem(Item item) {
        return items.contains(item);
    }

    /**
     * Adds an item in the Library items list
     * @param item the item to be added
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Adds a user to the member list
     * @param user the user to be added
     */
    public void addMember(User user) {
        members.add(user);
        user.setAssociatedLibrary(this);
    }
}
