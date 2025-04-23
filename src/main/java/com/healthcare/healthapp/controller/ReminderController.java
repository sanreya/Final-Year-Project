package com.healthcare.healthapp.controller;

import com.healthcare.healthapp.Model.Reminder;
import com.healthcare.healthapp.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@CrossOrigin(origins = "*") // Allow frontend (Flutter) access
public class ReminderController {

    private final ReminderService reminderService;

    @Autowired
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    // 1. Create Reminder
    @PostMapping("/add")
    public ResponseEntity<String> addReminder(@RequestBody Reminder reminder) {
        try {
            String timestamp = reminderService.addReminder(reminder);
            return ResponseEntity.ok("Reminder added at: " + timestamp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // 2. Get All Reminders for a User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reminder>> getUserReminders(@PathVariable String userId) {
        try {
            List<Reminder> reminders = reminderService.getReminders(userId);
            return ResponseEntity.ok(reminders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{documentId}")
    public ResponseEntity<String> updateReminder(@PathVariable String documentId, @RequestBody Reminder updatedReminder) {
        try {
            reminderService.updateReminder(documentId, updatedReminder);
            return ResponseEntity.ok("Reminder updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update failed: " + e.getMessage());
        }
    }

    // 3. Optional: Delete Reminder by Document ID
    @DeleteMapping("/delete/{documentId}")
    public ResponseEntity<String> deleteReminder(@PathVariable String documentId) {
        try {
            reminderService.deleteReminder(documentId);
            return ResponseEntity.ok("Reminder deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}

