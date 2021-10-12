CREATE DATABASE IF NOT EXISTS SUAI_Circles;
USE SUAI_Circles;
 CREATE TABLE IF NOT EXISTS Reg_inf
(

    Users_ssid INT PRIMARY KEY AUTO_INCREMENT,	-- Уникальный ID пользователя --
    Users_name VARCHAR(30),			-- Имя пользователя --
    Users_second_name VARCHAR(30),		-- Фамилия пользователя --
    Users_third_name VARCHAR(30),		-- Отчество пользователя --
    Users_email VARCHAR(60),			-- Почта пользователя --
    Users_telephone_number VARCHAR(30),		-- Телефон пользователя --
    Users_special_info VARCHAR(200),		-- Информация о пользователе --
    Users_avatar BLOB,				-- Аватар пользователя --
    Users_password VARCHAR(20)			-- Пароль пользователя --
);

CREATE TABLE IF NOT EXISTS Users_results
(
    Session_id INT PRIMARY KEY AUTO_INCREMENT,
    Player_statistics INT,			-- Статистика нажатия на круги (число процентов) --
    Game_difficulty_lvl VARCHAR(20),		-- Уровень сложности игры --
    Users_ssid INT,
	FOREIGN KEY (Users_ssid) REFERENCES Reg_inf (Users_ssid) ON DELETE SET NULL ON UPDATE CASCADE

);
