package com.codurance.training.tasks;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskList {
    private static final String QUIT = "quit";

    private final Map<String, Tasks> tasks = new LinkedHashMap<>();
    private final Writer writer;

    private long lastId = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String command = in.readLine();
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(out).execute(command);
    }

    public TaskList(Writer writer) {
        this.writer = writer;
    }

    public void execute(String commandLine) throws IOException {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                add(commandRest[1]);
                break;
            case "check":
                check(commandRest[1]);
                break;
            case "uncheck":
                uncheck(commandRest[1]);
                break;
            default:
                error(command);
                break;
        }
    }

    private void show() throws IOException {
        for (Map.Entry<String, Tasks> project : tasks.entrySet()) {
            writer.write(project.getKey());
            writer.write("\n");
            List<Task> taskList = project.getValue();
            writeTasks(taskList, writer);
        }
    }

    private static void writeTasks(List<Task> tasks, Writer writer) throws IOException {
        for (Task task : tasks) {
            writer.write(task.getFormat());
        }
    }

    private void add(String commandLine) throws IOException {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1]);
        }
    }

    private void addProject(String name) {
        tasks.put(name, new Tasks());
    }

    private void addTask(String project, String description) throws IOException {
        Tasks projectTasks = tasks.get(project);
        if (projectTasks == null) {
            writer.write("Could not find a project with the name \"%s\".");
            writer.write("\n");
            return;
        }
        projectTasks.add(new Task(nextId(), description, false));
    }

    private void check(String idString) throws IOException {
        setDone(idString, true);
    }

    private void uncheck(String idString) throws IOException {
        setDone(idString, false);
    }

    private void setDone(String idString, boolean done) throws IOException {
        int id = Integer.parseInt(idString);
        for (Map.Entry<String, Tasks> project : tasks.entrySet()) {
            Tasks projectTasks = project.getValue();
            if (done) {
                projectTasks.markTaskAsDone(id);
            } else {
                projectTasks.markTaskAsUndone(id);
            }
        }
        writer.write("Could not find a task with an ID of %d.");
        writer.write("\n");
    }

    private void error(String command) {
    }

    private long nextId() {
        return ++lastId;
    }
}
