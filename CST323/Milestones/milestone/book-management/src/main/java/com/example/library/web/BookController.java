package com.example.library.web;

import com.example.library.domain.Book;
import com.example.library.domain.BorrowRecord;
import com.example.library.domain.User;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRecordRepository;
import com.example.library.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/books")
public class BookController {

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
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("auth", authentication); // to show username if needed
        return "books";
    }

    /**
     * Admin: add a new book
     */
    @PostMapping("/add")
    public String addBook(@RequestParam String title,
                          @RequestParam String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        bookRepository.save(book);
        return "redirect:/books";
    }

    /**
     * Admin: delete a book
     */
    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

    /**
     * Logged-in users: borrow
     */
    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Authentication authentication) {
        if (authentication == null) {
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

        return "redirect:/books";
    }
}
