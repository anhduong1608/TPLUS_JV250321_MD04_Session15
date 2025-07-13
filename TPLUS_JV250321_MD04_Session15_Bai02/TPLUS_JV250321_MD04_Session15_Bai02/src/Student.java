public class Student {
    private int studentId;
    private String fullName;
    private String dateOfBirth;
    private String email;

    public Student(int studentId, String fullName, String dateOfBirth, String email) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    @Override
    public String toString() {
        return studentId + " | " + fullName + " | " + dateOfBirth + " | " + email;
    }
}

