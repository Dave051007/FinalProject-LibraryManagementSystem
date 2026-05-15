import org.dave.domain.Book;
import org.dave.domain.Item;
import org.dave.domain.Library;
import org.dave.domain.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BorrowingUserTest {
    @Test
    @DisplayName("Return borrowed item -> returns true and sets status IN_STORE")
    void testReturnItem1() {
        Library library = new Library();
        Student student = new Student("John", library);

        Item book = new Book("Java", "2222222222222", "Author", "Programming", library);

        student.borrowItem(book);

        boolean expected = true;
        boolean actual = student.returnItem(book);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Return item not borrowed -> returns false")
    void testReturnItem2() {
        Library library = new Library();
        Student student = new Student("John", library);

        Item book = new Book("Java", "2222222222222", "Author", "Programming", library);

        boolean expected = false;
        boolean actual = student.returnItem(book);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Return removes item from borrowedItems list")
    void testReturnItem3() {
        Library library = new Library();
        Student student = new Student("John", library);

        Item book = new Book("Java", "2222222222222", "Author", "Programming", library);

        student.borrowItem(book);
        student.returnItem(book);

        boolean expected = false;
        boolean actual = student.getBorrowedItems().contains(book);

        Assertions.assertEquals(expected, actual);
    }


}
