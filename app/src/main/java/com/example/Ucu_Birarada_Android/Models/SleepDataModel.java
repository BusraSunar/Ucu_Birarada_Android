package com.example.Ucu_Birarada_Android.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class SleepDataModel
{
    private String date;
    private Double frequency;

    public SleepDataModel(String date, Double frequency) {
        this.date = date;
        this.frequency = frequency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }
}