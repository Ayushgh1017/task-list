package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.Projects;

import java.io.Writer;

public class CommandContext {
    private final Projects projects;
    private final Writer writer;
    private final ParsedCommand command;

    public CommandContext(Projects projects, Writer writer, ParsedCommand command) {
        this.projects = projects;
        this.writer = writer;
        this.command = command;
    }

    public Projects getProjects() {
        return projects;
    }

    public Writer getWriter() {
        return writer;
    }

    public ParsedCommand getCommand() {
        return command;
    }

    public String getArguments() {
        return command.getArguments();
    }

    public String[] getParsedArguments() {
        return command.getArguments().split(" ");
    }

    public String getArgumentAt(int index) {
        String[] parsedArguments = getParsedArguments();
        if (index >= 0 && index < parsedArguments.length) {
            return parsedArguments[index];
        }
        return null;
    }
}
