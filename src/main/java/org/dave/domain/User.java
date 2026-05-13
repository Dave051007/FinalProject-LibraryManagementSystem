package org.dave.domain;

public abstract class User {
    protected String id;
    protected String name;
    protected Library associatedLibrary;

    private static int nextId = 1;

    public User(String name, Library associatedLibrary) {
        this.id = String.format("%05d", nextId++);
        this.name = name;
        this.associatedLibrary = associatedLibrary;
    }

}
