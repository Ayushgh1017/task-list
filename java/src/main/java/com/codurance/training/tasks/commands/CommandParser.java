package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.Projects;

public class CommandParser {
    private final CommandRegistry commandRegistry;

    public CommandParser(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    public ParsedCommand parse(String input, Projects projects) {
        String[] parts = input.split(" ", 2);
        String commandName = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        Command command = commandRegistry.getCommand(commandName);
        if (command == null) {
            throw new IllegalArgumentException(String.format("Unknown command: %s", commandName));
        }

        return new ParsedCommand(command, arguments);
    }
}
