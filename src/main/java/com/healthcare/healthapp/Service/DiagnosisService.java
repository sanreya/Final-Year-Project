package com.healthcare.healthapp.Service;

import com.healthcare.healthapp.DTO.DiseasePrediction;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class DiagnosisService {

    // Assuming EndlessMedical API base URL and key are stored in application.properties
    @Value("${endlessmedical.api.url}")
    private String apiUrl;

    @Value("${endlessmedical.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public DiagnosisService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<DiseasePrediction> predictDiseases(List<String> symptoms) {
        // Build the request URL with symptoms as query parameters
        String symptomsParam = String.join(",", symptoms);

        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .path("/predict")
                .queryParam("symptoms", symptomsParam)
                .queryParam("apiKey", apiKey)
                .toUriString();

        // Call the API and get the response
        DiseasePrediction[] predictions = restTemplate.getForObject(url, DiseasePrediction[].class);

        // Assuming the API returns an array of DiseasePrediction objects, now process the top 3
        if (predictions != null && predictions.length > 0) {
            return List.of(predictions).subList(0, Math.min(3, predictions.length));
        } else {
            throw new RuntimeException("No disease predictions found");
        }
    }
}

