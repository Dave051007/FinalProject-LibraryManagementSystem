package org.dave.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.dave.interfaces.Borrower;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BorrowingUser extends User implements Borrower {
    protected List<Item> borrowedItems;

    public BorrowingUser(String name) {
        super(name);
        borrowedItems = new ArrayList<>();
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

    @Override
    public abstract boolean borrowItem(Item item);
}
