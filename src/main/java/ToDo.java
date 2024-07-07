package main.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDo {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private List<Task> taskList;
    private final AtomicInteger idGenerator;

    public ToDo() {
        taskList = new LinkedList<>();
        loadFromJson(Main.JSON_FILE);

        idGenerator = new AtomicInteger(
                taskList.stream()
                        .mapToInt(Task::getId)
                        .max()
                        .orElse(0)
        );
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

    public void edit(int taskDisplayId) {
        Optional<Task> optionalTask = taskList.stream()
                .filter(task -> task.getDisplayId() == taskDisplayId)
                .findFirst();
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            System.out.print("Current task text: ");
            System.out.println(task.getText());

            System.out.print("Enter a new task text >>> ");
            String newTaskText = new Scanner(System.in).nextLine();

            if (!newTaskText.trim().isEmpty()) {
                task.setText(newTaskText);
                System.out.println("Task text updated!");
            } else {
                System.out.println("No changes have been made.");
            }
        } else {
            System.out.println("Task not found.");
        }
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

    public void search(String string) {
        for (Task task : getAllTasks()) {
            if (task.getText().contains(string)) {
                System.out.printf("%d. [%s] %s\n", task.getDisplayId(), task.isCompleted() ? "x" : " ", task.getText());
            }
        }
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskList);
    }

    public void saveToJson(File file) {
        // Создать родительские директории, если они не существуют
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        // Создать файл, если он не существует
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        // Запись данных в JSON
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(taskList, writer);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void loadFromJson(File file) {
        // Создать родительские директории, если они не существуют
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        // Создать файл, если он не существует
        if (!file.exists()) {
            try {
                file.createNewFile();
                taskList = new LinkedList<>();
                return;
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                taskList = new LinkedList<>();
                return;
            }
        }

        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<LinkedList<Task>>() {
            }.getType();
            taskList = gson.fromJson(reader, type);

            if (taskList == null) {
                taskList = new LinkedList<>();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            taskList = new LinkedList<>();
        }
    }

    /* Обновляем нумерацию отображаемых задач */
    private void updateDisplayIds() {
        for (Task task : taskList) {
            task.setDisplayId(taskList.indexOf(task) + 1);
        }
    }

    public void print() {
        List<Task> tasks = getAllTasks();
        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                System.out.println(task);
            }
        } else {
            System.out.println("You don't have any task.");
        }
    }
}
