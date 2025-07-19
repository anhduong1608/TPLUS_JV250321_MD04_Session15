package business;

import java.util.Scanner;

public interface TaskBusiness {
    void addTask(Scanner scanner);
    void showAllTask();
    void updateTaskStatus(Scanner scanner);
    void deleteTask(Scanner scanner);
    void searchTaskByName(Scanner scanner);
    void statisticTaskByStatus();
}
