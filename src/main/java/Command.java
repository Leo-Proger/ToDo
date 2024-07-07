package main.java;

public enum Command {
    ADD("add"),
    DELETE("delete"),
    EDIT("edit"),
    COMPLETE("complete"),
    UNCOMPLETE("uncomplete"),
    SEARCH("search"),
    LIST("list"),
    HELP("help");

    private final String cmdInString;
    Command(String cmd) {
        cmdInString = cmd;
    }

    public static Command getAsCommand(String text) {
        for (Command value : Command.values()) {
            if (text.equalsIgnoreCase(value.cmdInString)) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }
}
