package org.dave.domain;

import lombok.Getter;
import org.dave.util.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Library {
    private List<Item> items;
    private List<User> members;

    public Library() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    /**
     * Check if item is inside List of items
     * @param item the item
     * @return true if item is inside of items list
     */
    public boolean hasItem(Item item) {
        return items.contains(item);
    }

    /**
     * Adds an item in the Library items list
     * @param item the item to be added
     * @return true if item is added,
     * false if item has similar id to one of the items in Library
     */
    public boolean registerItem(Item item) {
        for (Item item1 : items) {
            if (Objects.equals(item1.getId(), item.getId())) {
                return false;
            }
        }

        items.add(item);
        return true;
    }

    /**
     * Adds a user to the member list
     * @param user the user to be added
     * @return true if user added to member list,
     * false if user already in member list
     */
    public boolean addMember(User user) {
        if (members.contains(user)) {
            return false;
        }

        members.add(user);
        user.setAssociatedLibrary(this);
        return true;
    }

    /**
     * Removes an item in the Library items list
     * @param item the item to be removed
     * @return true if item is removed, false if item is not in Library
     */
    public boolean removeItem(Item item) {
        if (!items.contains(item)) {
            return false;
        }

        if (item.getStatus() == Item.Status.BORROWED) {
            throw new IllegalArgumentException("Cannot remove item because it is already borrowed");
        }

        items.remove(item);
        item.setStatus(Item.Status.LOST);

        return true;
    }

    /**
     * Removes a user in the members list
     * @param user the user to be removed
     * @return true if member is removed from members list,
     * false if user is not in members list
     */
    public boolean removeMember(User user) {
        if (!members.contains(user)) {
            return false;
        }

        members.remove(user);
        user.setAssociatedLibrary(null);
        return true;
    }

    /**
     * Allows user to search for items available in their associated library
     * @param keyWord the title of the item the user is searching for
     * @return a list of items containing the keyWord in their title
     */
    public List<Item> searchStream(String keyWord) {
        Set<Item> result = items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyWord.toLowerCase()))
                .collect(Collectors.toSet());

        return result.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyWord))
                .sorted(Comparator.comparing(Item::getTitle))
                .toList();
    }

    // recursion version of searchStream
    public List<Item> searchRecursion(String keyWord) {
        if (items.isEmpty()) {
            return new ArrayList<>();
        }

        Item first = items.get(0);
        Library library = new Library();
        library.items = new ArrayList<>(items.subList(1, items.size()));
        List<Item> result = library.searchRecursion(keyWord);

        if (first.getTitle().toLowerCase().contains(keyWord.toLowerCase())
                && !result.contains(first)) {
            result.add(first);
        }

        return result;
    }

    /**
     * Loads and initializes users from a CSV file.
     */
    public void initUsers() {
        int studentAmount = 0;
        int teacherAmount = 0;
        int adminAmount = 0;
        try (Scanner scanner = new Scanner(new File(Constants.USER_CSV_PATH))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");

                String id = elements[0];
                String name = elements[1];

                int amount = Integer.parseInt(id.substring(1));

                switch (id.charAt(0)) {
                    case 'S' -> {
                        studentAmount = Math.max(studentAmount, amount);
                        new Student(name, this);
                    }
                    case 'T' -> {
                        teacherAmount = Math.max(teacherAmount, amount);
                        new Teacher(name, this);
                    }
                    case 'A' -> {
                        adminAmount = Math.max(adminAmount, amount);
                        new Admin(name, this);
                    }
                    default -> {
                        throw new IllegalArgumentException("Unknown user type");
                    }
                };
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Student.setNextId(studentAmount);
        Teacher.setNextId(teacherAmount);
        Admin.setNextId(adminAmount);
    }

    /**
     * Loads and initializes books, dvds, magazines from a CSV file.
     */
    public void initItems() {
        initBooks();
        initDVD();
        initMagazine();
    }

    private void initBooks() {
        int bookAmount = 0;
        try (Scanner scanner = new Scanner(new File(Constants.BOOK_CSV_PATH))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");

                String id = elements[0];
                String title = elements[1];
                String isbn = elements[2];
                String author = elements[3];
                String genre = elements[4];

                int amount = Integer.parseInt(id.substring(1));

                new Book(title, isbn, author, genre, this);
                bookAmount = Math.max(bookAmount, amount);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Book.setNextId(bookAmount);
    }

    private void initDVD() {
        int dvdAmount = 0;
        try (Scanner scanner = new Scanner(new File(Constants.DVD_CSV_PATH))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");

                String id = elements[0];
                String title = elements[1];
                String director = elements[2];
                int duration = Integer.parseInt(elements[3]);

                int amount = Integer.parseInt(id.substring(1));

                new DVD(title, director,duration, this);
                dvdAmount = Math.max(dvdAmount, amount);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        DVD.setNextId(dvdAmount);
    }

    private void initMagazine() {
        int magazineAmount = 0;
        try (Scanner scanner = new Scanner(new File(Constants.MAGAZINE_CSV_PATH))) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] elements = line.split(",");

                String id = elements[0];
                String title = elements[1];
                int issueNumber = Integer.parseInt(elements[2]);
                String publisher = elements[3];

                int amount = Integer.parseInt(id.substring(1));

                new Magazine(title, issueNumber, publisher, this);
                magazineAmount = Math.max(magazineAmount, amount);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Magazine.setNextId(magazineAmount);
    }
}
