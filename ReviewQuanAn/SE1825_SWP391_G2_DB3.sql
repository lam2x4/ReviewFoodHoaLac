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

CREATE TABLE [User](
	id int IDENTITY(1,1) primary key,
	username nvarchar(50) not null,
	[password] nvarchar(50) not null,
	email nvarchar(50) not null,
	phone nvarchar(10) not null,
	avatar nvarchar(255),
	gender int,
	[description] nvarchar(750),
	verify_status bit default 0,
	role_id int foreign key references [role](role_id),
)

CREATE TABLE Blog(
	id int Identity(1,1) primary key,
	[user_id] int foreign key references [user](id),
	title nvarchar(50) not null,
	content nvarchar(750) not null,
	likes int,
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
	likes int,
)

-- Insert data into the Role table
INSERT INTO [Role] (role_id, role_name)
VALUES (1, 'admin'),
       (2, 'user');

-- Insert data into the User table
INSERT INTO [User] (username, [password], email, phone, avatar, gender, [description], verify_status, role_id)
VALUES ('anhtthe182190', '123', 'anhtthe182190@fpt.edu.vn', '0344191620', NULL, 1, 'Yessir', 1, 1),
       ('yuuhi', '123', 'user1@example.com', '0987654321', NULL, 1, 'Description for user1', 1, 2),
       ('marine', '123', 'user2@example.com', '9876543210', NULL, 0, 'Description for user2', 1, 2);

-- Insert data into the Blog table
INSERT INTO Blog ([user_id], title, content, likes)
VALUES (1, 'Review of Cafeteria 1', 'The food is 6/10', 10),
       (1, 'Review of Cafeteria 2', 'The food is 7/10', 15),
       (2, 'Review of Cafeteria 1', 'The food is 10/10', 20),
       (2, 'Review of Cafeteria 2', 'The food is 9/10', 8);

-- Insert data into the Images table
INSERT INTO Images (blog_id, link)
VALUES (1, 'https://tiki.vn/blog/wp-content/uploads/2023/07/thumb-12-768x433.jpg'),
       (1, 'https://www.recipetineats.com/wp-content/uploads/2024/03/Crispy-pork-belly-banh-mi_3.jpg?resize=900,1125'),
       (1, 'https://c8.alamy.com/comp/FWREE7/miami-floridael-chalan-restaurant-peruvian-foodcheck-receipt-bill-FWREE7.jpg'),
	   (2, 'https://cdn.tgdd.vn/Files/2018/04/01/1078873/nau-bun-bo-hue-cuc-de-tai-nha-tu-vien-gia-vi-co-san-202109161718049940.jpg'),
       (2, 'https://d2w1ef2ao9g8r9.cloudfront.net/images/products/print-receipt-loyalty-sign-up.png');

-- Insert data into the Comment table
INSERT INTO Comment ([user_id], blog_id, content, likes)
VALUES (1, 1, 'First comment on the first blog post.', 5),
       (1, 1, 'Second comment on the first blog post.', 3),
       (1, 2, 'First comment on the second blog post.', 7),
       (1, 2, 'First comment on the third blog post.', 12),
       (2, 1, 'Second comment on the third blog post.', 6),
       (2, 2, 'First comment on the fourth blog post.', 4),
       (2, 2, 'Second comment on the fourth blog post.', 9);
