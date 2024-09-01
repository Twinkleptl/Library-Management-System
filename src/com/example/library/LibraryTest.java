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

    
}