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

        try {
            switch (Command.getAsCommand(command[0])) {
                case Command.ADD -> toDo.add(command[1]);
                case Command.DELETE -> {
                    subArr = command[1].split(" ");
                    toDo.delete(Arrays.stream(subArr).mapToInt(Integer::parseInt).toArray());
                }
                case Command.COMPLETE -> {
                    subArr = command[1].split(" ");
                    toDo.markAs(Arrays.stream(subArr).mapToInt(Integer::parseInt).toArray(), true);
                }
                case Command.UNCOMPLETE -> {
                    subArr = command[1].split(" ");
                    toDo.markAs(Arrays.stream(subArr).mapToInt(Integer::parseInt).toArray(), false);
                }
                case Command.LIST -> toDo.print();
                case Command.HELP -> System.out.println(Main.HELP_TEXT);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
