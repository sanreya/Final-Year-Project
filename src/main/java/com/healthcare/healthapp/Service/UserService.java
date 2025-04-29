package com.healthcare.healthapp.Service;

import com.healthcare.healthapp.Entity.User;
import com.healthcare.healthapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> saveUser(User user) {
        try {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // Check if the user exists by email
            User existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser != null) {
                // Update all fields of the existing user
                existingUser.setName(user.getName());
                existingUser.setGender(user.getGender());
                existingUser.setAge(user.getAge());
                existingUser.setHabits(user.getHabits());
                existingUser.setPreviousDiagnosis(user.getPreviousDiagnosis());
                existingUser.setHeight(user.getHeight());
                existingUser.setWeight(user.getWeight());
                existingUser.setEmail(user.getEmail());
                existingUser.setUsername(user.getUsername());
                existingUser.setPassword(user.getPassword());

                userRepository.save(existingUser); // Save the updated user
                return ResponseEntity.ok("User updated successfully");
            }

            // Save the new user if no existing user is found
            userRepository.save(user);
            return ResponseEntity.ok("User registered successfully");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unable to register or update user");
        }
    }
}
