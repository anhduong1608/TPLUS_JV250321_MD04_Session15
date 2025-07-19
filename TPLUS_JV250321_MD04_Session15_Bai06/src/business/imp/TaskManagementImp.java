package business.imp;

import Validator.Validation;
import business.TaskBusiness;
import dao.TaskDAO;
import dao.imp.TaskDAOImp;
import entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskManagementImp implements TaskBusiness {
    private final TaskDAO taskDAO;

    public TaskManagementImp() {
        taskDAO = new TaskDAOImp();
    }

    @Override
    public void addTask(Scanner scanner) {
        Task task = new Task();
        task.inputData(scanner);
        if (taskDAO.addTasks(task)) {
            System.out.println("Add task successful");
        } else {
            System.err.println("Add task fail!");
        }
    }

    @Override
    public void showAllTask() {
        List<Task> taskList = new ArrayList<>();
        taskList = taskDAO.findAllTask();
        if (!taskList.isEmpty()) {
            System.out.println("List to do:");
            taskList.forEach(System.out::println);
        } else {
            System.out.println("No task!");
        }
    }

    @Override
    public void updateTaskStatus(Scanner scanner) {
        System.out.print("Enter task ID to update: ");
        String taskId = scanner.nextLine();
        if (Validation.isPositiveInteger(taskId)) {
            Task taskUpdate = taskDAO.findTaskById(Integer.parseInt(taskId));
            if (taskUpdate != null) {
                taskUpdate.setStatus(taskUpdate.inputStatus(scanner));
                if (taskDAO.updateTaskStatus(taskUpdate)) {
                    System.out.println("Task Status update successful");
                } else {
                    System.err.println("Task status update failed!");
                }
            } else {
                System.err.println("This task ID not exist!");
            }
        } else {
            System.err.println("Enter a positive integer!");
        }
    }

    @Override
    public void deleteTask(Scanner scanner) {
        System.out.print("Enter task ID to delte: ");
        String taskId = scanner.nextLine();
        if (Validation.isPositiveInteger(taskId)) {
            Task taskDelete = taskDAO.findTaskById(Integer.parseInt(taskId));
            if (taskDelete != null) {
                if (taskDAO.deleteTask(taskDelete)) {
                    System.out.println("Task Status delete successful");
                } else {
                    System.out.println("Task Status delete failed!");
                }
            } else {
                System.err.println("This task ID not exist!");
            }
        } else {
            System.err.println("Enter a positive integer!");
        }
    }

    @Override
    public void searchTaskByName(Scanner scanner) {
        System.out.print("Enter task name to search: ");
        String taskName = scanner.nextLine();
        if (Validation.isNotEmpty(taskName)) {
            List<Task> taskList = taskDAO.findTaskByName(taskName);
            if (!taskList.isEmpty()) {
                System.out.printf("List Task contains '%s': ", taskName);
                taskList.forEach(System.out::println);
            }
        } else {
            System.err.println("This field can not empty!");
        }
    }
    @Override
    public void statisticTaskByStatus() {
        Map<Boolean, Long> grouped = taskDAO.findAllTask().stream()
                .collect(Collectors.groupingBy(Task::isStatus, Collectors.counting()));

        grouped.forEach((status, count) -> {
            System.out.printf("%s: %d\n", status, count);
        });
    }
}
