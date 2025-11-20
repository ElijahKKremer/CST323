package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.domain.Book;
/**
 * Provides CRUD operations for Book entities.
 */

public interface BookRepository extends JpaRepository<Book, Long> {}