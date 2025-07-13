
CREATE DATABASE IF NOT EXISTS movie_db;
USE movie_db;


CREATE TABLE IF NOT EXISTS movies (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(255),
    director VARCHAR(255),
    release_year INT
    );

-- thêm phim
DELIMITER //
CREATE PROCEDURE add_movie(IN p_title VARCHAR(255), IN p_director VARCHAR(255), IN p_year INT)
BEGIN
INSERT INTO movies (title, director, release_year)
VALUES (p_title, p_director, p_year);
END //
DELIMITER ;

-- liệt kê phim
DELIMITER //
CREATE PROCEDURE list_movies()
BEGIN
SELECT * FROM movies;
END //
DELIMITER ;

-- cập nhật phim
DELIMITER //
CREATE PROCEDURE update_movie(
    IN p_id INT,
    IN p_title VARCHAR(255),
    IN p_director VARCHAR(255),
    IN p_year INT)
BEGIN
UPDATE movies
SET title = p_title,
    director = p_director,
    release_year = p_year
WHERE id = p_id;
END //
DELIMITER ;

-- xoá phim
DELIMITER //
CREATE PROCEDURE delete_movie(IN p_id INT)
BEGIN
DELETE FROM movies WHERE id = p_id;
END //
DELIMITER ;

-- thêm phim
CALL add_movie('Inception', 'Christopher Nolan', 2010);
-- liệt kê
CALL list_movies();
-- cập nhật
CALL update_movie(2, 'Inception', 'Christopher', 2011);
-- xóa phim
CALL delete_movie(2);