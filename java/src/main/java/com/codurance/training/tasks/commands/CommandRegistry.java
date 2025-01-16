package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.Projects;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry(Projects projects) {
        commands.put("show", new ShowCommand());
        commands.put("add", new AddCommand());
        commands.put("check", new MarkTaskAsDoneCommand());
        commands.put("uncheck", new MarkTaskAsUndoneCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
