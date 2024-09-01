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
    
  
    
}