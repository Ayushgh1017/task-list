package com.codurance.training.tasks.commands;

import java.io.IOException;
public class ShowCommand implements Command {
    @Override
    public void execute(CommandContext context) throws IOException {
        context.getProjects().displayProjects(context.getWriter());
    }
}

