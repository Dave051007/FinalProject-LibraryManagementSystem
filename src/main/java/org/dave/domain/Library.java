package org.dave.domain;

import lombok.Getter;
import java.util.List;

@Getter
public class Library {
    private List<Item> items;
    private List<Item> members;
}
