package org.dave.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.dave.interfaces.Borrower;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BorrowingUser extends User implements Borrower {
    protected List<Item> borrowedItems;
    protected List<Item> lostItems;

    public BorrowingUser(String name) {
        super(name);
        borrowedItems = new ArrayList<>();
        lostItems = new ArrayList<>();
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
        associatedLibrary.registerItem(item);
        item.setStatus(Item.Status.IN_STORE);

        return true;
    }

    @Override
    public abstract boolean borrowItem(Item item);

    /**
     * Randomly loses an item in the User borrowedItems list
     * @return true if task done successfully, false if list is empty
     */
    public boolean loseRandomBorrowedItem() {
        if (borrowedItems.isEmpty()) {
            return false;
        }

        Random random = new Random();
        int randomIdx = random.nextInt(borrowedItems.size());

        Item lostItem = borrowedItems.remove(randomIdx);
        lostItem.setStatus(Item.Status.LOST);

        return true;
    }
}
