USE [master]
GO

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'SE1825_SWP391_G2_DB3')
BEGIN
	ALTER DATABASE SE1825_SWP391_G2_DB3 SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE SE1825_SWP391_G2_DB3 SET ONLINE;
	DROP DATABASE SE1825_SWP391_G2_DB3;
END

GO

CREATE DATABASE SE1825_SWP391_G2_DB3
GO

USE SE1825_SWP391_G2_DB3
GO

CREATE TABLE [Role](
	role_id int primary key,
	role_name nvarchar(10),
)
CREATE TABLE Ban_Name(
	id int primary key,
	[name] nvarchar(10)
)
CREATE TABLE [User](
	id int IDENTITY(1,1) primary key,
	username nvarchar(50) Unique,
	[password] nvarchar(255) not null,
	email nvarchar(50) Unique,
	phone nvarchar(10) Unique,
	avatar nvarchar(255),
	gender int,
	[description] nvarchar(750),
	create_date nvarchar(20) not null,
	verify_status int not null,
	role_id int foreign key references [role](role_id),
	is_banned int foreign key references Ban_Name(id)
)

CREATE TABLE Approve_Name(
	id int primary key,
	[name] nvarchar(10)
)

CREATE TABLE Blog(
	id int Identity(1,1) primary key,
	[user_id] int foreign key references [user](id),
	title nvarchar(max) not null,
	content nvarchar(max) not null,
	create_date nvarchar(20) not null,
	likes int,
	is_approved int foreign key references Approve_Name(id),
	is_banned int not null
)

CREATE TABLE Images(
	id int Identity(1,1) primary key,
	blog_id int foreign key references blog(id),
	link nvarchar(255) not null,
)

CREATE TABLE Comment(
	id int Identity(1,1) primary key,
	[user_id] int foreign key references [User](id),
	blog_id int foreign key references Blog(id),
	content nvarchar(750),
	create_date nvarchar(20),
	likes int,
	is_banned int not null
)

CREATE TABLE Draft(
	id int Identity(1,1) primary key,
	[user_id] int foreign key references [user](id),
	title nvarchar(max) not null,
	content nvarchar(max) not null,
	create_date nvarchar(20) not null,
)


-- Insert data into the Role table
INSERT INTO [Role] (role_id, role_name)
VALUES (1, 'admin'),
       (2, 'user');

INSERT INTO Ban_Name(id, [name])
VALUES (0, 'Active'),
	   (1, 'Banned');

-- Insert data into the User table
INSERT INTO [User] (username, [password], email, phone, avatar, gender, [description], create_date, verify_status, role_id, is_banned)
VALUES ('anhtthe182190', '$2a$10$4gdBX6nPOX8rgNHKGzcQjOPgy9zDXQ4I9UboWPd.wy.Ii.SDys2DO', 'anhtthe182190@fpt.edu.vn', '0123456789', NULL, 1, 'Tuan Anh', '15/6/2024', 1, 1, 0),
       ('anhlhhe186102', '$2a$10$iLg.r2pLXmkHhAPWObgbVuSujjYz5KV1qA4vDOonrVfRnVCKniAQi', 'anhlhhe186102@fpt.edu.vn', '0223456789', NULL, 1, 'Hoang Anh','15/6/2024', 1, 1, 0),
       ('kienvthe186151', '$2a$10$3CiZMivn3fu0mx6WaOuNZ.ieW4B1nP7eQuiZ8yjMp2u7AFK18vAK2', 'kienvthe186151@fpt.edu.vn', '0323456789', NULL, 1, 'Kien', '15/6/2024', 1, 1, 0),
	   ('vietthhe186188', '$2a$10$9mczDJvMzyaFZ0Bf1i5e8.7gYZb/j6ROTUxJaEfUq48zgR8D1ub7.', 'vietthhe186188@fpt.edu.vn', '0423456789', NULL, 1, 'Viet', '15/6/2024', 1, 1, 0),
       ('lamtbhe186252', '$2a$10$ET0PtpYZn/nsX.XiBqdHueBJsZdAGAy5U6kqqRONk.zr00u0d4uCu', 'lamtbhe186252@fpt.edu.vn', '0523456789', NULL, 1, 'Lam', '15/6/2024', 1, 2, 0);

INSERT INTO Approve_Name(id, [name])
VALUES (0, 'Waiting'),
	   (1, 'Approved'),
	   (2, 'Reject');

-- Insert data into the Blog table
INSERT INTO Blog ([user_id], title, content, create_date, likes, is_approved, is_banned)
VALUES (1, 'Review of Cafeteria 1', 'The food is 6/10', '15/06/2024', 10, 0, 0),
       (2, 'Review of Cafeteria 2', 'The food is 7/10', '15/06/2024', 15, 0, 0),
       (3, 'Review of Cafeteria 1', 'The food is 10/10', '15/06/2024', 20, 0, 0),
       (4, 'Review of Cafeteria 2', 'The food is 9/10', '15/06/2024', 8, 0, 0),
	   (5, 'Review of Cafeteria 3', 'The food is 11/10', '15/06/2024', 8, 0, 0);

-- Insert data into the Images table
INSERT INTO Images (blog_id, link)
VALUES (1, 'https://tiki.vn/blog/wp-content/uploads/2023/07/thumb-12-768x433.jpg'),
       (1, 'https://www.recipetineats.com/wp-content/uploads/2024/03/Crispy-pork-belly-banh-mi_3.jpg?resize=900,1125'),
       (1, 'https://c8.alamy.com/comp/FWREE7/miami-floridael-chalan-restaurant-peruvian-foodcheck-receipt-bill-FWREE7.jpg'),
	   (2, 'https://cdn.tgdd.vn/Files/2018/04/01/1078873/nau-bun-bo-hue-cuc-de-tai-nha-tu-vien-gia-vi-co-san-202109161718049940.jpg'),
       (2, 'https://d2w1ef2ao9g8r9.cloudfront.net/images/products/print-receipt-loyalty-sign-up.png');

-- Insert data into the Comment table
INSERT INTO Comment ([user_id], blog_id, content, create_date, likes, is_banned)
VALUES (1, 1, 'Tuan Anh comment', '15/06/2024', 5, 0),
       (2, 2, 'Hoang Anh comment', '15/06/2024', 3, 0),
       (3, 3, 'Kien comment', '15/06/2024', 7, 0),
       (4, 4, 'Viet comment', '15/06/2024', 12, 0),
       (5, 5, 'Lam comment', '15/06/2024', 6, 0);

INSERT INTO Draft ([user_id], title, content, create_date)
VALUES (1, 'Tuan Anh Draft', 'Tuan Anh Sample content for the draft.', '15/06/2024'),
		(2, 'Hoang Anh Draft', 'Hoang Anh Sample content for the draft.', '15/06/2024'),
		(3, 'Kien Draft', 'Kien Sample content for the draft.', '15/06/2024'),
		(4, 'Viet Draft', 'Viet Sample content for the draft.', '15/06/2024'),
		(5, 'Lam Draft', 'Lam Sample content for the draft.', '15/06/2024');