package org.dave.domain;

public abstract class Item {
    protected String id;
    protected String name;
    protected Status status;

    private static int nextId = 1;

    public Item(String name, Status status) {
        this.id = String.format("%05d", nextId++);
        this.name = name;
        this.status = status;
    }

    public enum Status {
        BORROWED, IN_STORE, LOST
    }
}
