package com.example.library;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of books and tracks borrowed books.
 */
public class Library {
    private List<Book> books = new ArrayList<>();
    private List<String> borrowedBooks = new ArrayList<>();
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
     * Borrows a book from the library.
     * @param isbn The ISBN of the book.
     * @throws IllegalArgumentException if book is not available or already borrowed.
     */
    public void borrowBook(String isbn) {
        if (!isBookAvailable(isbn)) {
            if (borrowedBooks.contains(isbn)) {
                throw new IllegalArgumentException("Already Borrowed");
            } else {
                throw new IllegalArgumentException("Book is not available in library: " + isbn);
            }
        }
        borrowedBooks.add(isbn);
    }

    /**
     * Checks if a book is available.
     * @param isbn The ISBN of the book.
     * @return true if available, false otherwise.
     */
    public boolean isBookAvailable(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !borrowedBooks.contains(isbn)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a borrowed book to the library.
     * @param isbn The ISBN of the book.
     * @throws IllegalArgumentException if book was not borrowed.
     */
    public void returnBook(String isbn) {
        if (!borrowedBooks.contains(isbn)) {
            throw new IllegalArgumentException("Book was not borrowed: " + isbn);
        }
        borrowedBooks.remove(isbn);
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
