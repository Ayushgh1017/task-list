package com.codurance.training.tasks.commands;

import java.io.IOException;

public class SetTaskStatusCommand implements Command {
    private final boolean done;

    public SetTaskStatusCommand(boolean done) {
        this.done = done;
    }

    @Override
    public void execute(CommandContext context) throws IOException {
        long id = Long.parseLong(context.getArguments());
        boolean updated = context.getProjects().setTaskStatus(id, done);
        if (!updated) {
            context.getWriter().write(String.format("Could not find a task with an ID of %d.%n", id));
        }
    }
}
