package com.healthcare.healthapp.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.healthcare.healthapp.Model.Reminder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ReminderService {

    private final Firestore firestore = FirestoreClient.getFirestore();

    public String addReminder(Reminder reminder) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> result = firestore
                .collection("reminders")
                .document()
                .set(reminder);
        return result.get().getUpdateTime().toString();
    }

    public List<Reminder> getReminders(String userId) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore
                .collection("reminders")
                .whereEqualTo("userId", userId)
                .get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        List<Reminder> reminders = new ArrayList<>();
        for (QueryDocumentSnapshot doc : documents) {
            reminders.add(doc.toObject(Reminder.class));
        }
        return reminders;
    }

    public void deleteReminder(String documentId) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = firestore
                .collection("reminders")
                .document(documentId)
                .delete();

        writeResult.get();
    }

    public void updateReminder(String documentId, Reminder updatedReminder) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = firestore
                .collection("reminders")
                .document(documentId)
                .set(updatedReminder); // Overwrites existing document
        future.get(); // Wait for it to finish
    }
}

