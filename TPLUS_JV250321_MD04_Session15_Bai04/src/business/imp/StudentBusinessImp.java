package business.imp;

import business.StudentBusiness;
import dao.StudentDAO;
import dao.imp.StudentDAOImp;
import entity.Student;
import validator.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class StudentBusinessImp implements StudentBusiness {
    private final StudentDAO studentDAO;

    public StudentBusinessImp() {
        studentDAO = new StudentDAOImp();
    }

    @Override
    public void findAll() {
        List<Student> studentList = studentDAO.findAll();
        studentList.forEach(System.out::println);
    }

    @Override
    public void createStudent(Scanner scanner) {
        Student newStudent = new Student();
        newStudent.inputData(scanner);
        boolean result = studentDAO.addStudent(newStudent);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.err.println("Có lỗi trong quá trình thêm mới sinh viên");
        }
    }

    @Override
    public void updateStudent(Scanner scanner) {
        System.out.print("Nhập mã sinh viên muốn cập nhật: ");
        String studentId = scanner.nextLine();
        if (Validation.isPositiveInteger(studentId)) {
            Student student = studentDAO.findStudentById(Integer.parseInt(studentId));
            if (student != null) {
                boolean notExist = true;
                do {
                    System.out.println("1. Cập nhật tên sinh viên");
                    System.out.println("2. Cập nhật ngày tháng năm sinh");
                    System.out.println("3. Cập nhật email");
                    System.out.println("4. Thoát");
                    System.out.print("Lựa chọn: ");
                    String choice = scanner.nextLine();
                    if (Validation.isIntegerInRange(choice, 1, 4)) {
                        switch (Integer.parseInt(choice)) {
                            case 1:
                                System.out.print("Nhập tên mới của sinh viên: ");
                                student.setStudentName(scanner.nextLine());
                                break;
                            case 2:
                                System.out.print("Nhập ngày tháng năm sinh mới: ");
                                student.setDob(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                break;
                            case 3:
                                System.out.print("Nhập email mới: ");
                                student.setEmail(scanner.nextLine());
                                break;
                            default:
                                notExist = false;
                        }

                        boolean result = studentDAO.updateStudent(student);
                        if (result) {
                            System.out.println("Cập nhật thành công");
                        } else {
                            System.err.println("Có lỗi trong quá trình cập nhật");
                        }
                    } else {
                        System.err.println("Nhập số nguyên từ 1-4");
                    }
                } while (notExist);
            } else {
                System.err.println("Không tồn tại sinh viên này!");
            }
        } else {
            System.err.println("Mã sinh viên không hợp lệ!");
        }
    }

    @Override
    public void deleteStudent(Scanner scanner) {
        System.out.print("Nhập mã sinh viên muốn xóa: ");
        String studentId = scanner.nextLine();
        if (Validation.isPositiveInteger(studentId)) {
            Student student = studentDAO.findStudentById(Integer.parseInt(studentId));
            if (student != null) {
                boolean result = studentDAO.deleteStudent(Integer.parseInt(studentId));
                if (result) {
                    System.out.println("Xóa thành công!");
                } else {
                    System.err.println("Có lỗi trong quá trình xóa sinh viên.");
                }
            } else {
                System.err.println("Không tồn tại sinh viên này!");
            }
        } else {
            System.err.println("Mã nhập vào không hợp lệ!");
        }
    }

    @Override
    public void searchStudentByName(Scanner scanner) {
        System.out.print("Nhập vào tên sinh viên muốn tìm: ");
        String studentName = scanner.nextLine();
        if (!Validation.isEmpty(studentName)) {
            List<Student> studentList = studentDAO.searchStudentByName(studentName);
            if (!studentList.isEmpty()) {
                studentList.forEach(System.out::println);
            } else {
                System.err.println("Không tìm thấy sinh viên có tên " + studentName);
            }
        } else {
            System.err.println("Không được để trống!");
        }
    }
}
