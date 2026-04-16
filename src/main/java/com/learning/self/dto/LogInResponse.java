package com.learning.self.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogInResponse {
    private String token;
    private String refreshToken;
}
