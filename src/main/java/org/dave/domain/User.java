package org.dave.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User {
    protected String id;
    protected String name;
    protected Library associatedLibrary;

    private static int nextId = 1;

    public User(String name) {
        this.id = String.format("%05d", nextId++);
        this.name = name;
        this.associatedLibrary = null;
    }

    /**
     * Registers the user to a Library to be able to borrow its items
     * @param library the library to be registered to
     * @return true if registered successfully,
     * false if user is already registered to the library
     */
    public boolean registerForMembership(Library library) {
        if (library.getMembers().contains(this)) {
            return false;
        }

        library.addMember(this);
        return true;
    }
}
