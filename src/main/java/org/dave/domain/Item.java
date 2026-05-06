package org.dave.domain;

public abstract class Item {
    protected String id;
    protected String name;
    protected Status status;

    private static int nextId = 1;
}
