package main.java;

public enum Command {
    ADD("add"),
    DELETE("delete"),
    COMPLETE("complete"),
    UNCOMPLETE("uncomplete"),
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
        throw new IllegalArgumentException("Unknown command: " + text);
    }
}
