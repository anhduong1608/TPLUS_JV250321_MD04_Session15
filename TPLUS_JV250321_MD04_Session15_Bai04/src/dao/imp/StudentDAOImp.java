package dao.imp;

import dao.StudentDAO;
import entity.Student;
import utli.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO {

    @Override
    public List<Student> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> studentList = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_students}");
            ResultSet resultSet = callSt.executeQuery();
            studentList = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (resultSet.next()) {
                Student newStudent = new Student();
                newStudent.setStudentId(resultSet.getInt("student_id"));
                newStudent.setStudentName(resultSet.getString("full_name"));
                newStudent.setDob(LocalDate.parse(resultSet.getString("date_of_birth"), formatter));
                newStudent.setEmail(resultSet.getString("email"));

                studentList.add(newStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return studentList;
    }

    @Override
    public boolean isEmailExist(String email) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call check_email_exist(?, ?)}");
            callSt.setString(1, email);
            callSt.registerOutParameter(2, Types.BOOLEAN);
            callSt.execute();
            boolean isExist = callSt.getBoolean(2);
            if (isExist) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean addStudent(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();

            callSt = conn.prepareCall("{call add_student(?, ?, ?)}");
            callSt.setString(1, student.getStudentName());
            callSt.setDate(2, java.sql.Date.valueOf(student.getDob()));
            callSt.setString(3, student.getEmail());

            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Student findStudentById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_student_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet resultSet = callSt.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setStudentName(resultSet.getString("full_name"));
                student.setDob(LocalDate.parse(resultSet.getString("date_of_birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                student.setEmail(resultSet.getString("email"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return student;
    }

    @Override
    public boolean updateStudent(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_student(?, ?, ?, ?)}");
            callSt.setInt(1, student.getStudentId());
            callSt.setString(2, student.getStudentName());
            callSt.setDate(3, java.sql.Date.valueOf(student.getDob()));
            callSt.setString(4, student.getEmail());
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_student(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Student> searchStudentByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> studentList = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call search_student_by_name(?)}");
            callSt.setString(1, name);
            ResultSet resultSet = callSt.executeQuery();
            studentList = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setStudentName(resultSet.getString("full_name"));
                student.setDob(LocalDate.parse(resultSet.getString("date_of_birth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                student.setEmail(resultSet.getString("email"));
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return studentList;
    }
}
