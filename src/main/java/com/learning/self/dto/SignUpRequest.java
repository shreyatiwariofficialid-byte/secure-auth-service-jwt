package com.learning.self.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String contact;
    private String username;
    private String password;
    private String location;
}
