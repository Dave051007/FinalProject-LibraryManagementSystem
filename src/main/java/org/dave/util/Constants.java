package org.dave.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Constants {
    public static final int MAX_BORROWABLE_BOOKS_STUDENTS = 5;
    public static final int MAX_BORROWABLE_ITEMS_TEACHERS = 10;
    public static final String USER_CSV_PATH = "src/main/resources/user.csv";
}
