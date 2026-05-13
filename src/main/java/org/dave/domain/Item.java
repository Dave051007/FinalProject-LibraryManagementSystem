package org.dave.domain;

import lombok.Getter;

@Getter
public abstract class Item {
    protected String id;
    protected String title;
    protected Status status;

    private static int nextId = 1;

    public Item(String title, Status status) {
        this.id = String.format("%05d", nextId++);
        this.title = title;
        this.status = status;
    }

    public enum Status {
        BORROWED, IN_STORE, LOST
    }
}
