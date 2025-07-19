import Validator.Validation;
import business.TaskBusiness;
import business.imp.TaskManagementImp;

import java.util.Scanner;

public class Main {
    private final TaskBusiness taskBusiness;

    public Main() {
        taskBusiness = new TaskManagementImp();
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("************ TO DO LIST **************");
            System.out.println("1. Show all task");
            System.out.println("2. Add new task");
            System.out.println("3. Update task status");
            System.out.println("4. Delete task");
            System.out.println("5. Search task by name");
            System.out.println("6. Statistic task by status");
            System.out.println("7. Exit");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 7)) {
                switch (Integer.parseInt(choice)) {
                    case 1 -> main.taskBusiness.showAllTask();
                    case 2 -> main.taskBusiness.addTask(scanner);
                    case 3 -> main.taskBusiness.updateTaskStatus(scanner);
                    case 4 -> main.taskBusiness.deleteTask(scanner);
                    case 5 -> main.taskBusiness.searchTaskByName(scanner);
                    case 6 -> main.taskBusiness.statisticTaskByStatus();
                    default -> System.exit(0);
                }
            } else {
                System.err.println("Enter a positive integer between 1-7!");
            }
        } while (true);
    }
}