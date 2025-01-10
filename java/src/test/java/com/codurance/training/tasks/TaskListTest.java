package com.codurance.training.tasks;


import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

public class TaskListTest {

    @Test
    public void testExecuteWithAdditionOfAProjectContainingOneTask() throws Exception {
        StringWriter writer = new StringWriter();
        TaskList taskList = new TaskList(writer);
        taskList.execute("add project caizin");
        taskList.execute("add task caizin Task1");
        taskList.execute("show");

        String expected = "caizin\n" + "[ ] 1: Task1" + "\r\n";

        Assert.assertEquals(expected, writer.toString());
    }


}