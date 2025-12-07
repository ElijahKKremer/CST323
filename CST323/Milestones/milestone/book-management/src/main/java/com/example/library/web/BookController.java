package com.example.library.web;
//new imports for logging 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

// new logger 
@Controller
@RequestMapping("/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Displays list of books.
     */
    @GetMapping
    public String listBooks(Model model) {
        log.info("ENTER: BookController#listBooks");
        model.addAttribute("books", bookService.findAll());
        log.info("EXIT: BookController#listBooks");
        return "books";
    }

    /**
     * Adds a new book.
     */
    @PostMapping
    public String addBook(@ModelAttribute Book book) {
        log.info("ENTER: BookController#addBook");
        bookService.save(book);
        log.info("EXIT: BookController#addBook");
        return "redirect:/books";
    }
}