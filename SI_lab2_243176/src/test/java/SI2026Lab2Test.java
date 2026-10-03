import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SI2026Lab2Test {

    @Test
    public void searchBookEveryStatementTest() {
        Library library = new Library();

        library.addBook(new Book("Java", "Marko", "Programming"));
        library.addBook(new Book("Python", "Ana", "Programming"));


        List<Book> result1 = library.searchBookByTitle("Java");
        assertNotNull(result1);
        assertEquals(1, result1.size());


        List<Book> result2 = library.searchBookByTitle("C++");
        assertNull(result2);


        assertThrows(IllegalArgumentException.class, () -> {
            library.searchBookByTitle("");
        });
    }

    @Test
    public void borrowBookEveryBranchTest() {
        Library library = new Library();

        library.addBook(new Book("Java", "Marko", "Programming"));


        library.borrowBook("Java", "Marko");


        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("Java", "Marko");
        });


        assertThrows(RuntimeException.class, () -> {
            library.borrowBook("Python", "Ana");
        });


        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "Marko");
        });
    }

    @Test
    public void searchBookMultipleConditionTest() {
        Library library = new Library();

        Book b1 = new Book("Java", "Marko", "Programming"); // T,T
        Book b2 = new Book("Java", "Ana", "Programming");   // T,F
        b2.setBorrowed(true);

        Book b3 = new Book("Python", "Ana", "Programming"); // F,T

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        List<Book> result = library.searchBookByTitle("Java");

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void borrowBookMultipleConditionTest() {
        Library library = new Library();


        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "");
        });


        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("", "Marko");
        });


        assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("Java", "");
        });


        library.addBook(new Book("Java", "Marko", "Programming"));
        assertDoesNotThrow(() -> {
            library.borrowBook("Java", "Marko");
        });
    }


}
