package org.dave.domain;

import java.util.List;

public class Teacher extends User {
    private List<Item> borrowedItems;

    public Teacher(String name) {
        super(name);
        this.borrowedItems = borrowedItems;
    }
}
