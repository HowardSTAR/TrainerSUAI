CREATE DATABASE IF NOT EXISTS SUAI_Circles;
USE SUAI_Circles;
 CREATE TABLE IF NOT EXISTS Reg_inf
(
    Users_ssid INT PRIMARY KEY AUTO_INCREMENT,
    Users_name VARCHAR(30),
    Users_second_name VARCHAR(30),
    Users_third_name VARCHAR(30),
    Users_email VARCHAR(60),
    Users_telephone_number VARCHAR(30),
    Users_special_info VARCHAR(200),
    Users_avatar BLOB,
    Users_password VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS Users_results
(
    Session_id INT PRIMARY KEY AUTO_INCREMENT,
    Player_statistics INT,
    Game_difficulty_lvl VARCHAR(20),
    Users_ssid INT,
	FOREIGN KEY (Users_ssid) REFERENCES Reg_inf (Users_ssid) ON DELETE SET NULL ON UPDATE CASCADE
     
);
