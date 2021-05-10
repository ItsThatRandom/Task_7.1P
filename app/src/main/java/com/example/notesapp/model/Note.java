package com.example.notesapp.model;

// Custom class for our notes being stored in the database.
public class Note {
    private int note_id;
    private String content;

    public Note(int note_id, String content) {
        this.content = content;
        this.note_id = note_id;
    }

    public Note(String content) {
        this.content = content;
    }

    public Note() {
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
