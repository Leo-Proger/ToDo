package main.java;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static final String HELP_TEXT = """
            Available commands:
              add <text>        - Add a new task
              delete <number>     - Delete a task
              complete <number>   - Mark task as completed
              uncomplete <number> - Mark task as uncompleted
              list              - Show all tasks
              help              - Display this message
              exit              - Exit the program
            """;
    static final File JSON_FILE = new File("src/main/resources/data.json");

    public static void main(String[] args) {
        // TODO: Убрать из git data.json и зафиксировать только пустой файл
        // TODO: Добавить сортировку (по дате, приоритету), редактирование, поиск задач
        ToDo toDo = new ToDo();
        CommandHandler cmd = new CommandHandler(toDo);
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------- Welcome to ToDo ----------\n");
        System.out.println(HELP_TEXT);

        toDo.loadFromJson(JSON_FILE);

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
