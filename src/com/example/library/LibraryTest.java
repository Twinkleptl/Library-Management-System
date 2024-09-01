package com.example.library;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.*;
/**
 * Unit tests for the Library class.
 */
public class LibraryTest {
    Library library = new Library();

    @Test
    public void testAddBook() {
        // Create a book and add it to the library
        Book book = new Book("101", "The Great Gatsby", "F. Scott Fitzgerald", 2000);
        library.addBook(book);
        
        // Verify that the book is now available in the library
        assertTrue(library.getAvailableBooks().contains(book));
    }
    
    @Test
    public void testAddDuplicateIsbn() {
        // Create two books with the same ISBN
        Book book1 = new Book("102", "1984", "George Orwell", 1949);
        Book book2 = new Book("102", "Animal Farm", "George Orwell", 1945);
        library.addBook(book1);

        // Attempt to add the second book with a duplicate ISBN
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(book2);
        });
        
        // Verify that the exception message is correct
        assertEquals("A book with this ISBN already exists: 102", exception.getMessage());
    }
    
    @Test
    public void testAddWithNullIsbn() {
        // Create a book with a null ISBN
        Book book = new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", 2000);

        // Attempt to add the book with a null ISBN
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(book);
        });
        
        // Verify that the exception message is correct
        assertEquals("ISBN cannot be null or empty", exception.getMessage());
    }
    
    @Test
    public void testAddWithInvalidPublicationYear() {
        // Create a book with an invalid publication year
        Book book = new Book("104", "The Great Gatsby", "F. Scott Fitzgerald", 200);

        // Attempt to add the book with an invalid year
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.addBook(book);
        });
        
        // Verify that the exception message is correct
        assertEquals("Publication Year is invalid", exception.getMessage());
    }
    @Test
    public void testBorrowBook() {
        // Add a book and ensure it is available
        Book book = new Book("103", "The Adventures Of Tom Sawyer", "Mark Twain", 2022);
        library.addBook(book);
        assertTrue(library.isBookAvailable("103"));

        // Borrow the book and verify it is no longer available
        library.borrowBook("103");
        assertFalse(library.isBookAvailable("103"));
    }
    
    @Test
    public void testBookNotAvailable() {
        // Attempt to borrow a book that does not exist in the library
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("106");
        });
        
        // Verify that the exception message is correct
        assertEquals("Book is not available in library: 106", exception.getMessage());
    }
    
    @Test
    public void testBookAlreadyBorrowed() {
        // Add a book, borrow it, and attempt to borrow it again
        Book book = new Book("105", "The Catcher in the Rye", "J.D. Salinger", 1951);
        library.addBook(book);
        library.borrowBook("105");

        // Attempt to borrow the book again
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.borrowBook("105");
        });
        
        // Verify that the exception message is correct
        assertEquals("Already Borrowed", exception.getMessage());
    }

    @Test
    public void testReturnBook() {
        // Add, borrow, and then return a book
        Book book = new Book("106", "1984", "George Orwell", 1949);
        library.addBook(book);
        library.borrowBook("106");
        library.returnBook("106");

        // Verify that the book is available after returning
        assertTrue(library.getAvailableBooks().stream().anyMatch(b -> b.getIsbn().equals("106")));
    }
    
    @Test
    public void testReturnBookNotBorrowed() {
        // Add a book and attempt to return it without borrowing
        Book book = new Book("107", "To Kill a Mockingbird", "Harper Lee", 1960);
        library.addBook(book);

        // Attempt to return a book that was not borrowed
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.returnBook("107");
        });
        
        // Verify that the exception message is correct
        assertEquals("Book was not borrowed: 107", exception.getMessage());
    }
    
    @Test
    public void testReturnBookNotAvailable() {
        // Attempt to return a book that was never added
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.returnBook("108");
        });
        
        // Verify that the exception message is correct
        assertEquals("Book was not borrowed: 108", exception.getMessage());
    }
    
    @Test
    public void testBookAlreadyReturned() {
        // Add, borrow, and return a book, then attempt to return it again
        Book book = new Book("109", "The Lord of the Rings", "J.R.R. Tolkien", 1954);
        library.addBook(book);
        library.borrowBook("109");
        library.returnBook("109");

        // Attempt to return the book again
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            library.returnBook("109");
        });
        
        // Verify that the exception message is correct
        assertEquals("Book was not borrowed: 109", exception.getMessage());
    }

}