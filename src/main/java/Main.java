package main.java;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static final String HELP_TEXT = """
            Available commands:
              add <text>           - Add a new task
              delete <numbers>     - Delete a task
              complete <numbers>   - Mark task as completed
              uncomplete <numbers> - Mark task as uncompleted
              search <text>        - Find tasks with a given text
              list                 - Show all tasks
              help                 - Display this message
              exit                 - Exit the program
            """;
    static final File JSON_FILE = new File("src/main/resources/data.json");

    public static void main(String[] args) {
        // TODO: Добавить сортировку (по дате, приоритету)
        // TODO: Добавить команду info, которая выдает информацию о задаче ((не)выполнена, дедлайн, дата создания, дата редактирования и тд)
        ToDo toDo = new ToDo();
        CommandHandler cmd = new CommandHandler(toDo);
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------- Welcome to ToDo ----------\n");
        System.out.println(HELP_TEXT);

        while (true) {
            System.out.print(">>> ");
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("exit")) {
                toDo.saveToJson(JSON_FILE);
                System.out.println("Tasks saved. Goodbye!");
                break;
            }
            cmd.handle(line);
        }
    }
}
