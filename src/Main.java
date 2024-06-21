import java.util.Scanner;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        ToDo toDo = new ToDo();

        toDo.addTask("Покормить пса");
        toDo.addTask("Сходить погулять");
        printTasks(toDo);

        toDo.deleteTask("гулять");

        toDo.addTask("Что-то сделать");
        printTasks(toDo);

        toDo.markTaskAsCompleted("ПСА");
        printTasks(toDo);
    }

    private static void printTasks(ToDo toDo) {
        for (Task task : toDo.getAllTasks()) {
            System.out.println(task);
        }
        System.out.print('\n');
    }
}
