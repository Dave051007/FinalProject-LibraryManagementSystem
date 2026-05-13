package org.dave.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
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
