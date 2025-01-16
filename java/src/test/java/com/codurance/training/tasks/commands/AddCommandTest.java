package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.Projects;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

public class AddCommandTest {

    @Test
    public void testAddProject() throws Exception {
        Projects projects = new Projects();
        StringWriter writer = new StringWriter();
        AddCommand addCommand = new AddCommand();

        ParsedCommand parsedCommand = new ParsedCommand(addCommand, "project TestProject");
        CommandContext context = new CommandContext(projects, writer, parsedCommand);

        addCommand.execute(context);

        Assert.assertTrue(projects.containsKey("TestProject"));
    }
}
