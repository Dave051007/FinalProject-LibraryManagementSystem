package org.dave.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DVD extends Item {
    private String director;
    private int duration;

    @Setter private static int nextId = 1;


    public DVD(String title, String director, int duration, Library library) {
        super(title, library);
        this.id = String.format("D%05d", nextId++);
        this.director = director;
        this.duration = duration;
    }
}
