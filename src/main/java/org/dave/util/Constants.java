package org.dave.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Constants {
    public static final int MAX_BORROWABLE_BOOKS_STUDENTS = 5;
    public static final int MAX_BORROWABLE_ITEMS_TEACHERS = 10;
    public static final String USER_CSV_PATH = "src/main/resources/user.csv";
    public static final String BOOK_CSV_PATH = "src/main/resources/book.csv";
    public static final String DVD_CSV_PATH = "src/main/resources/dvd.csv";
    public static final String MAGAZINE_CSV_PATH = "src/main/resources/magazine.csv";
}
