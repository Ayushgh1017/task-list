package com.codurance.training.tasks;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

public class Projects extends LinkedHashMap<String, Tasks> {

    public void addProject(String name) {
        this.put(name, new Tasks());
    }

    public void addTaskToProject(String projectName, Task task, Writer writer) throws IOException {
        Tasks tasks = this.get(projectName);
        if (tasks == null) {
            writer.write(String.format("Could not find a project with the name \"%s\".%n", projectName));
            return;
        }
        tasks.addTask(task);
    }

    public void displayProjects(Writer writer) throws IOException {
        for (Map.Entry<String, Tasks> entry : this.entrySet()) {
            writer.write(entry.getKey());
            writer.write("\r\n");
            displayTasks(entry.getValue(), writer);
        }
    }

    private void displayTasks(Tasks tasks, Writer writer) throws IOException {
        for (Task task : tasks) {
            writer.write(task.getFormat());
        }
    }

    public boolean setTaskStatus(long taskId, boolean done) {
        for (Tasks tasks : this.values()) {
            if (done) {
                tasks.markTaskAsDone(taskId);
            } else {
                tasks.markTaskAsUndone(taskId);
            }
            return true;
        }
        return false;
    }
}
