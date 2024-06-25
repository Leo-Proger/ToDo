import java.util.Scanner;

public class Main {
    public static final String HELP_TEXT = """
            Available commands:
              add <task>        - Add a new task
              delete <task>     - Delete a task matching unique part
              complete <task>   - Mark matching task as completed
              uncomplete <task> - Mark matching task as uncompleted
              list              - Show all tasks
              help              - Display this message

              Note: <part> can be a unique part of the task text.\s
              If not unique, you'll be asked to provide a more specific text.
            """;

    public static void main(String[] args) {
        ToDo toDo = new ToDo();
        CommandHandler cmd = new CommandHandler(toDo);
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------- Welcome to ToDo ----------\n");
        System.out.println(HELP_TEXT);
        while (true) {
            System.out.print(">>> ");
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Tasks saved. Goodbye!");
                break;
            }
            cmd.handle(line);
        }
    }
}
