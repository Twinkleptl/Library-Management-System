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
        if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }
        if (book.getPublicationYear() < 1000) {
            throw new IllegalArgumentException("Publication Year is invalid");
        }
        for (Book b : books) {
            if (b.getIsbn().equals(book.getIsbn())) {
                throw new IllegalArgumentException("A book with this ISBN already exists: " + book.getIsbn());
            }
        }
        books.add(book);
    }
        
        /**
         * Gets a list of available books.
         * @return List of available books.
         */
        public List<Book> getAvailableBooks() {
            List<Book> availableBooks = new ArrayList<>();
            return availableBooks;
        }

    
}
