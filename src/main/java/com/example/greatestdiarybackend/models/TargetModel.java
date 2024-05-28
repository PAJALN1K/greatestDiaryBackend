package com.example.greatestdiarybackend.models;

import com.example.greatestdiarybackend.entities.statuses.Status;

import java.time.LocalDateTime;

public class TargetModel {
    private String uuid;
    private String title;
    private String description;
    private int priority;
    private Status status;
    private LocalDateTime startDate;

    public String getUuid() {
        return uuid;
    }

    public TargetModel setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TargetModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TargetModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public TargetModel setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public TargetModel setStatus(Status status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public TargetModel setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }
}
