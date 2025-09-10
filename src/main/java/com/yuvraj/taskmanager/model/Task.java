package com.yuvraj.taskmanager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Task model.
 */
public final class Task {
    public enum Priority { LOW, MEDIUM, HIGH }

    private final long id;
    private final String title;
    private final Priority priority;
    private final LocalDate dueDate;       // nullable
    private final boolean done;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private Task(
            long id,
            String title,
            Priority priority,
            LocalDate dueDate,
            boolean done,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.done = done;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Task createNew(long id, String title, Priority priority, LocalDate dueDate) {
        LocalDateTime now = LocalDateTime.now();
        return new Task(id, title.trim(), priority, dueDate, false, now, now);
    }

    public Task markDone() {
        return new Task(id, title, priority, dueDate, true, createdAt, LocalDateTime.now());
    }

    public Task updateTitle(String newTitle) {
        return new Task(id, newTitle.trim(), priority, dueDate, done, createdAt, LocalDateTime.now());
    }

    public Task updatePriority(Priority newPriority) {
        return new Task(id, title, newPriority, dueDate, done, createdAt, LocalDateTime.now());
    }

    public Task updateDueDate(LocalDate newDueDate) {
        return new Task(id, title, priority, newDueDate, done, createdAt, LocalDateTime.now());
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public Priority getPriority() { return priority; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isDone() { return done; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    @Override
    public String toString() {
        String status = done ? "DONE" : "OPEN";
        String due = (dueDate == null) ? "-" : dueDate.toString();
        return String.format("#%d [%s] %s | P:%s | Due:%s | Created:%s",
                id, status, title, priority, due, createdAt.toLocalDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task that = (Task) o;
        return id == that.id
                && done == that.done
                && Objects.equals(title, that.title)
                && priority == that.priority
                && Objects.equals(dueDate, that.dueDate)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, priority, dueDate, done, createdAt, updatedAt);
    }

    public String toCsv() {
        String safeTitle = title.replace(",", " ");
        String due = (dueDate == null) ? "" : dueDate.toString();
        return id + "," + safeTitle + "," + priority + "," + due + "," + done + ","
                + createdAt + "," + updatedAt;
    }

    public static Task fromCsv(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 7) throw new IllegalArgumentException("Corrupt data: " + line);

        long id = Long.parseLong(parts[0]);
        String title = parts[1];
        Priority priority = Priority.valueOf(parts[2]);
        LocalDate due = parts[3].isEmpty() ? null : LocalDate.parse(parts[3]);
        boolean done = Boolean.parseBoolean(parts[4]);
        LocalDateTime created = LocalDateTime.parse(parts[5]);
        LocalDateTime updated = LocalDateTime.parse(parts[6]);
        return new Task(id, title, priority, due, done, created, updated);
    }
}
