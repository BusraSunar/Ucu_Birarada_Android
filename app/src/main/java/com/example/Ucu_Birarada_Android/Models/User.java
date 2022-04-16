package com.example.Ucu_Birarada_Android.Models;

public class User {
    String userMatchMail;
    String isChat;
    String isMatched;


    public User(String userMatchMail, String isChat, String isMatched) {
        this.userMatchMail = userMatchMail;
        this.isChat = isChat;
        this.isMatched = isMatched;
    }

    public String getUserMatchMail() {
        return userMatchMail;
    }

    public void setUserMatchMail(String userMatchMail) {
        this.userMatchMail = userMatchMail;
    }

    public String getIsChat() {
        return isChat;
    }

    public void setIsChat(String isChat) {
        this.isChat = isChat;
    }

    public String getIsMatched() {
        return isMatched;
    }

    public void setIsMatched(String isMatched) {
        this.isMatched = isMatched;
    }
}
