package org.dave.domain;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private List<Item> borrowedItems;

    public Student(String name) {
        super(name);
        this.borrowedItems = new ArrayList<>();
    }
}
