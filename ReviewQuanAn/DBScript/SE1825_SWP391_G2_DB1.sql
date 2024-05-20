USE [master]
GO

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'SE1825_SWP391_G2_DB1')
BEGIN
	ALTER DATABASE SE1825_SWP391_G2_DB1 SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE SE1825_SWP391_G2_DB1 SET ONLINE;
	DROP DATABASE SE1825_SWP391_G2_DB1;
END

GO

CREATE DATABASE SE1825_SWP391_G2_DB1
GO

USE SE1825_SWP391_G2_DB1
GO

CREATE TABLE [role] (
	role_id int primary key,
	role_name nvarchar(50) not null,
)

CREATE TABLE [user] (
	id int IDENTITY(1,1) primary key,
	[name] nvarchar(50) not null,
	account nvarchar(50) not null,
	[password] nvarchar(50) not null,
	[role] int Foreign key references [role](role_id),
	[address] nvarchar(50),
	[phone] nvarchar(11),
)

CREATE TABLE store(
	store_id int IDENTITY(1,1) primary key,
	store_name nvarchar(50) not null,
	store_image nvarchar(50),
)

CREATE TABLE menu(
	id int IDENTITY(1,1) primary key,
	store_id int Foreign key references store(store_id),
	menu_image nvarchar(50),
)

CREATE TABLE comment(
	customer_id int foreign key references [user](id),
	store_id int foreign key references store(store_id),
	content nvarchar(255),
	[comment_date] date,
	rating int,
	CONSTRAINT comment_id PRIMARY KEY (customer_id, store_id),
)

INSERT INTO [role] (role_id, role_name) VALUES
(1, 'Admin'),
(2, 'Manager'),
(3, 'Employee'),
(4, 'Customer');

-- user table
INSERT INTO [user] ([name], account, [password], [role], [address], [phone]) VALUES
('John Doe', 'john@example.com', 'password123', 1, '123 Main St', '555-1234'),
('Jane Smith', 'jane@example.com', 'qwerty', 2, '456 Elm St', '555-5678'),
('Alice Johnson', 'alice@example.com', 'abc123', 3, '789 Oak St', '555-9012'),
('Bob Brown', 'bob@example.com', 'pass123', 4, '101 Pine St', '555-3456');


INSERT INTO store (store_name, store_image) VALUES
('SuperMart', 'https://example.com/supermart.jpg'),
('FreshGrocer', 'https://example.com/freshgrocer.jpg'),
('MegaMarket', 'https://example.com/megamarket.jpg');

-- comment table
INSERT INTO comment (customer_id, store_id, content, [comment_date]) VALUES
(4, 1, 'Great store, love the selection!', '2024-05-15'),
(4, 2, 'Clean and organized, will definitely come back!', '2024-05-17'),
(3, 1, 'Could use more variety in produce.', '2024-05-18');
-- menu table
INSERT INTO menu (store_id, menu_image) VALUES
(1, 'https://example.com/supermart_menu.jpg'),
(2, 'https://example.com/freshgrocer_menu.jpg'),
(3, 'https://example.com/megamarket_menu.jpg');