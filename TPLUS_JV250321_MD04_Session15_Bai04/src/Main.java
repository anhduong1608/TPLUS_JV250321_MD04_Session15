import business.StudentBusiness;
import business.imp.StudentBusinessImp;
import validator.Validation;

import java.util.Scanner;

public class Main {
    private final StudentBusiness studentBusiness;

    public Main() {
        studentBusiness = new StudentBusinessImp();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();

        do {
            System.out.println("\n************ MENU ************");
            System.out.println(
                    "1. Hiển thị danh sách sinh viên.\n" +
                            "2. Thêm mới sinh viên.\n" +
                            "3. Sửa sinh viên.\n" +
                            "4. Xóa sinh viên.\n" +
                            "5. Tìm kiếm sinh viên.\n" +
                            "6. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validation.isIntegerInRange(choice, 1, 6)) {
                switch (Integer.parseInt(choice)) {
                    case 1 -> main.studentBusiness.findAll();
                    case 2 -> main.studentBusiness.createStudent(scanner);
                    case 3 -> main.studentBusiness.updateStudent(scanner);
                    case 4 -> main.studentBusiness.deleteStudent(scanner);
                    case 5 -> main.studentBusiness.searchStudentByName(scanner);
                    default -> System.exit(0);
                }
            } else {
                System.err.println("Nhập số nguyên từ 1-6");
            }
        } while (true);

    }
}