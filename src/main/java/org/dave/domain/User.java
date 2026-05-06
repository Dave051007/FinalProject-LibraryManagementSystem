package org.dave.domain;

public abstract class User {
    protected String id;
    protected String name;

    private static int nextId = 1;

    public User(String id, String name) {
        this.id = String.format("%05d", nextId++);
        this.name = name;
    }
}
