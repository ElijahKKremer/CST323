package com.example.library.web;

import com.example.library.domain.Book;
import com.example.library.domain.BorrowRecord;
import com.example.library.domain.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRecordRepository;
import com.example.library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/books")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;

    public BookController(BookRepository bookRepository,
                          BorrowRecordRepository borrowRecordRepository,
                          UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.borrowRecordRepository = borrowRecordRepository;
        this.userRepository = userRepository;
    }

    /**
     * Public book list. Shows add/delete/borrow buttons depending on your role
     */
    @GetMapping
    public String listBooks(Model model, Authentication authentication) {
        log.info("[ENTER] listBooks()");
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("auth", authentication);
        log.info("[EXIT] listBooks()");
        return "books";
    }

    /**
     * Admin: add a new book
     */
    @PostMapping("/add")
    public String addBook(@RequestParam String title, @RequestParam String author) {
        log.info("[ENTER] addBook()");
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        bookRepository.save(book);
        log.info("[EXIT] addBook()");
        return "redirect:/books";
    }

    /**
     * Admin: delete a book
     */
    public String deleteBook(@PathVariable Long id) {
        log.info("[ENTER] deleteBook() - id={}", id);
        bookRepository.deleteById(id);
        log.info("[EXIT] deleteBook()");
        return "redirect:/books";
    }

    /**
     * Logged-in users: borrow
     */
    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Authentication authentication) {
        log.info("[ENTER] borrowBook() - id={}", id);

        if (authentication == null) {
            log.info("[EXIT] borrowBook() - Not authenticated");
            return "redirect:/login";
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        Book book = bookRepository.findById(id).orElse(null);

        if (user != null && book != null) {
            BorrowRecord record = new BorrowRecord();
            record.setUser(user);
            record.setBook(book);
            record.setBorrowDate(LocalDate.now());
            borrowRecordRepository.save(record);
        }

        log.info("[EXIT] borrowBook()");
        return "redirect:/books";
    }
}
