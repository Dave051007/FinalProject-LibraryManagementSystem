package org.dave.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class User {
    protected String id;
    protected String name;
    protected Library associatedLibrary;

    public User(String name, Library associatedLibrary) {
        this.name = name;
        this.associatedLibrary = associatedLibrary;

        associatedLibrary.addMember(this);
    }

    /**
     * Registers the user to a Library to be able to borrow its items
     * @param library the library to be registered to
     * @return true if registered successfully,
     * false if user is already registered to the library
     */
    public boolean registerForMembership(Library library) {
        if (associatedLibrary != null) {
            return false;
        }

        library.addMember(this);
        return true;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", associatedLibrary=" + associatedLibrary;
    }
}
