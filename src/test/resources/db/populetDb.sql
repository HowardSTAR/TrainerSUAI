-- для тестов
DELETE FROM Users_results;
DELETE FROM Reg_inf;

INSERT INTO Reg_inf(Users_ssid,
                    Users_name,
                    Users_second_name,
                    Users_third_name,
                    Users_email,
                    Users_telephone_number,
                    Users_special_info,
                    Users_avatar,
                    Users_password)
VALUES
(1, 'User1 name', 'User1 secondname', 'User1 thirdname', 'user1@mail.ru', '911-123-45-67', 'info for user1', null, '123'),
(2, 'User2 name', 'User2 secondname', 'User2 thirdname', 'user2@gmail.ru', '921-987-65-43', 'info for user2', null, '456');

INSERT INTO Users_results (Session_id, Player_statistics, Users_ssid)
VALUES
(3, 12, 1),
(4, 32, 1),
(5, 43, 2),
(6, 56, 2),
(7, 10, 1),
(8, 95, 2),
(9, 42, 1),
(10, 35, 1),
(11, 27, 2);
