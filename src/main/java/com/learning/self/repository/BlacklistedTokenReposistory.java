package com.learning.self.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.self.entity.BlacklistedToken;

public interface BlacklistedTokenReposistory  extends  JpaRepository<BlacklistedToken,Long>{
    boolean existsByToken(String Long);

    // void save(BlacklistedToken blacklistedToken);
}
