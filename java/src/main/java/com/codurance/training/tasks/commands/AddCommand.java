package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.utils.IDGenerator;

import java.io.IOException;

public class AddCommand implements Command {
    @Override
    public void execute(CommandContext context) throws IOException {
        String arguments = context.getArguments();
        String[] subcommandRest = arguments.split(" ", 2);
        String subcommand = subcommandRest[0];
        Projects projects = context.getProjects();
        if ("project".equals(subcommand)) {
            projects.addProject(subcommandRest[1]);
        } else if ("task".equals(subcommand)) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            String projectName = projectTask[0];
            String description = projectTask[1];
            Task task = new Task(IDGenerator.nextId(), description, false);
            projects.addTaskToProject(projectName, task, context.getWriter());
        }
    }
}
