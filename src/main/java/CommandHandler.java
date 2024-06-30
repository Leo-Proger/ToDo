package main.java;

import java.util.Arrays;
import java.util.List;

public class CommandHandler {
    ToDo toDo;

    public CommandHandler(ToDo toDo) {
        this.toDo = toDo;
    }

    public void handle(String string) {
        String[] command = string.trim().toLowerCase().split(" ", 2);
        String[] subArr;

        switch (command[0]) {
            case "add":
                toDo.add(command[1]);
                break;
            case "delete":
                subArr = command[1].split(" ");
                toDo.delete(Arrays.stream(subArr).mapToInt(Integer::parseInt).toArray());
                break;
            case "complete":
                subArr = command[1].split(" ");
                toDo.markAs(Arrays.stream(subArr).mapToInt(Integer::parseInt).toArray(), true);
                break;
            case "uncomplete":
                subArr = command[1].split(" ");
                toDo.markAs(Arrays.stream(subArr).mapToInt(Integer::parseInt).toArray(), false);
            case "list":
                List<Task> tasks = toDo.getAllTasks();
                if (!tasks.isEmpty()) {
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                } else {
                    System.out.println("You don't have any task.");
                }
                break;
            case "help":
                System.out.println(Main.HELP_TEXT);
                break;
            default:
                System.out.println("Unknown command.");
        }
    }
}
