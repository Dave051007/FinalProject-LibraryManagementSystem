package org.dave.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Magazine extends Item {
    private int issueNumber;
    private String publisher;

    @Setter private static int nextId = 1;


    public Magazine(String title, int issueNumber, String publisher, Library library) {
        super(title, library);
        this.id = String.format("M%05d", nextId++);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }
}
