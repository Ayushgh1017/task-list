package com.codurance.training.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

public class ProjectsTest {

    @Test
    public void shouldMarkTaskAsDone() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("Project1");

        Task task = new Task(1, "Task1", false);
        projects.get("Project1").add(task);

        // Act
        boolean updated = projects.markTaskAsDone(1);

        // Assert
        Assert.assertTrue(updated);
        Assert.assertTrue(task.isDone());
    }

    @Test
    public void shouldMarkTaskAsUndone() {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("Project1");

        Task task = new Task(1, "Task1", true);
        projects.get("Project1").add(task);

        // Act
        boolean updated = projects.markTaskAsUndone(1);

        // Assert
        Assert.assertTrue(updated);
        Assert.assertFalse(task.isDone());
    }



    @Test
    public void addTaskToProject_ShouldAddTaskSuccessfully() throws Exception {
        // Arrange
        Projects projects = new Projects();
        projects.addProject("Project A");
        Writer writer = new StringWriter();

        Task task = new Task(1, "Task 1", false);

        // Act
        projects.addTaskToProject("Project A", task, writer);

        // Assert
        Assert.assertEquals(1, projects.get("Project A").size());
        Assert.assertEquals(task, projects.get("Project A").get(0));
    }

    @Test
    public void addTaskToProject_ShouldWriteErrorMessage_WhenProjectNotFound() throws Exception {
        // Arrange
        Projects projects = new Projects();
        Writer writer = new StringWriter();

        Task task = new Task(1, "Task 1", false);

        // Act
        projects.addTaskToProject("Nonexistent Project", task, writer);

        // Assert
        Assert.assertEquals(
                "Could not find a project with this name",
                writer.toString()
        );
    }
}
