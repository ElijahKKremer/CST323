package com.example.library.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.library.domain.Book;
import com.example.library.service.BookService;
/**
 * Handles HTTP requests for books.
 */

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
/**
     * Displays list of books.
     */

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }
/**
     * Adds a new book.
     */

    @PostMapping
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }
}