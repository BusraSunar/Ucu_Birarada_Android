package com.example.Ucu_Birarada_Android.Models;

public class ToDoModel
{
    private String task;
    private boolean isDone;

    public ToDoModel(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

}