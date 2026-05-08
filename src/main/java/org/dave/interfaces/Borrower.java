package org.dave.interfaces;

import org.dave.domain.Item;

public interface Borrower {
    boolean borrowItem(Item item);
    boolean returnItem(Item item);
}
