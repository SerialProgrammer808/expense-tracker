package com.noahasano.expense.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.noahasano.expense.dto.LoginRequest;
import com.noahasano.expense.dto.RegisterRequest;
import com.noahasano.expense.dto.UserDTO;
import com.noahasano.expense.services.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            userService.registerUser(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
                )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(Principal principal) {
        UserDTO userProfile = userService.getUserProfile(principal.getName());
        return ResponseEntity.ok(userProfile);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }
}
