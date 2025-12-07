package com.example.library.web;

import com.example.library.domain.User;
import com.example.library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;

    public AuthController(UserRepository userRepo, BCryptPasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        log.info("[ENTER] loginPage()");
        log.info("[EXIT] loginPage()");
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        log.info("[ENTER] registerPage()");
        model.addAttribute("user", new User());
        log.info("[EXIT] registerPage()");
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        log.info("[ENTER] registerUser() ... username={}", user.getUsername());
        user.setRole("USER");
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        log.info("[EXIT] registerUser() ... User saved");
        return "redirect:/login?registered=true";
    }
}
