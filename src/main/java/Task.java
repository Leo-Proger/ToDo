package main.java;

import java.util.Objects;

public class Task {
    private final int id;
    private int displayId;
    private String text;
    private boolean completed;

    public Task(int id) {
        this.id = id;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public int getDisplayId() {
        return displayId;
    }

    public void setDisplayId(int displayId) {
        this.displayId = displayId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return String.format("%d. [%s] %s", getDisplayId(), isCompleted() ? "x" : " ", getText());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && displayId == task.displayId && completed == task.completed && Objects.equals(text, task.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayId, text, completed);
    }
}
