package com.learning.self.entity;

import java.time.Instant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private  UserProfile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Password password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Role role;

    @Column(nullable = false)
    private String location;

    private String roleName="USER";
    private int failedAttempt=0;

    private boolean enabled=true;
    private boolean account_locked=true;


    private Instant accountCreatedAt=Instant.now();
}
