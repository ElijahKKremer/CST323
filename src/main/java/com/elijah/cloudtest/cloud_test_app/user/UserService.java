package com.elijah.cloudtest.cloud_test_app.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    // Explicit constructor for dependency injection
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> all() {
        log.debug("Fetching all users");
        return repo.findAll();
    }

    @Transactional
    public User create(User user) {
        if (repo.existsByEmail(user.getEmail())) {
            log.warn("Duplicate email attempt: {}", user.getEmail());
            throw new IllegalArgumentException("Email already in use");
        }
        User saved = repo.save(user);
        log.info("Created user id={}", saved.getId());
        return saved;
    }

    public User get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional
    public User update(Long id, User updated) {
        User existing = get(id);
        existing.setFullName(updated.getFullName());
        existing.setEmail(updated.getEmail());
        existing.setRole(updated.getRole());
        log.info("Updating user id={}", id);
        return repo.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting user id={}", id);
        repo.deleteById(id);
    }
}