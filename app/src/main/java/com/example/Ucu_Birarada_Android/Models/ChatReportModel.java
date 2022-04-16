package com.example.Ucu_Birarada_Android.Models;

public class ChatReportModel
{
    private String reporterEmail;
    private String report;
    private String reportedEmail;

    public ChatReportModel(String reporterEmail, String report, String reportedEmail) {
        this.reporterEmail = reporterEmail;
        this.report = report;
        this.reportedEmail = reportedEmail;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public void setReporterEmail(String reporterEmail) {
        this.reporterEmail = reporterEmail;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReportedEmail() {
        return reportedEmail;
    }

    public void setReportedEmail(String reportedEmail) {
        this.reportedEmail = reportedEmail;
    }
}