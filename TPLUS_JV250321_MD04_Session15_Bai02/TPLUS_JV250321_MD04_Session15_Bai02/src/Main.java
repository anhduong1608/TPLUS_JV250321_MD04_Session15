import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Sửa sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên");
            System.out.println("6. Thoát");
            System.out.print("lựa chon của bạn ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    dao.getAllStudents();
                    break;
                case 2:
                    dao.addStudent();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}

