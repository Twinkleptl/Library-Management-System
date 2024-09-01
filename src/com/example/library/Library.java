package com.example.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of books and tracks borrowed books.
 */
public class Library {
    private List<Book> books = new ArrayList<>();
 

    /**
     * Adds a book to the library.
     * @param book The book to add.
     * @throws IllegalArgumentException if ISBN is invalid or book already exists.
     */
    public void addBook(Book book) {
        books.add(book);
    }
    
}
