package entity;

import dao.StudentDAO;
import dao.imp.StudentDAOImp;
import validator.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Student {
    private int studentId;
    private String studentName;
    private LocalDate dob;
    private String email;

    private final StudentDAO studentDAO;

    public Student() {
        studentDAO = new StudentDAOImp();
    }

    public Student(int studentId, String studentName, LocalDate dob, String email) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.dob = dob;
        this.email = email;
        studentDAO = new StudentDAOImp();
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Name: %s - Date of Birth: %s - Email: %s",
                this.studentId, this.studentName, this.dob, this.email);
    }

    public void inputData(Scanner scanner) {
        this.studentName = inputName(scanner);
        this.dob = inputDob(scanner);
        this.email = inputEmail(scanner);
    }

    public String inputName(Scanner scanner) {
        final int NAME_MAX_LENGTH = 100;
        do {
            System.out.print("Nhập vào tên sinh viên: ");
            String name = scanner.nextLine();
            if (!Validation.isEmpty(name)) {
                if (name.length() <= NAME_MAX_LENGTH) {
                    return name;
                } else {
                    System.err.println("Tên có độ dài tối đa 100 ký tự");
                }
            } else {
                System.err.println("Không được để trống");
            }
        } while (true);
    }

    public LocalDate inputDob(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        do {
            System.out.print("Nhập ngày tháng năm sinh (yyyy-MM-dd): ");
            String date = scanner.nextLine();
            if (Validation.isValidDate(date, "yyyy-MM-dd")) {
                return LocalDate.parse(date, formatter);
            } else {
                System.err.println("Không đúng định dạng ngày tháng");
            }
        } while (true);
    }

    public String inputEmail(Scanner scanner) {
        final int EMAIL_MAX_LENGTH = 100;
        String emailRegex = "^[A-Za-z0-9.]+@[A-Za-z0-9.-]+$";
        do {
            System.out.print("Nhập vào email: ");
            String email = scanner.nextLine();
            if (!Validation.isEmpty(email)) {
                if (email.length() <= EMAIL_MAX_LENGTH && email.matches(emailRegex)) {
                    if (!studentDAO.isEmailExist(email)) {
                        return email;
                    } else {
                        System.err.println("Email đã tồn tại, vui lòng nhập lại!");
                    }
                } else {
                    System.err.println("Email không hợp lệ!");
                }
            } else {
                System.err.println("Không được để trống");
            }
        } while (true);
    }

}
