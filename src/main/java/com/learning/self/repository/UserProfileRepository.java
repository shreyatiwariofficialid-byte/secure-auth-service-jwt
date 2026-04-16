package com.learning.self.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.self.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long>{
    Optional<UserProfile> findByEmailOrUsernameOrContact(String email, String username, String contact);
    boolean existsByEmail(String email);
    boolean existsByContact(String contact);
    boolean existsByUsername(String username);
    
}
