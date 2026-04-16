package com.learning.self.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.self.dto.LogInRequest;
import com.learning.self.dto.LogInResponse;
import com.learning.self.dto.RefreshTokenRequest;
import com.learning.self.dto.SignUpRequest;
import com.learning.self.repository.RefreshTokenRepository;
import com.learning.self.service.AuthService;
import com.learning.self.service.RefreshTokenService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/self")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LogInResponse> login(
            @RequestBody LogInRequest request) {
        LogInResponse response=authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LogInResponse> refreshToken(@RequestBody RefreshTokenRequest request){
        LogInResponse response=refreshTokenService.refreshAccessToken(request);
        return ResponseEntity.ok(response);

    }


    @GetMapping("/profile")
    public String profile(){
        return "Profile API working";
    }
            
}
