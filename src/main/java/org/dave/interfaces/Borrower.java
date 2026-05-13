package org.dave.interfaces;

import org.dave.domain.Item;
import org.dave.domain.Library;

public interface Borrower {
    boolean borrowItem(Item item, Library library);
    boolean returnItem(Item item, Library library);
}
