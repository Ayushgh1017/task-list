package com.codurance.training.tasks.commands;

import com.codurance.training.tasks.Projects;
import com.codurance.training.tasks.Task;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

public class ShowCommandTest {
    @Test
    public void shouldDisplayProjectsAndTasks() throws Exception {
        // Arrange
        ShowCommand showCommand = new ShowCommand();
        Projects projects = new Projects();
        projects.addProject("caizin");
        Writer writer = new StringWriter();

        Task task = new Task(1, "Task 1", false);
        projects.addTaskToProject("caizin", task, writer);

        CommandContext context = new CommandContext(projects, writer, "");
        showCommand.execute(context);
        String expected = "caizin" + System.lineSeparator() +
                "[ ] 1: Task 1" + System.lineSeparator();

        Assert.assertEquals(expected, writer.toString());
    }
}
