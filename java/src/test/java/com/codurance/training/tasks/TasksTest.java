package com.codurance.training.tasks;

import org.junit.Assert;
import org.junit.Test;

public class TasksTest {

    @Test
    public void testAddTask() {
        Tasks tasks = new Tasks();
        Task task1 = new Task(1, "Test Task 1", false);

        tasks.add(task1);

        Task newTask = new Task(2, "Test Task 2", false);
        tasks.add(newTask);

        Assert.assertEquals(2, tasks.size());
    }

    @Test
    public void testMarkTaskAsDone() {
        Tasks tasks = new Tasks();
        Task task1 = new Task(1, "Test Task 1", false);
        Task task2 = new Task(2, "Test Task 2", false);
        tasks.add(task1);
        tasks.add(task2);

        tasks.markTaskAsDone(1);

        Assert.assertEquals(true, task1.isDone());
    }

    @Test
    public void testMarkTaskAsUndone() {
        Tasks tasks = new Tasks();
        Task task1 = new Task(1, "Test Task 1", true);
        tasks.add(task1);

        tasks.markTaskAsUndone(1);

        Assert.assertEquals(false,task1.isDone());
    }
}