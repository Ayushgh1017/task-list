package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.utils.IDGenerator;

import java.io.IOException;

public class AddCommand implements Command {
    @Override
    public void execute(CommandContext context) throws IOException {
        ParsedCommand parsedCommand = context.getCommand();
        String[] parsedArguments = parsedCommand.getArguments().split(" ");

        if (parsedArguments.length < 2) {
            context.getWriter().write("Invalid arguments for add command. Usage: add project <projectName> or add task <projectName> <taskDescription>\n");
            return;
        }

        String subcommand = parsedArguments[0];
        Projects projects = context.getProjects();

        if ("project".equals(subcommand)) {
            if (parsedArguments.length < 2) {
                context.getWriter().write("Project name is missing. Usage: add project <projectName>\n");
                return;
            }
            String projectName = parsedArguments[1];
            projects.addProject(projectName);
        } else if ("task".equals(subcommand)) {
            if (parsedArguments.length < 3) {
                context.getWriter().write("Invalid arguments for task. Usage: add task <projectName> <taskDescription>\n");
                return;
            }
            String projectName = parsedArguments[1];
            String description = String.join(" ", java.util.Arrays.copyOfRange(parsedArguments, 2, parsedArguments.length));

            Task task = new Task(IDGenerator.nextId(), description, false);
            projects.addTaskToProject(projectName, task, context.getWriter());
        } else {
            context.getWriter().write(String.format("Unknown subcommand: %s. Valid subcommands are 'project' and 'task'.\n", subcommand));
        }
    }
}
