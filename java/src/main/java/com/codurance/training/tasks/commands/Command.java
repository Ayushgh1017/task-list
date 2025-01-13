package com.codurance.training.tasks.commands;

import java.io.IOException;

public interface Command {
    void execute(CommandContext context) throws IOException;
}

