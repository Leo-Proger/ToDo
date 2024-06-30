package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDo {
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
            task.setDisplayId(taskList.size() + 1);
            taskList.add(task);

            saveToJson(Main.JSON_FILE);
        }
        System.out.println("Task added successfully!");
    }

    public void delete(int[] taskDisplayIds) {
        Iterator<Task> iterator = taskList.iterator();

        while (iterator.hasNext()) {
            Task task = iterator.next();

            for (int displayId : taskDisplayIds) {
                if (task.getDisplayId() == displayId) {
                    iterator.remove();
                    System.out.printf("Task \"%s\" deleted.\n", task.getText());
                    break;
                }
            }
        }
        updateDisplayIds();
        saveToJson(Main.JSON_FILE);
    }

    public void markAs(int[] taskDisplayIds, boolean isCompleted) {
        for (Task task : taskList) {
            for (int displayId : taskDisplayIds) {
                if (task.getDisplayId() == displayId) {
                    task.setCompleted(isCompleted);
                    System.out.printf("Task \"%s\" marked as %scompleted.\n", task.getText(), isCompleted ? "" : "un");
                }
            }
        }
        saveToJson(Main.JSON_FILE);
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

    /* Обновляем нумерацию отображаемых задач */
    private void updateDisplayIds() {
        for (Task task : taskList) {
            task.setDisplayId(taskList.indexOf(task) + 1);
        }
    }
}
