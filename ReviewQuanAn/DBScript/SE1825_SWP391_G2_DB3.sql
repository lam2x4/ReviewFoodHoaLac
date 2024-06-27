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
	is_banned int not null,
	author_id int foreign key references [user](id),

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

CREATE TABLE BlogLikes(
	[user_id] int,
	blog_id int,
	interaction_type varchar(10),
	primary key ([user_id], blog_id),
	foreign key ([user_id]) references [User](id),
	foreign key (blog_id) references Blog(id)
)

CREATE TABLE CommentLikes(
	blog_id int,
	[user_id] int,
	comment_id int,
	interaction_type varchar(10),
	primary key (blog_id, [user_id], comment_id),
	foreign key (blog_id) references Blog(id),
	foreign key ([user_id]) references [User](id),
	foreign key (comment_id) references Comment(id)
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
VALUES ('anhtthe182190', '$2a$10$4gdBX6nPOX8rgNHKGzcQjOPgy9zDXQ4I9UboWPd.wy.Ii.SDys2DO', 'anhtthe182190@fpt.edu.vn', '0123456789', 'photo_7_2024-06-06_11-09-40.jpg', 1, 'Tuan Anh', '15/6/2024', 1, 2, 0),
       ('anhlhhe186102', '$2a$10$iLg.r2pLXmkHhAPWObgbVuSujjYz5KV1qA4vDOonrVfRnVCKniAQi', 'anhlhhe186102@fpt.edu.vn', '0223456789', 'photo_5_2024-06-06_11-09-40.jpg', 1, 'Hoang Anh','15/6/2024', 1, 2, 0),
       ('kienvthe186151', '$2a$10$3CiZMivn3fu0mx6WaOuNZ.ieW4B1nP7eQuiZ8yjMp2u7AFK18vAK2', 'kienvthe186151@fpt.edu.vn', '0323456789', 'photo_6_2024-06-06_11-09-40.jpg', 1, 'Kien', '15/6/2024', 1, 2, 0),
	   ('vietthhe186188', '$2a$10$9mczDJvMzyaFZ0Bf1i5e8.7gYZb/j6ROTUxJaEfUq48zgR8D1ub7.', 'vietthhe186188@fpt.edu.vn', '0423456789', 'photo_1_2024-06-06_11-09-40.jpg', 1, 'Viet', '15/6/2024', 1, 2, 0),
       ('lamtbhe186252', '$2a$10$ET0PtpYZn/nsX.XiBqdHueBJsZdAGAy5U6kqqRONk.zr00u0d4uCu', 'lamtbhe186252@fpt.edu.vn', '0523456789', 'photo_3_2024-06-06_11-09-40.jpg', 1, 'Lam', '15/6/2024', 1, 2, 0);

INSERT INTO Approve_Name(id, [name])
VALUES (0, 'Waiting'),
	   (1, 'Approved'),
	   (2, 'Reject');

-- Insert data into the Blog table
INSERT INTO Blog ([user_id], title, content, create_date, likes, is_approved, is_banned, author_id)
VALUES (1, 'Review of Cafeteria 1', 'The food is 6/10', '15/06/2024', 10, 1, 0, 2),
       (2, 'Review of Cafeteria 2', 'The food is 7/10', '15/06/2024', 15, 1, 0, 2),
       (3, 'Review of Cafeteria 1', 'The food is 10/10', '15/06/2024', 20, 1, 0, 3),
       (4, 'Review of Cafeteria 2', 'The food is 9/10', '15/06/2024', 8, 1, 0, 4),
	   (5, 'Review of Cafeteria 3', 'The food is 11/10', '15/06/2024', 8, 1, 0, 5);

-- Insert data into the Images table
INSERT INTO Images (blog_id, link)
VALUES (1, '1.jpg'),
       (1, '2.jpg'),
       (1, '3.jpg'),
	   (2, '4.jpg'),
       (2, '5.png'),
	   (3, '5.png'),
	   (3, '5.png'),
	   (4, '5.png'),
	   (4, '5.png'),
	   (5, '5.png');
-- Insert data into the Comment table
INSERT INTO Comment ([user_id], blog_id, content, create_date, likes, is_banned)
VALUES (1, 1, 'Tuan Anh comment', '15/06/2024', 5, 0),
       (2, 2, 'Hoang Anh comment', '15/06/2024', 3, 0),
       (3, 3, 'Kien comment', '15/06/2024', 7, 0),
       (4, 4, 'Viet comment', '15/06/2024', 12, 0),
       (5, 5, 'Lam comment', '15/06/2024', 6, 0);
