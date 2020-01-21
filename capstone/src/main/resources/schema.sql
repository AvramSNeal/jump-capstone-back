CREATE TABLE IF NOT EXISTS todo_table (
	todo_id INT AUTO_INCREMENT PRIMARY KEY,
    todo_description VARCHAR(60),
    todo_user VARCHAR(60),
    todo_date DATE,
    todo_status BOOLEAN
);