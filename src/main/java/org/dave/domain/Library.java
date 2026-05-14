package org.dave.domain;

import lombok.Getter;

import java.util.*;
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
     * @return true if item is added,
     * false if item has similar id to one of the items in Library
     */
    public boolean registerItem(Item item) {
        for (Item item1 : items) {
            if (Objects.equals(item1.getId(), item.getId())) {
                return false;
            }
        }

        items.add(item);
        item.setStatus(Item.Status.IN_STORE);

        return true;
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
     * Removes an item in the Library items list
     * @param item the item to be removed
     * @return true if item is removed, false if item is not in Library
     */
    public boolean removeItem(Item item) {
        if (!items.contains(item)) {
            return false;
        }

        if (item.getStatus() == Item.Status.BORROWED) {
            throw new IllegalArgumentException("Cannot remove item because it is already borrowed");
        }

        items.remove(item);
        item.setStatus(Item.Status.NOT_REGISTERED);

        return true;
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
    public List<Item> searchStream(String keyWord) {
        Set<Item> result = items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyWord.toLowerCase()))
                .collect(Collectors.toSet());

        return result.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyWord))
                .sorted(Comparator.comparing(Item::getTitle))
                .toList();
    }

    // recursion version of searchStream
    public List<Item> searchRecursion(String keyWord) {
        if (items.isEmpty()) {
            return new ArrayList<>();
        }

        Item first = items.get(0);
        Library library = new Library();
        library.items = new ArrayList<>(items.subList(1, items.size()));
        List<Item> result = library.searchRecursion(keyWord);

        if (first.getTitle().toLowerCase().contains(keyWord.toLowerCase())
                && !result.contains(first)) {
            result.add(first);
        }

        return result;
    }
}
