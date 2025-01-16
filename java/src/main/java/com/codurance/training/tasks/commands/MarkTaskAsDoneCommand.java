package com.codurance.training.tasks.commands;

import java.io.IOException;

public class MarkTaskAsDoneCommand implements Command {

    @Override
    public void execute(CommandContext context) throws IOException {
        long id = Long.parseLong(context.getArguments());
        boolean updated = context.getProjects().markTaskAsDone(id);
        if (!updated) {
            context.getWriter().write(String.format("Could not find a task with an ID of %d.%n", id));
        }
    }
}
