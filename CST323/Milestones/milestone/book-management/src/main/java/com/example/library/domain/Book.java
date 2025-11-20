package com.example.library.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
 /**
     * List of borrow records for this book.
     */

    @OneToMany(mappedBy = "book")
    private List<BorrowRecord> borrowRecords;

    // getters and setters
}