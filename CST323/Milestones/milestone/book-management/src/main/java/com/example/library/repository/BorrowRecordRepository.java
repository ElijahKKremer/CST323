package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.domain.BorrowRecord;
import com.example.library.domain.User;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    // list of borrowed books for a given user
    List<BorrowRecord> findByUserAndReturnDateIsNull(User user);
}