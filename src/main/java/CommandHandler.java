package main.java;

public class CommandHandler {
    ToDo toDo;

    public CommandHandler(ToDo toDo) {
        this.toDo = toDo;
    }

    public void handle(String string) {
        String[] command = string.trim().toLowerCase().split(" ", 2);

        switch (command[0]) {
            case "add":
                toDo.addTask(command[1]);
                break;
            case "delete":
                toDo.deleteTask(command[1]);
                break;
            case "complete":
                toDo.markTaskAsCompleted(command[1]);
                break;
            case "uncomplete":
                toDo.markTaskAsUncompleted(command[1]);
            case "list":
                for (Task task : toDo.getAllTasks()) {
                    System.out.println(task);
                }
                break;
            case "help":
                System.out.println(Main.HELP_TEXT);
            default:
                System.out.println("Unknown command.");
        }
    }
}
