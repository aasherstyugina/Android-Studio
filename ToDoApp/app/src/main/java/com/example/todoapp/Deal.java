package com.example.todoapp;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Deal implements Serializable {
    private final String text;
    private final int importance;
    private final Date date;
    private final boolean isDateSet;

    public Deal(String text, int importance, Date date, boolean isDateSet){
        this.text = text;
        this.importance = importance;
        this.date = date;
        this.isDateSet = isDateSet;
    }

    public int getImportance() {
        return importance;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public boolean isDateSet() {
        return isDateSet;
    }
}
