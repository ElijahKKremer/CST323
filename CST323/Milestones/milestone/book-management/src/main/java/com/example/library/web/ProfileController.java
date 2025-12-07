package com.example.library.web;

import com.example.library.domain.BorrowRecord;
import com.example.library.domain.User;
import com.example.library.repository.BorrowRecordRepository;
import com.example.library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private final UserRepository userRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public ProfileController(UserRepository userRepository,
                             BorrowRecordRepository borrowRecordRepository) {
        this.userRepository = userRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    @GetMapping
    public String viewProfile(Model model, Authentication authentication) {
        log.info("[ENTER] viewProfile()");

        if (authentication == null) {
            log.info("[EXIT] viewProfile() - Not authenticated");
            return "redirect:/login";
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            log.info("[EXIT] viewProfile() - User not found");
            return "redirect:/login";
        }

        List<BorrowRecord> activeBorrows =
                borrowRecordRepository.findByUserAndReturnDateIsNull(user);

        model.addAttribute("user", user);
        model.addAttribute("borrows", activeBorrows);

        log.info("[EXIT] viewProfile()");
        return "profile";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Long id, Authentication authentication) {
        log.info("[ENTER] returnBook() - id={}", id);

        if (authentication == null) {
            log.info("[EXIT] returnBook() - Not authenticated");
            return "redirect:/login";
        }

        BorrowRecord record = borrowRecordRepository.findById(id).orElse(null);

        if (record != null && record.getReturnDate() == null) {
            String username = authentication.getName();

            if (record.getUser() != null &&
                    username.equals(record.getUser().getUsername())) {

                record.setReturnDate(LocalDate.now());
                borrowRecordRepository.save(record);
            }
        }

        log.info("[EXIT] returnBook()");
        return "redirect:/profile";
    }
}
