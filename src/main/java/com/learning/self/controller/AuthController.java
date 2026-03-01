package com.learning.self.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.self.dto.LogInRequest;
import com.learning.self.dto.SignUpRequest;
import com.learning.self.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/self")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authService.signup(request));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LogInRequest request) {

        return ResponseEntity.ok(
            authService.login(request));

    
}
}
