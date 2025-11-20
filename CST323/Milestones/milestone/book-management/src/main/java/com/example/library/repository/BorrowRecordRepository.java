package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.domain.BorrowRecord;
/**
 * Provides CRUD operations for BorrowRecord entities.
 */

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {}