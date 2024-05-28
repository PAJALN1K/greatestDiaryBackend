package com.example.greatestdiarybackend.forms;

public class TargetForm {
    private String title;
    private String description;
    private int priority;

    public String getTitle() {
        return title;
    }

    public TargetForm setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TargetForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public TargetForm setPriority(int priority) {
        this.priority = priority;
        return this;
    }
}
