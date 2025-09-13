package com.yuvraj.taskmanager.store;

import com.yuvraj.taskmanager.model.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class TaskStore {
    private final Path dataDir;
    private final Path dataFile;

    public TaskStore() {
        this.dataDir = Path.of(System.getProperty("user.home"), ".task-manager");
        this.dataFile = dataDir.resolve("tasks.csv");
        ensureInitialized();
    }

    private void ensureInitialized() {
        try {
            if (!Files.exists(dataDir)) Files.createDirectories(dataDir);
            if (!Files.exists(dataFile)) Files.createFile(dataFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize data store", e);
        }
    }

    public List<Task> loadAll() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(dataFile, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                tasks.add(Task.fromCsv(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read tasks", e);
        }
        tasks.sort(Comparator.comparingLong(Task::getId));
        return tasks;
    }

    public void saveAll(List<Task> tasks) {
        try (BufferedWriter bw = Files.newBufferedWriter(
                dataFile, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Task t : tasks) {
                bw.write(t.toCsv());
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write tasks", e);
        }
    }

    public long nextId(List<Task> existing) {
        return existing.stream().mapToLong(Task::getId).max().orElse(0L) + 1L;
    }
}
