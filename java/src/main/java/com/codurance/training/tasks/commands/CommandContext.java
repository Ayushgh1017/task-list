package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.Projects;

import java.io.Writer;

public class CommandContext {
    private final Projects projects;
    private final Writer writer;
    private final String arguments;

    public CommandContext(Projects projects, Writer writer, String arguments) {
        this.projects = projects;
        this.writer = writer;
        this.arguments = arguments;
    }

    public Projects getProjects() {
        return projects;
    }

    public Writer getWriter() {
        return writer;
    }

    public String getArguments() {
        return arguments;
    }
}
