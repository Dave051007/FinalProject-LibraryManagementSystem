package org.dave.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Getter
@Setter
public abstract class Item {
    protected String id;
    protected String title;
    protected Status status;
    protected Library library;

    private static int nextId = 1;

    public Item(String title, Library library) {
        this.title = title;
        this.status = Status.IN_STORE;
        this.library = library;

        library.registerItem(this);
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status;
    }

    public enum Status {
        BORROWED, IN_STORE, LOST
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
