package com.healthcare.healthapp.Controller;

import com.healthcare.healthapp.DTO.DiseasePrediction;
import com.healthcare.healthapp.Service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosis")
public class DiagnosisController {

    @Autowired
    private DiagnosisService diagnosisService;

    @PostMapping
    public ResponseEntity<?> getDiagnosis(@RequestBody List<String> symptoms) {
        try {
            List<DiseasePrediction> predictions = diagnosisService.predictDiseases(symptoms);
            return ResponseEntity.ok(predictions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing diagnosis");
        }
    }
}

