package com.example.greatestdiarybackend.entities;

import com.example.greatestdiarybackend.entities.statuses.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "purposes")
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String description;
    private int priority;
    private Status status;
    private LocalDateTime startDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Target setUser(User user) {
        this.user = user;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Target setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Target setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public Target setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Target setStatus(Status status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Target setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }
}
