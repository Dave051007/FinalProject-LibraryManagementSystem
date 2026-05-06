package org.dave.domain;

public class Admin extends User implements Reportable {

    public Admin(String name) {
        super(name);
    }

    @Override
    public String report() {
        return "";
    }
}
