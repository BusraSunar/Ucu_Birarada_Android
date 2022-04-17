package com.example.Ucu_Birarada_Android.Models;

public class DaysOfWeekModel
{

    private boolean isValid;
    private String quality;

    public DaysOfWeekModel(boolean isValid, String quality) {
        this.isValid = isValid;
        this.quality = quality;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
