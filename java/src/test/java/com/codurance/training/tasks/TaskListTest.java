package com.codurance.training.tasks;

import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class TaskListTest {

    @Test
    public void testExecuteWithAdditionOfAProjectContainingOneTask() throws Exception {
        StringWriter writer = new StringWriter();
        TaskList taskList = new TaskList(writer);

        taskList.execute("add project caizin", writer);
        taskList.execute("add task caizin Task1", writer);
        taskList.execute("show", writer);

        String expected = "caizin\r\n" +
                "[ ] 1: Task1\r\n";
        assertEquals(expected, writer.toString());
    }
}
