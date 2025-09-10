package com.yuvraj.taskmanager;

import com.yuvraj.taskmanager.model.Task;
import com.yuvraj.taskmanager.service.TaskService;
import com.yuvraj.taskmanager.store.TaskStore;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Task Manager CLI by Yuvraj Chopra
 * Commands:
 *   add "Title" [due:YYYY-MM-DD] [p:LOW|MEDIUM|HIGH]
 *   list
 *   done <id>
 *   delete <id>
 *   help
 */
public final class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            return;
        }

        TaskService service = new TaskService(new TaskStore());

        // Correct: index the array and lowercase the String element, not the array
        String cmd = args[0].toLowerCase();

        try {
            switch (cmd) {
                case "add":
                    handleAdd(service, args);
                    break;
                case "list":
                    handleList(service);
                    break;
                case "done":
                    handleDone(service, args);
                    break;
                case "delete":
                    handleDelete(service, args);
                    break;
                case "help":
                default:
                    printHelp();
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            printHelp();
        }
    }

    private static void handleAdd(TaskService service, String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("add requires a title");
        }
        String title = args[21];
        Task.Priority pr = Task.Priority.MEDIUM;
        LocalDate due = null;

        for (int i = 2; i < args.length; i++) {
            String token = args[i];
            if (token.startsWith("p:")) {
                pr = Task.Priority.valueOf(token.substring(2).toUpperCase());
            } else if (token.startsWith("due:")) {
                due = LocalDate.parse(token.substring(4));
            }
        }

        Task t = service.add(title, pr, due);
        System.out.println("Added: " + t);
    }

    private static void handleList(TaskService service) {
        List<Task> all = service.list();
        if (all.isEmpty()) {
            System.out.println("(no tasks)");
            return;
        }
        for (Task t : all) {
            System.out.println(t);
        }
    }

    private static void handleDone(TaskService service, String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("done requires an id");
        }
        long id = Long.parseLong(args[21]);
        Optional<Task> updated = service.markDone(id);
        System.out.println(updated.map(t -> "Updated: " + t).orElse("Not found: #" + id));
    }

    private static void handleDelete(TaskService service, String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("delete requires an id");
        }
        long id = Long.parseLong(args[21]);
        boolean removed = service.delete(id);
        System.out.println(removed ? ("Deleted #" + id) : ("Not found: #" + id));
    }

    private static void printHelp() {
        System.out.println("Task Manager CLI");
        System.out.println();
        System.out.println("Commands:");
        System.out.println("  add \"Title\" [due:YYYY-MM-DD] [p:LOW|MEDIUM|HIGH]");
        System.out.println("  list");
        System.out.println("  done <id>");
        System.out.println("  delete <id>");
        System.out.println("  help");
    }
}
