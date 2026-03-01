package com.learning.self.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.self.entity.Password;

public interface PasswordRepository extends JpaRepository<Password,Long> {
    Optional<Password> findByUser_UserId(Long userId);
    
} 
