package org.dave.interfaces;

import org.dave.domain.Item;
import org.dave.domain.Library;

public interface Borrower {
    boolean borrowItem(Item item);
    boolean returnItem(Item item);
}
