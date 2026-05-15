import org.dave.domain.Book;
import org.dave.domain.Item;
import org.dave.domain.Library;
import org.dave.domain.Teacher;
import org.dave.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TeacherTest {
    @Test
    @DisplayName("Teacher borrows valid item -> returns true")
    void testTeacherBorrow1() {
        Library library = new Library();
        Teacher teacher = new Teacher("John", library);

        Item book = new Book("Java", "2222222222222", "Author", "Programming", library);

        boolean expected = true;
        boolean actual = teacher.borrowItem(book);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Teacher not registered -> throws IllegalStateException")
    void testTeacherBorrow2() {
        Library library = new Library();
        Teacher teacher = new Teacher("John", library);

        library.removeMember(teacher);

        Item book = new Book("Java", "2222222222222", "Author", "Programming", library);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            teacher.borrowItem(book);
        });
    }

    @Test
    @DisplayName("Item not in library -> throws exception")
    void testTeacherBorrow3() {
        Library library = new Library();
        Teacher teacher = new Teacher("John", library);

        Item book = new Book("Java", "2222222222222", "Author", "Programming", new Library());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            teacher.borrowItem(book);
        });
    }

    @Test
    @DisplayName("Borrow limit exceeded -> throws exception")
    void testTeacherBorrow4() {
        Library library = new Library();
        Teacher teacher = new Teacher("John", library);

        int limit = Constants.MAX_BORROWABLE_ITEMS_TEACHERS;

        for (int i = 0; i < limit; i++) {
            Item book = new Book(
                    "Book" + i,
                    String.format("9780000000%03d", i),
                    "Author",
                    "Programming",
                    library
            );

            teacher.borrowItem(book);
        }

        Item extra = new Book(
                "Extra",
                "9780000000999",
                "Author",
                "Programming",
                library
        );

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            teacher.borrowItem(extra);
        });
    }

    @Test
    @DisplayName("Borrow same item twice -> returns false")
    void testTeacherBorrow5() {
        Library library = new Library();
        Teacher teacher = new Teacher("John", library);

        Item book = new Book("Java", "2222222222222", "Author", "Programming", library);

        teacher.borrowItem(book);

        boolean expected = false;
        boolean actual = teacher.borrowItem(book);

        Assertions.assertEquals(expected, actual);
    }
}
