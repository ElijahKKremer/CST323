package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.domain.Book;
import com.example.library.repository.BookRepository;
/**
 * Handles business logic for books.
 */

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
 /**
     * Returns all books.
     */

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
/**
     * Saves a new book.
     */

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}