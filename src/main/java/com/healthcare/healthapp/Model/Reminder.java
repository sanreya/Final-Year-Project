package com.healthcare.healthapp.Model;

public class Reminder {
    private String userId;
    private String type; // "tablet" or "water"
    private String description;
    private String reminderTime;
    private boolean repeatDaily;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public boolean isRepeatDaily() {
        return repeatDaily;
    }

    public void setRepeatDaily(boolean repeatDaily) {
        this.repeatDaily = repeatDaily;
    }
}
