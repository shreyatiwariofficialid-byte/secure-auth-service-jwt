package com.learning.self.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.self.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    
}
