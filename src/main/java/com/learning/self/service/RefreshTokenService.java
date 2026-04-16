package com.learning.self.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.learning.self.dto.LogInResponse;
import com.learning.self.dto.RefreshTokenRequest;
import com.learning.self.entity.RefreshToken;
import com.learning.self.entity.Users;
import com.learning.self.repository.RefreshTokenRepository;
import com.learning.self.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    public RefreshToken createRefreshToken(Users userId){
        RefreshToken refreshToken= new RefreshToken();
        refreshToken.setUser(userId);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plus(7,ChronoUnit.DAYS));
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().isBefore(Instant.now())){
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired");
        }
        return token;
    }

    public LogInResponse  refreshAccessToken(RefreshTokenRequest request){
        RefreshToken refreshToken=refreshTokenRepository.findByToken(request.getRefreshToken()).orElseThrow(()->new RuntimeException("Invalid refresh token"));
        verifyExpiration(refreshToken);
        String newAccessToken=jwtUtil.generateToken(String.valueOf(refreshToken.getUser()));
        return new LogInResponse(newAccessToken, refreshToken.getToken());
    }
}
