package com.healthcare.healthapp.DTO;

public class DiseasePrediction {

    private String diseaseName;
    private String description;
    private String homeTreatment;
    private boolean doctorRequired;

    // Getters and Setters
    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomeTreatment() {
        return homeTreatment;
    }

    public void setHomeTreatment(String homeTreatment) {
        this.homeTreatment = homeTreatment;
    }

    public boolean isDoctorRequired() {
        return doctorRequired;
    }

    public void setDoctorRequired(boolean doctorRequired) {
        this.doctorRequired = doctorRequired;
    }
}

