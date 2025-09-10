package com.yuvraj.taskmanager.service;

import com.yuvraj.taskmanager.model.Task;
import com.yuvraj.taskmanager.store.TaskStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class TaskService {
    private final TaskStore store;

    public TaskService(TaskStore store) {
        this.store = store;
    }

    public Task add(String title, Task.Priority priority, LocalDate dueDate) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        List<Task> all = new ArrayList<>(store.loadAll());
        long id = store.nextId(all);
        Task created = Task.createNew(id, title, priority, dueDate);
        all.add(created);
        store.saveAll(all);
        return created;
    }

    public List<Task> list() {
        return store.loadAll();
    }

    public Optional<Task> markDone(long id) {
        List<Task> all = new ArrayList<>(store.loadAll());
        Optional<Task> found = all.stream().filter(t -> t.getId() == id).findFirst();
        if (found.isEmpty()) return Optional.empty();
        Task updated = found.get().markDone();
        replace(all, updated);
        store.saveAll(all);
        return Optional.of(updated);
    }

    public boolean delete(long id) {
        List<Task> all = new ArrayList<>(store.loadAll());
        boolean removed = all.removeIf(t -> t.getId() == id);
        if (removed) {
            store.saveAll(all);
        }
        return removed;
    }

    private void replace(List<Task> list, Task updated) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == updated.getId()) {
                list.set(i, updated);
                return;
            }
        }
    }
}
