import java.sql.*;
import java.util.Scanner;

public class StudentDAO {
    public void getAllStudents() {
        try (Connection conn = ConnectionDB.openConnection()) {
            CallableStatement call = conn.prepareCall("{CALL get_all_students()}");
            ResultSet rs = call.executeQuery();

            System.out.println("Danh sách sinh viên:");
            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("full_name");
                String dob = rs.getString("date_of_birth");
                String email = rs.getString("email");

                Student student = new Student(id, name, dob, email);
                System.out.println(student);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        String name, dob, email;

        try (Connection conn = ConnectionDB.openConnection()) {
            while (true) {
                try {
                    System.out.print("Nhập họ tên: ");
                    name = scanner.nextLine();

                    System.out.print("Nhập ngày sinh (YYYY-MM-DD): ");
                    dob = scanner.nextLine();

                    System.out.print("Nhập email: ");
                    email = scanner.nextLine();

                    // Gọi stored procedure
                    CallableStatement call = conn.prepareCall("{CALL add_student(?, ?, ?)}");
                    call.setString(1, name);
                    call.setString(2, dob);
                    call.setString(3, email);
                    call.execute();

                    System.out.println("Thêm sinh viên thành công!");
                    break;
                } catch (SQLException e) {
                    System.out.println("Lỗi SQL: " + e.getMessage());
                } catch (Exception ex) {
                    System.out.println("Lỗi định dạng đầu vào. Vui lòng nhập lại.");
                    System.out.println(ex.getMessage());
                    scanner.nextLine(); // clear buffer
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
        }
    }
}

