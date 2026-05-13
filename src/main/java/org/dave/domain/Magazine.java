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
    private int publisher;

    public Magazine(String title, Status status, int issueNumber, int publisher) {
        super(title, status);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }
}
