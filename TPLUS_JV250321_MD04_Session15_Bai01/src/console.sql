CREATE SCHEMA btvn_1;
USE btvn_1;

-- Tạo bảng Students
CREATE TABLE Students (
                          student_id INT AUTO_INCREMENT PRIMARY KEY,
                          full_name VARCHAR(100) NOT NULL,
                          date_of_birth DATE NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE
);

-- Tạo Stored Procedure
-- lấy tất cả vs
DELIMITER &&
CREATE PROCEDURE get_all_students()
BEGIN
SELECT * FROM Students;
END &&
DELIMITER ;

-- thêm sv mới
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

-- cập nhật sv
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

-- tìm sv theo id
DELIMITER &&
CREATE PROCEDURE find_student_by_id(
    IN in_id INT
)
BEGIN
SELECT * FROM Students WHERE student_id = in_id;
END &&
DELIMITER ;

-- xóa sv
DELIMITER &&
CREATE PROCEDURE delete_student(
    IN in_id INT
)
BEGIN
DELETE FROM Students WHERE student_id = in_id;
END &&
DELIMITER ;

-- lấy danh sách sv
CALL get_all_students();
-- thêm sv
CALL add_student('Nguyễn Văn B', '2002-06-15', 'vanb@example.com');
-- cập nhật sv
CALL update_student(1, 'Nguyễn Văn A Updated', '2001-05-21', 'vana_updated@example.com');
-- tìm sv id
CALL find_student_by_id(1);
-- xóa sv
CALL delete_student(2);