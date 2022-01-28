package de.nmadev.notes.db.entities;

import java.io.Serializable;

public class Category implements Serializable {

    private long id;
    private String name;
    private String color = "yellow";
    private int noteAmount = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNoteAmount() {
        return noteAmount;
    }

    public void setNoteAmount(int noteAmount) {
        this.noteAmount = noteAmount;
    }
}
