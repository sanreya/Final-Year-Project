package com.healthcare.healthapp.Controller;

import com.healthcare.healthapp.Entity.User;
import com.healthcare.healthapp.Security.JwtUtil;
import com.healthcare.healthapp.Service.CustomUserDetailsService;
import com.healthcare.healthapp.Service.UserService;
import com.healthcare.healthapp.Security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        try {
            // Trace the authentication process
            System.out.println("Authenticating user with email: " + email);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            System.out.println("Authentication successful");

            // Generate a JWT token if authentication succeeds
            String token = jwtUtil.generateToken(email);
            return "Bearer " + token;

        } catch (Exception e) {
            // Log detailed errors for debugging
            System.err.println("Authentication failed: " + e.getMessage());
            throw new RuntimeException("Invalid credentials");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
