package com.learning.self.service;

import java.time.Instant;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.self.dto.LogInRequest;
import com.learning.self.dto.LogInResponse;
import com.learning.self.dto.SignUpRequest;
import com.learning.self.entity.Users;
import com.learning.self.entity.BlacklistedToken;
import com.learning.self.entity.Password;
import com.learning.self.entity.RefreshToken;
import com.learning.self.entity.Role;
import com.learning.self.entity.UserProfile;
import com.learning.self.repository.BlacklistedTokenReposistory;
import com.learning.self.repository.PasswordRepository;
import com.learning.self.repository.RoleRepository;
import com.learning.self.repository.UserProfileRepository;
import com.learning.self.repository.UsersRepository;
import com.learning.self.security.JwtUtil;
import com.learning.self.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthService {
    private final JwtUtil jwtUtil;
    private final UsersRepository userRepository;
    private final PasswordRepository passwordRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserProfileRepository userProfileRepository;
    private final RefreshTokenService  refreshTokenService;
    private final BlacklistedTokenReposistory blacklistedTokenReposistory;

    public String signup(SignUpRequest request){

        if ((request.getEmail() == null || request.getEmail().isBlank()) &&
        (request.getUsername() == null || request.getUsername().isBlank()) &&
        (request.getContact() == null || request.getContact().isBlank())) {

            throw new IllegalArgumentException(
                "At least one of email, username, or contact must be provided"
            );
        }

        if ((request.getPassword() == null || request.getPassword().isBlank()) ) {

            throw new IllegalArgumentException(
                "Password cannot be blank"
            );
        }  

        if (request.getEmail() != null && userProfileRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        if (request.getUsername() != null && userProfileRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        if (request.getContact() != null && userProfileRepository.existsByContact(request.getContact())) {
            throw new RuntimeException("Contact already registered");
        }

        
        Users user =new Users();
        UserProfile profile=new UserProfile();
        profile.setEmail(request.getEmail());
        profile.setUsername(request.getUsername());
        profile.setContact(request.getContact());
        profile.setEnabled(true);
        profile.setAccountLocked(false);
        profile.setAccountCreatedAt(Instant.now());
        
        user.setLocation(request.getLocation());
        userRepository.save(user);
        profile.setUser(user);
        userProfileRepository.save(profile);
        Password pass=new Password();
        pass.setPassword(passwordEncoder.encode(request.getPassword()));
        pass.setUser(user);

        passwordRepository.save(pass);

        Role role=new Role();
        role.setBasic(true);
        role.setAdvance(false);
        role.setUser(user);

        roleRepository.save(role);

        
        return "user registered successfully";

    }


    public LogInResponse login(LogInRequest request){    

        Optional<UserProfile> profile=userProfileRepository.findByEmailOrUsernameOrContact(request.getUserLog(), request.getUserLog(), request.getUserLog());
        
        if(profile.isEmpty()){
            throw new RuntimeException("User not found");
        }

        Users user=profile.get().getUser();
        String storedPassword=user.getPassword().getPassword();

        if(passwordEncoder.matches(request.getPassword(), storedPassword)){
            String accessGiven="";
            RefreshToken refreshToken=refreshTokenService.createRefreshToken(user);
            if(user.getProfile().getUsername() != null && !user.getProfile().getUsername().isEmpty()){
                accessGiven=user.getProfile().getUsername();
            }
            else if(user.getProfile().getEmail() != null && !user.getProfile().getEmail().isEmpty()){
                accessGiven=user.getProfile().getEmail();
            }
            else if(user.getProfile().getContact() != null && !user.getProfile().getContact().isEmpty()){
                accessGiven=user.getProfile().getContact(); 
            }
            String token=jwtUtil.generateToken(accessGiven);
            // return jwtUtil.generateToken(accessGiven);
            return new LogInResponse(token,refreshToken.getToken());
        }

        throw new RuntimeException("Invalid credential");

    }

    public void logout(String token){
        BlacklistedToken blacklistedToken= new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedTokenReposistory.save(blacklistedToken);
    }
    
}
