package org.dave.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Magazine extends Item {
    private int issueNumber;
    private int publisher;

    public Magazine(String title, Status status, int issueNumber, int publisher) {
        super(title, status);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }
}
