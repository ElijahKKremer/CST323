package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library.domain.User;
/**
 * Provides CRUD operations for User entities.
 */

public interface UserRepository extends JpaRepository<User, Long> {}