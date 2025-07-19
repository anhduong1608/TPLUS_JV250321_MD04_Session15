package business;

import java.util.Scanner;

public interface StudentBusiness {
    void findAll();

    void createStudent(Scanner scanner);

    void updateStudent(Scanner scanner);

    void deleteStudent(Scanner scanner);

    void searchStudentByName(Scanner scanner);

}
