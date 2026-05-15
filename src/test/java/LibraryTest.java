import org.dave.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        Item item1 = new Book("Java", "1111111111111", "Dave", "Programming", library);

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

    @Test
    @DisplayName("Existing member is removed -> true")
    void testRemoveMember1() {
        Library library = new Library();
        User user = new Student("Dave", library);

        boolean expected = true;
        boolean actual = library.removeMember(user);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Member is removed from members list")
    void testRemoveMember2() {
        Library library = new Library();
        User user = new Student("Dave", library);

        library.removeMember(user);

        boolean expected = false;
        boolean actual = library.getMembers().contains(user);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("User associatedLibrary is set to null after removal")
    void testRemoveMember3() {
        Library library = new Library();
        User user = new Student("Dave", library);

        library.removeMember(user);

        Library expected = null;
        Library actual = user.getAssociatedLibrary();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Non-existing member -> false")
    void testRemoveMember4() {
        Library library = new Library();
        User user = new Student("Dave", library);

        library.removeMember(user); // already removed from constructor logic

        boolean expected = false;
        boolean actual = library.removeMember(user);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Keyword matches items -> returns results")
    void testSearchStream1() {
        Library library = new Library();

        Item item1 = new Book("Java Basics", "1234567891011", "Dave", "Programming", library);
        Item item2 = new Book("Advanced Java", "1111111111111", "John", "Programming", library);
        Item item3 = new Book("Python Guide", "2222222222222", "Mike", "Programming", library);

        List<Item> expected = List.of(item2, item1);
        List<Item> actual = library.searchStream("Java");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Empty keyword -> empty list")
    void testSearchStream2() {
        Library library = new Library();
        Item item1 = new Book("Java Basics", "1111111111111", "Dave", "Programming", library);

        List<Item> expected = List.of();
        List<Item> actual = library.searchStream("");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Null keyword -> empty list")
    void testSearchStream3() {
        Library library = new Library();
        Item item1 = new Book("Java Basics", "1111111111111", "Dave", "Programming", library);

        List<Item> expected = List.of();
        List<Item> actual = library.searchStream(null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("BORROWED items are not allowed from search")
    void testSearchStream4() {
        Library library = new Library();

        Item item1 = new Book("Java Basics", "5555555555555", "Dave", "Programming", library);
        item1.setStatus(Item.Status.BORROWED);

        List<Item> expected = List.of();
        List<Item> actual = library.searchStream("Java");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Duplicate titles -> only one returned")
    void testSearchStream5() {
        Library library = new Library();

        Item item1 = new Book("Java Basics", "3333333333333", "Dave", "Programming", library);
        Item item2 = new Book("Java Basics", "2222222222222", "John", "Programming", library);

        List<Item> actual = library.searchStream("Java");
        List<Item> expected = List.of(item1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Search is case-insensitive")
    void testSearchStream6() {
        Library library = new Library();
        Item item1 = new Book("Java Basics", "2839283726151", "Dave", "Programming", library);

        List<Item> expected = List.of(item1);
        List<Item> actual = library.searchStream("java");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Recursive search returns matching items")
    void testSearchRecursion1() {
        Library library = new Library();

        Item item1 = new Book("Java Basics", "1111111111111", "Dave", "Programming", library);
        Item item2 = new Book("Advanced Java", "2222222222222", "John", "Programming", library);
        Item item3 = new Book("Python Guide", "3333333333333", "Mike", "Programming", library);

        String input = "Java";

        List<Item> expected = List.of(item2, item1);
        List<Item> actual = library.searchRecursion(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Recursive search with no matches -> empty list")
    void testSearchRecursion2() {
        Library library = new Library();
        Item item1 = new Book("Java Basics", "1111111111111", "Dave", "Programming", library);

        String input = "C++";

        List<Item> expected = List.of();
        List<Item> actual = library.searchRecursion(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Null keyword -> empty list")
    void testSearchRecursion3() {
        Library library = new Library();

        Item item1 = new Book("Java Basics", "2222222222222", "Dave", "Programming", library);

        List<Item> expected = List.of();
        List<Item> actual = library.searchRecursion(null);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Duplicate titles -> only one result")
    void testSearchRecursion4() {
        Library library = new Library();

        Item item1 = new Book("Java Basics", "3333333333333", "Dave", "Programming", library);
        Item item2 = new Book("Java Basics", "3333333333333", "John", "Programming", library);

        String input = "Java";

        List<Item> expected = List.of(item1);
        List<Item> actual = library.searchRecursion(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Recursive search is case-insensitive")
    void testSearchRecursion5() {
        Library library = new Library();

        Item item1 = new Book("Java Basics", "5555555555555", "Dave", "Programming", library);

        String input = "java";

        List<Item> expected = List.of(item1);
        List<Item> actual = library.searchRecursion(input);

        Assertions.assertEquals(expected, actual);
    }
}
