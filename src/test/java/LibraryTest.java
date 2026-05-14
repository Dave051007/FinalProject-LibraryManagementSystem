import org.dave.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LibraryTest {
    @Test
    @DisplayName("Item exists in library -> true")
    void testHasItem1() {
        Library library = new Library();

        Item book = new Book("Java", "0912839203910", "Dave", "Programming", library);

        Item input = book;
        boolean expected = true;
        boolean actual = library.hasItem(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Item not in library -> false")
    void testHasItem2() {
        Library library = new Library();

        Item book = new Book("Java", "9027463728372", "Dave", "Programming", library);
        Item anotherBook = new Book("Python", "9027463728372", "John", "Programming", library);

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

    @Test
    @DisplayName("Item is added to library list")
    void testRegisterItem1() {
        Library library = new Library();

        Item item = new Book("Java", "1234567891011", "Dave", "Programming", library);

        library.registerItem(item);

        boolean expected = true;
        boolean actual = library.getItems().contains(item);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Duplicate ID -> false")
    void testRegisterItem2() {
        Library library = new Library();

        Item item1 = new Book("Java", "1234567891011", "Dave", "Programming", library);
        Item item2 = new Book("Python", "1234567891011", "John", "Programming", library);

        boolean expected = false;
        boolean actual = library.registerItem(item2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Null item -> throws NullPointerException")
    void testRegisterItem3() {
        Library library = new Library();

        Item input = null;
        Item item1 = new Book("Java", "9781234567890123", "Dave", "Programming", library);

        Assertions.assertThrows(NullPointerException.class, () -> {library.registerItem(input);});
    }

    @Test
    @DisplayName("User is added to members list")
    void testAddMember1() {
        Library library = new Library();
        User user = new Student("Dave", library);

        library.addMember(user);

        boolean expected = true;
        boolean actual = library.getMembers().contains(user);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Duplicate user -> false")
    void testAddMember2() {
        Library library = new Library();
        User user = new Student("Dave", library);

        library.addMember(user);

        boolean expected = false;
        boolean actual = library.addMember(user);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("User associatedLibrary is set after adding")
    void testAddMember3() {
        Library library = new Library();
        User user = new Student("Dave", library);

        library.addMember(user);

        Library expected = library;
        Library actual = user.getAssociatedLibrary();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Null user -> throws NullPointerException")
    void testAddMember4() {
        Library library = new Library();

        User input = null;

        Assertions.assertThrows(NullPointerException.class, () -> {library.addMember(input);});
    }

    @Test
    @DisplayName("Existing item is removed -> true")
    void testRemoveItem1() {
        Library library = new Library();

        Item item = new Book("Java", "1234567891011", "Dave", "Programming", library);
        library.registerItem(item);

        boolean expected = true;
        boolean actual = library.removeItem(item);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Item is removed from library list")
    void testRemoveItem2() {
        Library library = new Library();

        Item item = new Book("Java", "1234567891011", "Dave", "Programming", library);
        library.registerItem(item);

        library.removeItem(item);

        boolean expected = false;
        boolean actual = library.getItems().contains(item);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Item not in library -> false")
    void testRemoveItem3() {
        Library library = new Library();
        Item item = new Book("Java", "1234567891011", "Dave", "Programming", library);

        library.removeItem(item);

        boolean expected = false;
        boolean actual = library.removeItem(item);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Borrowed item cannot be removed -> exception")
    void testRemoveItem4() {
        Library library = new Library();
        Item item = new Book("Java", "1234567891011", "Dave", "Programming", library);

        library.registerItem(item);
        item.setStatus(Item.Status.BORROWED);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {library.removeItem(item);});
    }
}
