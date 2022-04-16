package com.example.Ucu_Birarada_Android.Models;

import com.google.firebase.Timestamp;

import java.sql.Time;

public class ChatMessage {

    public String senderEmail;
    public String receiverEmail;
    public String message;
    public long dateTime;

    //deneme field
    private Timestamp created;

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public  ChatMessage(){

    }

    public ChatMessage(String senderEmail, String receiverEmail, String message, long dateTime, Timestamp timestamp) {
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.message = message;

        this.created = timestamp;
        this.dateTime = dateTime;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }



}

