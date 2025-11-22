package com.example.library.web;

import com.example.library.domain.BorrowRecord;
import com.example.library.domain.User;
import com.example.library.repository.BorrowRecordRepository;
import com.example.library.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public ProfileController(UserRepository userRepository,
                             BorrowRecordRepository borrowRecordRepository) {
        this.userRepository = userRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    @GetMapping
    public String viewProfile(Model model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            return "redirect:/login";
        }

        // active borrows only (with no returnDate)
        List<BorrowRecord> activeBorrows =
                borrowRecordRepository.findByUserAndReturnDateIsNull(user);

        model.addAttribute("user", user);
        model.addAttribute("borrows", activeBorrows);
        return "profile";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@PathVariable Long id, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }

        BorrowRecord record = borrowRecordRepository.findById(id).orElse(null);
        if (record != null && record.getReturnDate() == null) {
            // verify that this record belongs to the user
            String username = authentication.getName();
            if (record.getUser() != null &&
                    username.equals(record.getUser().getUsername())) {

                record.setReturnDate(LocalDate.now());
                borrowRecordRepository.save(record);
            }
        }

        return "redirect:/profile";
    }
}
