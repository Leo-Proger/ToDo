import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDo {
    private final String errorMessage = "An error has occurred.";

    private final List<Task> taskList;
    private final AtomicInteger idGenerator;

    public ToDo() {
        taskList = new LinkedList<>();
        idGenerator = new AtomicInteger(0);
    }

    public void addTask(String text) {
        Task task = new Task(text, idGenerator.incrementAndGet());
        boolean isAdded = taskList.add(task);
        System.out.println("Task added successfully!");
    }

    public void deleteTask(String string) {
        for (Task task : taskList) {
            String taskText = task.getText();
            if (taskText.contains(string.toLowerCase(Locale.ROOT))) {
                taskList.remove(task);
                System.out.println("Task \"" + taskText + "\" deleted.");
                return;
            }
        }
        System.out.println(errorMessage);
    }

    public void markTaskAsCompleted(String string) {
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

    public void markTaskAsUncompleted(String string) {
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
}
