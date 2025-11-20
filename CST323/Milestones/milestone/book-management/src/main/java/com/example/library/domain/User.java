package com.example.library.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

// Library User 
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

/**
     * List of books borrowed by this user.
     */

    @OneToMany(mappedBy = "user")
    private List<BorrowRecord> borrowRecords;

    // getters and setters
}