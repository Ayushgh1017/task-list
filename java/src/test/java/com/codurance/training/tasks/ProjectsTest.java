package com.codurance.training.tasks;

import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;
import java.io.Writer;

public class ProjectsTest {
    @Test
    public void shouldSetTaskStatusToDone() {
        Projects projects = new Projects();
        projects.addProject("Project1");

        Task task = new Task(1, "Task1", false);
        projects.get("Project1").add(task);

        boolean updated = projects.setTaskStatus(1, true);

        Assert.assertTrue(updated);
        Assert.assertTrue(task.isDone());
    }

    @Test
    public void shouldSetTaskStatusToUndone() {
        Projects projects = new Projects();
        projects.addProject("Project1");

        Task task = new Task(1, "Task1", true);
        projects.get("Project1").add(task);

        boolean updated = projects.setTaskStatus(1, false);

        Assert.assertTrue(updated);
        Assert.assertFalse(task.isDone());
    }

    @Test
    public void addTaskToProject_ShouldAddTaskSuccessfully() throws Exception {
        Projects projects = new Projects();
        projects.addProject("Project A");
        Writer writer = new StringWriter();

        Task task = new Task(1, "Task 1", false);
        projects.addTaskToProject("Project A", task, writer);

        Assert.assertEquals(1, projects.get("Project A").size());
        Assert.assertEquals(task, projects.get("Project A").get(0));
    }
}