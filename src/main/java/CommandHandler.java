package main.java;

import java.util.Arrays;

public class CommandHandler {
    ToDo toDo;

    public CommandHandler(ToDo toDo) {
        this.toDo = toDo;
    }

    public void handle(String string) {
        String[] args = string.trim().toLowerCase().split(" ", 2);
        String[] subArr;

        try {
            switch (Command.getAsCommand(args[0])) {
                case Command.ADD -> toDo.add(args[1]);
                case Command.DELETE -> {
                    subArr = args[1].split(" ");
                    toDo.delete(Arrays.stream(subArr).mapToInt(Integer::parseInt).toArray());
                }
                case Command.EDIT -> toDo.edit(Integer.parseInt(args[1]));
                case Command.COMPLETE -> {
                    subArr = args[1].split(" ");
                    toDo.markAs(Arrays.stream(subArr).mapToInt(Integer::parseInt).toArray(), true);
                }
                case Command.UNCOMPLETE -> {
                    subArr = args[1].split(" ");
                    toDo.markAs(Arrays.stream(subArr).mapToInt(Integer::parseInt).toArray(), false);
                }
                case Command.SEARCH -> toDo.search(args[1]);
                case Command.LIST -> toDo.print();
                case Command.HELP -> System.out.println(Main.HELP_TEXT);
            }
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
