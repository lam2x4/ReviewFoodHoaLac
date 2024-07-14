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
	create_date datetime not null,
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
	create_date datetime not null,
	likes int,
	is_approved int foreign key references Approve_Name(id),
	is_banned int foreign key references Ban_Name(id),
	[author_id] int foreign key references [user](id),
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
	create_date datetime,
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
CREATE TABLE Report_Type(
	id int identity(1,1) primary key ,
	[type_name] int,
	
)

CREATE TABLE Report(
	id int identity(1,1) primary key,
	[user_id] int foreign key references [User](id),
	[blog_id] int foreign key references Blog(id),
	[content] nvarchar(100),
	[type_id] int foreign key references Report_Type(id),
	
)

create table BookMark(
id int Identity(1,1) primary key,
[user_id] int foreign key references [user](id),
[blog_id] int foreign key references Blog(id),
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
VALUES ('anhtthe182190', '$2a$10$4gdBX6nPOX8rgNHKGzcQjOPgy9zDXQ4I9UboWPd.wy.Ii.SDys2DO', 'anhtthe182190@fpt.edu.vn', '0123456789', 'photo_7_2024-06-06_11-09-40.jpg', 1, 'Tuan Anh', '2024-06-15 18:45:30', 1, 1, 0),
       ('anhlhhe186102', '$2a$10$iLg.r2pLXmkHhAPWObgbVuSujjYz5KV1qA4vDOonrVfRnVCKniAQi', 'anhlhhe186102@fpt.edu.vn', '0223456789', 'photo_5_2024-06-06_11-09-40.jpg', 1, 'Hoang Anh','2024-06-15 18:45:30', 1, 2, 0),
       ('kienvthe186151', '$2a$10$3CiZMivn3fu0mx6WaOuNZ.ieW4B1nP7eQuiZ8yjMp2u7AFK18vAK2', 'kienvthe186151@fpt.edu.vn', '0323456789', 'photo_6_2024-06-06_11-09-40.jpg', 1, 'Kien', '2024-06-15 18:45:30', 1, 2, 0),
	   ('vietthhe186188', '$2a$10$9mczDJvMzyaFZ0Bf1i5e8.7gYZb/j6ROTUxJaEfUq48zgR8D1ub7.', 'vietthhe186188@fpt.edu.vn', '0423456789', 'photo_1_2024-06-06_11-09-40.jpg', 1, 'Viet', '2024-06-15 18:45:30', 1, 2, 0),
       ('lamtbhe186252', '$2a$10$ET0PtpYZn/nsX.XiBqdHueBJsZdAGAy5U6kqqRONk.zr00u0d4uCu', 'lamtbhe186252@fpt.edu.vn', '0523456789', 'photo_3_2024-06-06_11-09-40.jpg', 1, 'Lam', '2024-06-15 18:45:30', 1, 2, 0);

INSERT INTO Approve_Name(id, [name])
VALUES (0, 'Waiting'),
	   (1, 'Approved'),
	   (2, 'Reject');

	   SET IDENTITY_INSERT [dbo].[Blog] ON 
-- Insert data into the Blog table
INSERT [dbo].[Blog] ([id], [user_id], [title], [content], [create_date], [likes], [is_approved], [is_banned],[author_id]) VALUES (7, 5, N'Review Quán Ăn Bún Đậu Đồng Mô', N'Đây là một quán rất ngon và tuyệt vời, nhân viên nhiệt tình nhanh nhạy, bún đậu ngon, nhiều topping ','2024-06-15 11:45:30', 0, 2, 0,5)
INSERT [dbo].[Blog] ([id], [user_id], [title], [content], [create_date], [likes], [is_approved], [is_banned],[author_id]) VALUES (8, 5, N'Review Quán Kem Mixue Cây Xăng 39', N'Kem ngon,rẻ , nhưng nước uống hơi ngọt, không hợp với tôi ', '2024-06-15 11:45:30', 0, 1, 0,5)
INSERT [dbo].[Blog] ([id], [user_id], [title], [content], [create_date], [likes], [is_approved], [is_banned],[author_id]) VALUES (9, 5, N'Review Quán Ăn Bún Bò Huế Thái Anh', N'Nước dùng đậm đà, mỗi tội không có điều hòa', '2024-06-15 11:45:30', 0, 1, 0,5)
INSERT [dbo].[Blog] ([id], [user_id], [title], [content], [create_date], [likes], [is_approved], [is_banned],[author_id]) VALUES (10, 5, N'Review Quán Ăn Gà Ri Phú Bình', N'Rất ngon, đậm đà, gà rất dai và ngon', '2024-06-15 11:45:30', 0, 1, 0,5)
INSERT [dbo].[Blog] ([id], [user_id], [title], [content], [create_date], [likes], [is_approved], [is_banned],[author_id]) VALUES (11, 5, N'Review Nhà Hàng Huy Cường', N'Cực sang trọng, rất nên thử, thức ăn đa dạng phong phú, món ăn đậm đà, đặc biệt món bò xào hành tây cực cuốn', '2024-06-15 11:45:30', 0, 1, 0,5)
INSERT [dbo].[Blog] ([id], [user_id], [title], [content], [create_date], [likes], [is_approved], [is_banned],[author_id])VALUES (12, 5, N'Review Quán Ăn 1988 BBQ', N'Thịt hơi động lạnh tí, ăn không ngon, thịt rất mỏng , thái nhỏ xíu, nhân viên thái độ không tốt ', '2024-06-15 11:45:30', 0, 1, 0,5)
INSERT [dbo].[Blog] ([id], [user_id], [title], [content], [create_date], [likes], [is_approved], [is_banned],[author_id]) VALUES (13,5 ,N'Review Nhà Hãng Nguyễn Gia', N'Rất ngon , trông sang trọng , giá trung bình từ 200-300k một món, rất vừa túi với người giàu ', '2024-06-15 11:45:30', 0, 1, 0,5)
INSERT [dbo].[Blog] ([id], [user_id], [title], [content], [create_date], [likes], [is_approved], [is_banned],[author_id])VALUES (14, 5, N'Review Quán Nước Cà Phê Bao Cấp', N'Khá ổn, giá nước tầm 30-35k khá vừa giá, đặc biệt cà phê rất ngon', '2024-06-15 11:45:30', 0, 1, 0,5)
INSERT [dbo].[Blog] ([id], [user_id], [title], [content], [create_date], [likes], [is_approved], [is_banned],[author_id]) VALUES (17, 5, N'Review Quán Bún bò đồng đậu', N'Quán ăn rất ngon Nhưng hơi đắt Ủng hộ', '2024-06-15 11:45:30', 0, 1, 0,5)
   SET IDENTITY_INSERT [dbo].[Blog] off 

SET IDENTITY_INSERT [dbo].[Images] ON 
-- Insert data into the Images table
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (10, 7, N'9aa017e7-207d-4afd-8197-127bf84ddd29_mau-bill-an-uong-6_1597330928.jpg');
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (11, 7, N'fa5c1abd-6a4b-4ec0-aca6-8a8cb2eef78e_nội-thất-quán-ăn-2.jpg');
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (12, 7, N'd6898f36-7926-4a98-81aa-e4888fce80fc_thiet-ke-quan-an.jpeg');
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (13, 8, N'30ac5e9a-534e-40f3-919f-c46441d6c073_mau-bill-an-uong-6_1597330928.jpg');
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (14, 8, N'058be914-e6e6-439f-bf8b-c4d0fdbaf2d6_1670824558-franchise-mixue-20230725145847105.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (15, 9, N'af3eb0f1-8893-4a15-985a-990bb8a6eec6_mau-bill-an-uong-6_1597330928.jpg');
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (16, 9, N'a07a2f59-47e9-4bd1-b5a1-6ee6bba77de2_images (1).jpg');
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (17, 9, N'2eeb23cc-b1bc-4e6e-9ecb-0a9462651dd0_images.jpg');
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (18, 10, N'6d0ab0f0-b265-49c7-a436-f16533ac1734_mau-bill-an-uong-6_1597330928.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (19, 10, N'3217c234-899f-4246-9c1e-84f7e7566405_len1435799157.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (20, 10, N'7e4020f8-cbc8-4248-888e-a85ecc059119_nội-thất-quán-ăn-2.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (21, 10, N'3c07468f-ad7e-47aa-bdd1-451093f21ec2_thiet-ke-quan-an.jpeg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (22, 11, N'1e5dbc8c-ca70-43e9-81dd-096fe9769017_mau-bill-an-uong-6_1597330928.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (23, 11, N'e0576909-dc47-463d-8b25-70dcba5eea8f_2019-11-17.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (24, 12, N'934f88ca-b4f0-41b2-8613-893b27ff4027_mau-bill-an-uong-6_1597330928.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (25, 12, N'956ad4ba-2df1-4135-8caa-366df19d6a47_2021-10-23.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (26, 12, N'e2e8cf05-4e37-4358-904c-31dc9b09e175_2019-11-17.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (27, 13, N'3f1a9557-e860-49a1-ac41-3f248ebf6c29_mau-bill-an-uong-6_1597330928.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (28, 13, N'0b96b021-8eae-44df-8de2-c7ba4e7a8e57_IMG20221119105711.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (29, 13, N'3f0bab1e-7a69-4803-93b9-18671a82f4c5_2021-10-23.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (30, 14, N'f9f9f1e5-ab8a-4fd4-8f0e-376eaca220c1_mau-bill-an-uong-6_1597330928.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (31, 14, N'e09c1800-4bb3-4279-a4fa-05405fd62a21_2023-07-18.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (38, 17, N'f2d74e2f-b899-48d5-bfb4-b5b03d5ce5b2_mau-bill-an-uong-6_1597330928.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (39, 17, N'e45c3fff-2038-4975-b62f-0d7fa7a6f714_2023-07-18.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (40, 17, N'f05f1c20-6003-4972-9a4c-f5b160954fe7_IMG20221119105711.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (41, 17, N'6c58498e-b7b1-4a27-8596-c8ec9bceda2c_png-transparent-default-avatar-thumbnail.png')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (42, 17, N'e11c0278-76df-486a-aed9-f58f949767a9_2021-10-23.jpg')
INSERT [dbo].[Images] ([id], [blog_id], [link]) VALUES (43, 17, N'ff26210e-44bb-49f6-b8cc-bc7ee405363b_2019-11-17.jpg')
SET IDENTITY_INSERT [dbo].[Images] off 
-- Insert data into the Comment table
INSERT INTO Comment ([user_id], blog_id, content, create_date, likes, is_banned)
VALUES (1, 7, 'Tuan Anh comment', '2024-06-15 18:45:30', 5, 0),
       (2, 8, 'Hoang Anh comment', '2024-06-15 18:45:30', 3, 0),
       (3, 9, 'Kien comment', '2024-06-15 18:45:30', 7, 0),
       (4, 9, 'Viet comment', '2024-06-15 18:45:30', 12, 0),
       (5, 9, 'Lam comment', '2024-06-15 18:45:30', 6, 0);