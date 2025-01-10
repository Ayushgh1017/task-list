package com.codurance.training.tasks;

import java.util.ArrayList;

public class Tasks extends ArrayList<Task> {
    
    public void markTaskAsDone(long id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.markAsDone();
        }
    }

    public void markTaskAsUndone(long id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.markAsUndone();
        }
    }

    private Task findTaskById(long id) {
        for (Task task : this) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
}
