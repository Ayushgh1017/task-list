package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.CommandContext;
import com.codurance.training.tasks.commands.CommandParser;
import com.codurance.training.tasks.commands.CommandRegistry;
import com.codurance.training.tasks.commands.ParsedCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;

public final class TaskList {
    private static final String QUIT = "quit";

    private final Projects projects;
    private final CommandRegistry commandRegistry;
    private final CommandParser commandParser;

    public TaskList(Writer writer) {
        this.projects = new Projects();
        this.commandRegistry = new CommandRegistry(projects);
        this.commandParser = new CommandParser(commandRegistry); // Instantiate CommandParser
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out, true);
        TaskList taskList = new TaskList(out);

        String commandLine;
        while (!(commandLine = in.readLine()).equals(QUIT)) {
            taskList.execute(commandLine, out);
        }
    }

    public void execute(String commandLine, Writer writer) throws IOException {
        try {
            ParsedCommand parsedCommand = commandParser.parse(commandLine, projects);
            CommandContext context = new CommandContext(projects, writer, parsedCommand);
            parsedCommand.getCommand().execute(context);
        } catch (IllegalArgumentException e) {
            writer.write(e.getMessage() + System.lineSeparator());
        }
    }
}
