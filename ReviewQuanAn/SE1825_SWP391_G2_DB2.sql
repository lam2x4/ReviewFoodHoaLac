USE [master]
GO

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'SE1825_SWP391_G2_DB2')
BEGIN
	ALTER DATABASE SE1825_SWP391_G2_DB2 SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE SE1825_SWP391_G2_DB2 SET ONLINE;
	DROP DATABASE SE1825_SWP391_G2_DB2;
END

GO

CREATE DATABASE SE1825_SWP391_G2_DB2
GO

USE SE1825_SWP391_G2_DB2
GO

CREATE TABLE [role](
	role_id int primary key,
	role_name nvarchar(50),
)

CREATE TABLE [User](
	[user_id] int IDENTITY(1,1) primary key,
	username nvarchar(50),
	[password] nvarchar(50),
	email nvarchar(50),
	phone nvarchar(50),
	avatar nvarchar(255),
	gender bit,
	[description] nvarchar(255),
	role_id int foreign key references [role](role_id),
)

CREATE TABLE Store(
	store_id int IDENTITY(1,1) primary key,
	store_name nvarchar(50),
	store_address nvarchar(255),
	manager_id int foreign key references [User]([user_id]),
	avg_rating float,
)

CREATE TABLE Menu(
	menu_id int not null,
	store_id int foreign key references Store(store_id),
	menu_image nvarchar(255),
	CONSTRAINT menu_id PRIMARY KEY (menu_id, store_id)
)

CREATE TABLE Comment(
	[user_id] int foreign key references [User]([user_id]),
	store_id int foreign key references Store(store_id),
	content nvarchar(255),
	rating int,
	likes int,
	CONSTRAINT comment_id PRIMARY KEY ([user_id], store_id)
)

-- Inserting data into the role table
INSERT INTO [role] (role_id, role_name)
VALUES 
    (1, 'Admin'),
    (2, 'Manager'),
    (3, 'Customer');

-- Inserting data into the User table
INSERT INTO [User] (username, [password], email, phone, avatar, gender, [description], role_id)
VALUES
    ('admin', 'admin123', 'admin@example.com', '123456789', 'admin_avatar.jpg', 1, 'Administrator', 1),
    ('manager1', 'manager123', 'manager1@example.com', '987654321', 'manager1_avatar.jpg', 0, 'Store Manager', 2),
    ('customer1', 'customer123', 'customer1@example.com', '111222333', 'customer1_avatar.jpg', 1, 'Regular customer', 3);

-- Inserting data into the Store table
INSERT INTO Store (store_name, store_address, manager_id, avg_rating)
VALUES
    ('Store 1','So 1 Hoa Lac', 2, 4.5),
    ('Store 2','So 2 Hoa Lac', 2, 4.0),
    ('Store 3','So 3 Hoa Lac', 2, 3.8);

-- Inserting data into the Menu table
INSERT INTO Menu (menu_id, store_id, menu_image)
VALUES
    (1, 1, 'menu1.jpg'),
    (2, 1, 'menu2.jpg'),
    (3, 2, 'menu3.jpg');

-- Inserting data into the Comment table
INSERT INTO Comment ([user_id], store_id, content, rating, likes)
VALUES
    (3, 1, 'Great store!', 5, 10),
    (3, 2, 'Nice menu variety.', 4, 8),
    (3, 3, 'Could be better.', 3, 5);