package com.learning.self.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.self.entity.Users;
public interface UsersRepository extends JpaRepository<Users,Long> {
    
}
