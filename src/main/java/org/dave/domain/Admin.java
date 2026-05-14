package org.dave.domain;

import org.dave.interfaces.Reportable;

public class Admin extends User implements Reportable {

    public Admin(String name, Library associatedLibrary) {
        super(name, associatedLibrary);
        this.id = String.format("A%04d", nextId++);
    }

    @Override
    public String report() {
        return "";
    }
}
