package com.example.Ucu_Birarada_Android.Models;

public class AchievementModel
{


    private String id;
    private String email;
    private String achievementType;
    private String description; private String percentage;
    private String isCompleted;
    private String goal;
    private String occurred;


    public String getId() {
        return id;
    }

    public AchievementModel(String id, String email, String achievementType, String description, String percentage, String isCompleted, String goal, String occurred) {
        this.id = id;
        this.email = email;
        this.achievementType = achievementType;
        this.description = description;
        this.percentage = percentage;
        this.isCompleted = isCompleted;
        this.goal = goal;
        this.occurred = occurred;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAchievementType() {
        return achievementType;
    }

    public void setAchievementType(String achievementType) {
        this.achievementType = achievementType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String isCompleted() {
        return isCompleted;
    }

    public void setCompleted(String completed) {
        isCompleted = completed;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getOccurred() {
        return occurred;
    }

    public void setOccurred(String occurred) {
        this.occurred = occurred;
    }







}
