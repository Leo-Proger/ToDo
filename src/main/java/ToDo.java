package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDo {
    private final String errorMessage = "An error has occurred.";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<Task> taskList;
    private final AtomicInteger idGenerator;

    public ToDo() {
        taskList = new LinkedList<>();
        idGenerator = new AtomicInteger(0);
    }

    public void add(String text) {
        if (!text.isEmpty()) {
            Task task = new Task(idGenerator.incrementAndGet());
            task.setText(text);
            taskList.add(task);
            saveToJson(Main.JSON_FILE);
        }
        System.out.println("Task added successfully!");
    }

    public void delete(String string) {
        if (string != null) {
            for (Task task : taskList) {
                String taskText = task.getText();
                if (taskText.contains(string.toLowerCase(Locale.ROOT))) {
                    taskList.remove(task);
                    System.out.printf("Task \"%s\" deleted.\n", taskText);
                    saveToJson(Main.JSON_FILE);
                    return;
                }
            }
            System.out.println(errorMessage);
        }
    }

    public void markAsCompleted(String string) {
        for (Task task : taskList) {
            String taskText = task.getText();
            if (taskText.contains(string.toLowerCase(Locale.ROOT))) {
                task.setCompleted(true);
                System.out.println("Task \"" + taskText + "\" marked as completed.");
                return;
            }
        }
        System.out.println(errorMessage);
    }

    public void markAsUncompleted(String string) {
        for (Task task : taskList) {
            String taskText = task.getText();
            if (taskText.contains(string.toLowerCase(Locale.ROOT))) {
                task.setCompleted(false);
                System.out.println("Task \"" + taskText + "\" marked as uncompleted.");
                return;
            }
        }
        System.out.println(errorMessage);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskList);
    }

    public void saveToJson(File file) {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(taskList, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadFromJson(File file) {
        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<LinkedList<Task>>() {
            }.getType();
            taskList = gson.fromJson(reader, type);
            if (taskList == null) {
                taskList = new LinkedList<>();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
