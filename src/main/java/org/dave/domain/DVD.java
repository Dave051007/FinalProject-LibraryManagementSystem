package org.dave.domain;

public class DVD extends Item {
    private String director;
    private int duration;

    public DVD(String title, Status status, String director, int duration) {
        super(title, status);
        this.director = director;
        this.duration = duration;
    }
}
