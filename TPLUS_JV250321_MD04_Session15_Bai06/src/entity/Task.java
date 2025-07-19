package entity;

import Validator.Validation;

import java.util.Scanner;

public class Task {
    private int taskId;
    private String taskName;
    private boolean status;

    public Task() {
    }

    public Task(int taskId, String taskName, boolean status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner) {
        this.taskName = inputName(scanner);
        this.status = inputStatus(scanner);
    }

    public String inputName(Scanner scanner) {
        final int NAME_MAX_LENGTH = 100;
        do {
            System.out.print("Enter your task: ");
            String name = scanner.nextLine();
            if (Validation.isNotEmpty(name) && name.length() <= NAME_MAX_LENGTH) {
                return name;
            } else {
                System.err.println("This field can not empty!");
            }
        } while (true);
    }

    public boolean inputStatus(Scanner scanner) {
        do {
            System.out.print("Enter task status (True || False): ");
            String status = scanner.nextLine();
            if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Input is not valid!");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Task Name: %s - Status: %s",
                this.taskId, this.taskName, this.status ? "Đã hoàn thành" : "Chưa hoàn thành");
    }
}
