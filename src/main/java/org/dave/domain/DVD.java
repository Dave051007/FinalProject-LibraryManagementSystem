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

    public DVD(String title, Status status, String director, int duration) {
        super(title, status);
        this.director = director;
        this.duration = duration;
    }
}
