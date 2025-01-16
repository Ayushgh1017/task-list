package com.codurance.training.tasks.commands;

public class ParsedCommand {
    private final Command command;
    private final String arguments;

    public ParsedCommand(Command command, String arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public Command getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }
}
