package org.dave.domain;

import lombok.Getter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * Removes item in the items list
     * @param item the item to be removed
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Removes a user in the members list
     * @param user the user to be removed
     */
    public void removeMember(User user) {
        members.remove(user);
        user.setAssociatedLibrary(null);
    }

    /**
     * Allows user to search for items available in their associated library
     * @param keyWord the title of the item the user is searching for
     * @return a list of items containing the keyWord in their title
     */
    public List<Item> streamSearch(String keyWord) {
        Set<Item> result = items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyWord))
                .collect(Collectors.toSet());

        return result.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyWord))
                .sorted(Comparator.comparing(Item::getTitle))
                .toList();
    }
}
