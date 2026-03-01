package com.learning.self.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "password")
@Data
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passwordId;
    private String password;
    private String lastPassword;

    private boolean account_locked=true;

    @OneToOne
    @JoinColumn(name="userId")
    private Users user;

}
