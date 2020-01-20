CREATE TABLE IF NOT EXISTS users (
  user_username VARCHAR(50) NOT NULL,
  user_password VARCHAR(60) NOT NULL,
  user_email VARCHAR(60) NOT NULL,
  user_enabled BOOLEAN NOT NULL DEFAULT TRUE,
  CONSTRAINT users_pkey PRIMARY KEY (user_username));
  
CREATE TABLE IF NOT EXISTS authorities (
  user_username VARCHAR(50) NOT NULL,
  authority varchar(30) NOT NULL,
CONSTRAINT fk_authorities_users FOREIGN KEY (user_username) REFERENCES users (user_username));

CREATE TABLE IF NOT EXISTS todo_table (
	todo_id INT AUTO_INCREMENT PRIMARY KEY,
    todo_description VARCHAR(60),
    todo_user VARCHAR(60),
    todo_date DATE,
    todo_status BOOLEAN
);