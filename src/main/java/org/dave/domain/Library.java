package org.dave.domain;

import lombok.Getter;
import java.util.List;

@Getter
public class Library {
    private List<Item> items;
    private List<Item> members;

    /**
     * Check if item is inside List of items
     * @param item the item
     * @return true if item is inside of items list
     */
    public boolean hasItem(Item item) {
        return items.contains(item);
    }
}
