package com.example.greatestdiarybackend.models;

public class NoteModel {
    private String uuid;
    private String description;
    private String date;
    private String location;
    private String emojiName;

    public String getUuid() {
        return uuid;
    }

    public NoteModel setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NoteModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDate() {
        return date;
    }

    public NoteModel setDate(String date) {
        this.date = date;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public NoteModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getEmojiName() {
        return emojiName;
    }

    public NoteModel setEmojiName(String emojiName) {
        this.emojiName = emojiName;
        return this;
    }
}
