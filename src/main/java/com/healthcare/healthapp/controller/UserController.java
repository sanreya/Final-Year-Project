package com.healthcare.healthapp.controller;

import com.google.cloud.firestore.DocumentSnapshot;
import com.healthcare.healthapp.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping("/save/{userId}")
    public String saveUser(@PathVariable String userId, @RequestBody Map<String, Object> profileData) throws Exception {
        return firebaseService.saveUserProfile(userId, profileData);
    }

    @GetMapping("/{userId}")
    public Map<String, Object> getUser(@PathVariable String userId) throws Exception {
        DocumentSnapshot snapshot = firebaseService.getUserProfile(userId);
        return snapshot.exists() ? snapshot.getData() : Map.of("error", "User not found");
    }
}
