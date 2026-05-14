import org.dave.domain.Book;
import org.dave.domain.Item;
import org.dave.domain.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    @Test
    @DisplayName("Item exists in library -> true")
    void testHasItem1() {
        Library library = new Library();

        Item book = new Book("Java", "9781234567890123", "Dave", "Programming", library);

        Item input = book;
        boolean expected = true;
        boolean actual = library.hasItem(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Item not in library -> false")
    void testHasItem2() {
        Library library = new Library();

        Item book = new Book("Java", "9781234567890123", "Dave", "Programming", library);
        Item anotherBook = new Book("Python", "9781234567890123", "John", "Programming", library);

        library.removeItem(anotherBook);

        Item input = anotherBook;
        boolean expected = false;
        boolean actual = library.hasItem(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Null item -> false")
    void testHasItem3() {
        Library library = new Library();

        Item input = null;
        boolean expected = false;
        boolean actual = library.hasItem(input);

        Assertions.assertEquals(expected, actual);
    }
}
