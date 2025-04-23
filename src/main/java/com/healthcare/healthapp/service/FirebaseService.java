package com.healthcare.healthapp.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FirebaseService {

    public String saveUserProfile(String userId, Map<String, Object> profileData) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = db.collection("users").document(userId).set(profileData);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public DocumentSnapshot getUserProfile(String userId) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentSnapshot> future = db.collection("users").document(userId).get();
        return future.get();
    }
}
