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
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@ToString(exclude = {"profile", "password", "role"})
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private  UserProfile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @NotBlank(message = "Password is required")
    private Password password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Role role;

    @Column
    private String location;

    private String roleName="USER";
    private int failedAttempt=0;

    private boolean enabled=true;
    private boolean account_locked=true;


    private Instant accountCreatedAt=Instant.now();
}
