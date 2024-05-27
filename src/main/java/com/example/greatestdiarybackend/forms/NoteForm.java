package com.example.greatestdiarybackend.forms;

import com.example.greatestdiarybackend.entities.User;

public class NoteForm {
    private User user;
    private String description;
    private String date;
    private String location;
    private String emojiName;

    public User getUser() {
        return user;
    }

    public NoteForm setUser(User user) {
        this.user = user;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NoteForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDate() {
        return date;
    }

    public NoteForm setDate(String date) {
        this.date = date;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public NoteForm setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getEmojiName() {
        return emojiName;
    }

    public NoteForm setEmojiName(String emojiName) {
        this.emojiName = emojiName;
        return this;
    }
}
