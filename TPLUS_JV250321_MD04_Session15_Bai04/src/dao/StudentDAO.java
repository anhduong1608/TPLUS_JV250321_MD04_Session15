package dao;

import entity.Student;
import java.util.List;

public interface StudentDAO {
    List<Student> findAll();

    boolean isEmailExist(String email);

    boolean addStudent(Student student);

    Student findStudentById(int id);

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);

    List<Student> searchStudentByName(String name);
}
