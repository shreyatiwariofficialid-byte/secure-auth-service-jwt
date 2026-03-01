package com.learning.self.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_profile")
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    private String email;
    private String contact;
    private String username;

    private boolean isEnabled=true;
    private boolean accountLocked=true;
    private Instant accountCreatedAt;

    @OneToOne
    @JoinColumn(name="userId")
    private Users user;
}
