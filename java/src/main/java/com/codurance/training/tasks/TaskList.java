package com.codurance.training.tasks;

import java.io.*;

public final class TaskList {
    private static final String QUIT = "quit";

    private final Projects projects = new Projects();
    private final Writer writer;

    private long lastId = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out, true);
        TaskList taskList = new TaskList(out);

        String command;
        while (!(command = in.readLine()).equals(QUIT)) {
            taskList.execute(command);
        }
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
                setTaskStatus(commandRest[1], true);
                break;
            case "uncheck":
                setTaskStatus(commandRest[1], false);
                break;
            default:
                writer.write(String.format("Unknown command: %s%n", command));
                break;
        }
    }

    private void show() throws IOException {
        projects.displayProjects(writer);
    }

    private void add(String commandLine) throws IOException {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if ("project".equals(subcommand)) {
            projects.addProject(subcommandRest[1]);
        } else if ("task".equals(subcommand)) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            String projectName = projectTask[0];
            String description = projectTask[1];
            Task task = new Task(nextId(), description, false);
            projects.addTaskToProject(projectName, task, writer);
        }
    }

    private void setTaskStatus(String idString, boolean done) throws IOException {
        long id = Long.parseLong(idString);
        boolean updated = projects.setTaskStatus(id, done);
        if (!updated) {
            writer.write(String.format("Could not find a task with an ID of %d.%n", id));
        }
    }

    private long nextId() {
        return ++lastId;
    }
}
