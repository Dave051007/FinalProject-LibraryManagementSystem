package org.dave.domain;

import org.dave.interfaces.Reportable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Admin extends User implements Reportable {

    public Admin(String name, Library associatedLibrary) {
        super(name, associatedLibrary);
        this.id = String.format("A%04d", nextId++);
    }

    @Override
    public Map<Item.Status, List<String>> report() {
       Map<Item.Status, List<String>> result = new TreeMap<>();

        for (Item item : associatedLibrary.getItems()) {
            result.putIfAbsent(item.getStatus(), new ArrayList<>());
            result.get(item.getStatus()).add(item.getTitle());
        }

       return result;
    }
}
