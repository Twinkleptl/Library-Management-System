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

    
}