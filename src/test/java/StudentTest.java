import org.dave.domain.*;
import org.dave.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentTest {
    @Test
    @DisplayName("Borrow valid book -> returns true")
    void testBorrowItem1() {
        Library library = new Library();
        Student student = new Student("John", library);
        Item book = new Book("Java", "2222222222222", "Dave", "Programming", library);

        boolean expected = true;
        boolean actual = student.borrowItem(book);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("User not registered -> throws IllegalStateException")
    void testBorrowItem2() {
        Library library = new Library();
        Student student = new Student("John", library);
        library.removeMember(student);

        Item book = new Book("Java", "2222222222222", "Dave", "Programming", library);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            student.borrowItem(book);
        });
    }

    @Test
    @DisplayName("Item not in library -> throws IllegalArgumentException")
    void testBorrowItem3() {
        Library library = new Library();
        Student student = new Student("John", library);

        Item book = new Book("Java", "2222222222222", "Dave", "Programming", new Library());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {student.borrowItem(book);});
    }

    @Test
    @DisplayName("Item is not Book -> throws exception")
    void testBorrowItem4() {
        Library library = new Library();
        Student student = new Student("John", library);

        Item dvd = new DVD("Movie", "Director", 120, library);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {student.borrowItem(dvd);});
    }

    @Test
    @DisplayName("Borrow limit exceeded -> throws exception")
    void testBorrowItem5() {
        Library library = new Library();
        Student student = new Student("John", library);

        Item item1 = new Book("book1", "2222222222221", "Author", "Programming", library);
        Item item2 = new Book("book2", "2222222222222", "Author", "Programming", library);
        Item item3 = new Book("book3", "2222222222223", "Author", "Programming", library);
        Item item4 = new Book("book4", "2222222222224", "Author", "Programming", library);
        Item item5 = new Book("book5", "2222222222225", "Author", "Programming", library);

        student.borrowItem(item1);
        student.borrowItem(item2);
        student.borrowItem(item3);
        student.borrowItem(item4);
        student.borrowItem(item5);

        Item extra = new Book("Extra", "2222222222226", "Author", "Programming", library);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            student.borrowItem(extra);
        });
    }

    @Test
    @DisplayName("Borrow same item twice -> returns false")
    void testBorrowItem6() {
        Library library = new Library();
        Student student = new Student("John", library);

        Item book = new Book("Java", "2222222222222", "Dave", "Programming", library);

        student.borrowItem(book);

        boolean expected = false;
        boolean actual = student.borrowItem(book);

        Assertions.assertEquals(expected, actual);
    }
}
