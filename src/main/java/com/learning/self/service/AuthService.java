package com.learning.self.service;

import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learning.self.dto.LogInRequest;
import com.learning.self.dto.SignUpRequest;
import com.learning.self.entity.Users;
import com.learning.self.entity.Password;
import com.learning.self.entity.Role;
import com.learning.self.entity.UserProfile;
import com.learning.self.repository.PasswordRepository;
import com.learning.self.repository.RoleRepository;
import com.learning.self.repository.UserProfileRepository;
import com.learning.self.repository.UsersRepository;
import com.learning.self.security.JwtUtil;

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

    public String signup(SignUpRequest request){
        
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


    public String login(LogInRequest request){
        UserProfile profile=userProfileRepository.findByEmailOrUsernameOrContact(request.getUserLog(), request.getUserLog(), request.getUserLog()).orElseThrow(()->new RuntimeException("User not found"));

        Long userId=profile.getUser().getUserId();

        Password pass=passwordRepository.findByUser_UserId(userId).orElseThrow(()->new RuntimeException("Password not found"));

        if(passwordEncoder.matches(request.getPassword(),pass.getPassword())){
            return jwtUtil.generateToken(profile.getUsername());
        }
        throw new RuntimeException("Invalid Password");
    }
    
}
