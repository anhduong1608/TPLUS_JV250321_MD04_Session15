
CREATE SCHEMA btvn_3;
USE btvn_3;

-- Tạo bảng Students
CREATE TABLE Students (
                          student_id INT AUTO_INCREMENT PRIMARY KEY,
                          full_name VARCHAR(100) NOT NULL,
                          date_of_birth DATE NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE
);

-- Tạo Stored Procedure: Lấy tất cả sinh viên
DELIMITER &&
CREATE PROCEDURE get_all_students()
BEGIN
SELECT * FROM Students;
END &&
DELIMITER ;

-- Tạo Stored Procedure: Thêm sinh viên mới
DELIMITER &&
CREATE PROCEDURE add_student(
    IN in_full_name VARCHAR(100),
    IN in_date_of_birth DATE,
    IN in_email VARCHAR(100)
)
BEGIN
INSERT INTO Students (full_name, date_of_birth, email)
VALUES (in_full_name, in_date_of_birth, in_email);
END &&
DELIMITER ;

-- Tạo Stored Procedure: Cập nhật sinh viên
DELIMITER &&
CREATE PROCEDURE update_student(
    IN in_id INT,
    IN in_full_name VARCHAR(100),
    IN in_date_of_birth DATE,
    IN in_email VARCHAR(100)
)
BEGIN
UPDATE Students
SET full_name = in_full_name,
    date_of_birth = in_date_of_birth,
    email = in_email
WHERE student_id = in_id;
END &&
DELIMITER ;

-- Tạo Stored Procedure: Tìm sinh viên theo ID
DELIMITER &&
CREATE PROCEDURE find_student_by_id(
    IN in_id INT
)
BEGIN
SELECT * FROM Students WHERE student_id = in_id;
END &&
DELIMITER ;

-- Tạo Stored Procedure: Xóa sinh viên theo ID
DELIMITER &&
CREATE PROCEDURE delete_student(
    IN in_id INT
)
BEGIN
DELETE FROM Students WHERE student_id = in_id;
END &&
DELIMITER ;

-- Gọi thử các Stored Procedure
CALL get_all_students();
CALL add_student('Nguyễn Văn B', '2002-06-15', 'vanb@example.com');
CALL update_student(1, 'Nguyễn Văn A Updated', '2001-05-21', 'vana_updated@example.com');
CALL find_student_by_id(1);
CALL delete_student(1);
