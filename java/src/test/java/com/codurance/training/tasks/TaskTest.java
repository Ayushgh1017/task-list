package com.codurance.training.tasks;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {

    @Test
    public void testTaskFormat() {
        Task task = new Task(1,"Task1", false);
        Assert.assertEquals("[ ] 1: Task1\r\n",task.getFormat());
    }
}