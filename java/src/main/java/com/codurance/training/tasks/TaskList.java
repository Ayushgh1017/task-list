package com.codurance.training.tasks;

import com.codurance.training.tasks.commands.Command;
import com.codurance.training.tasks.commands.CommandContext;
import com.codurance.training.tasks.commands.CommandRegistry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;

public final class TaskList {
    private static final String QUIT = "quit";

    private final Projects projects;
    private final CommandRegistry commandRegistry;

    public TaskList(Writer writer) {
        this.projects = new Projects();
        this.commandRegistry = new CommandRegistry(projects);
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
        String[] commandParts = commandLine.split(" ", 2);
        String commandName = commandParts[0];
        String arguments = commandParts.length > 1 ? commandParts[1] : "";

        Command command = commandRegistry.getCommand(commandName);
        if (command == null) {
            writer.write(String.format("Unknown command: %s%n", commandName));
            return;
        }

        CommandContext context = new CommandContext(projects, writer, arguments);
        command.execute(context);
    }
}
