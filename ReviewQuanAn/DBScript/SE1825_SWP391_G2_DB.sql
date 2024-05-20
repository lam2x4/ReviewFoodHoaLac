USE [master]
GO

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'SE1825_SWP391_G2_DB')
BEGIN
	ALTER DATABASE SE1825_SWP391_G2_DB SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE SE1825_SWP391_G2_DB SET ONLINE;
	DROP DATABASE SE1825_SWP391_G2_DB;
END

GO

CREATE DATABASE SE1825_SWP391_G2_DB
GO

USE SE1825_SWP391_G2_DB
GO

CREATE TABLE [admin] (
	id int PRIMARY KEY,
	account nvarchar(50) not null,
	[password] nvarchar(50) not null,
)

CREATE TABLE store (
	id int PRIMARY KEY,
	[name] nvarchar(50) not null,
	is_banned bit not null,
	average_rating float,
)

CREATE TABLE item (
	store_id int FOREIGN KEY REFERENCES store(id),
	id int PRIMARY KEY,
	[name] nvarchar(50) not null,
	rating int,
)


CREATE TABLE store_manager (
	id int PRIMARY KEY,
	[name] nvarchar(50) not null,
	account nvarchar(50) not null,
	[password] nvarchar(50) not null,
	is_banned bit not null,
	store_id int FOREIGN KEY REFERENCES store(id)
)

CREATE TABLE customer (
	id int PRIMARY KEY,
	[name] nvarchar(50),
	account nvarchar(50),
	[password] nvarchar(50),
	is_banned bit not null,
)

CREATE TABLE post(
	customer_id int FOREIGN KEY REFERENCES customer(id),
	store_id int FOREIGN KEY REFERENCES store (id),
	is_banned bit not null,
	content nvarchar(500),
)

CREATE TABLE store_rating(
	customer_id int FOREIGN KEY REFERENCES customer (id),
	store_id int FOREIGN KEY REFERENCES store (id),
	[value] int,
)

CREATE TABLE item_rating (
	customer_id int FOREIGN KEY REFERENCES customer (id),
	store_id int FOREIGN KEY REFERENCES store (id),
	item_id int FOREIGN KEY REFERENCES item(id),
	[value] int,
)

/*admin*/
INSERT INTO [admin] ([id], [account], [password]) VALUES (1 ,'admin','123456')

/*store*/
INSERT INTO [store] ([id], [name], [is_banned], [average_rating]) VALUES (1, 'Store1', 0, 0)
INSERT INTO [store] ([id], [name], [is_banned], [average_rating]) VALUES (2, 'Store2', 0, 0)
INSERT INTO [store] ([id], [name], [is_banned], [average_rating]) VALUES (3, 'Store3', 0, 0)
INSERT INTO [store] ([id], [name], [is_banned], [average_rating]) VALUES (4, 'Store4', 0, 0)
INSERT INTO [store] ([id], [name], [is_banned], [average_rating]) VALUES (5, 'Store5', 1, 0)

/*item*/
INSERT INTO [item] ([store_id], [id], [name], [rating]) VALUES (1, 1, 'Item1', 0)
INSERT INTO [item] ([store_id], [id], [name], [rating]) VALUES (1, 2, 'Item2', 0)
INSERT INTO [item] ([store_id], [id], [name], [rating]) VALUES (1, 3, 'Item3', 0)
INSERT INTO [item] ([store_id], [id], [name], [rating]) VALUES (2, 4, 'Item1', 0)
INSERT INTO [item] ([store_id], [id], [name], [rating]) VALUES (2, 5, 'Item2', 0)
INSERT INTO [item] ([store_id], [id], [name], [rating]) VALUES (3, 6, 'Item1', 0)
INSERT INTO [item] ([store_id], [id], [name], [rating]) VALUES (3, 7, 'Item2', 0)
INSERT INTO [item] ([store_id], [id], [name], [rating]) VALUES (4, 8, 'Item1', 0)
INSERT INTO [item] ([store_id], [id], [name], [rating]) VALUES (4, 9, 'Item2', 0)
INSERT INTO [item] ([store_id], [id], [name], [rating]) VALUES (4, 10, 'Item2', 0)

/*store_manager*/
INSERT INTO [store_manager] ([id] ,[name], [account], [password], [is_banned], [store_id])
     VALUES (1, 'ManagerStore1', 'ManagerStore1', '123456', 0, 1)
INSERT INTO [store_manager] ([id] ,[name], [account], [password], [is_banned], [store_id])
     VALUES (2, 'ManagerStore2', 'ManagerStore2', '123456', 0, 2)
INSERT INTO [store_manager] ([id] ,[name], [account], [password], [is_banned], [store_id])
     VALUES (3, 'ManagerStore3', 'ManagerStore3', '123456', 0, 3)
INSERT INTO [store_manager] ([id] ,[name], [account], [password], [is_banned], [store_id])
     VALUES (4, 'ManagerStore4', 'ManagerStore4', '123456', 0, 4)
INSERT INTO [store_manager] ([id] ,[name], [account], [password], [is_banned], [store_id])
     VALUES (5, 'ManagerStore5', 'ManagerStore5', '123456', 1, 5)

/*customer*/
INSERT INTO [customer] ([id], [name], [account], [password], [is_banned]) VALUES(1, 'Hoang Anh', 'User1', '123456', 0)
INSERT INTO [customer] ([id], [name], [account], [password], [is_banned]) VALUES(2, 'Lam', 'User2', '123456', 0)
INSERT INTO [customer] ([id], [name], [account], [password], [is_banned]) VALUES(3, 'Kien', 'User3', '123456', 0)
INSERT INTO [customer] ([id], [name], [account], [password], [is_banned]) VALUES(4, 'Hoang Viet', 'User4', '123456', 0)
INSERT INTO [customer] ([id], [name], [account], [password], [is_banned]) VALUES(5, 'Tuan Anh', 'User5', '123456', 0)

/*post*/
/*
INSERT INTO [post] ([id], [customer_id], [store_id], [is_banned],[content]) VALUES (1, 1, 1, 0, 'Store1 is OK')
INSERT INTO [post] ([id], [customer_id], [store_id], [is_banned],[content]) VALUES (2, 1, 2, 0, 'Store2 is OK')
INSERT INTO [post] ([id], [customer_id], [store_id], [is_banned],[content]) VALUES (1, 2, 1, 0, 'Store1 is OK')
INSERT INTO [post] ([id], [customer_id], [store_id], [is_banned],[content]) VALUES (2, 2, 2, 0, 'Store2 is OK')
INSERT INTO [post] ([id], [customer_id], [store_id], [is_banned],[content]) VALUES (1, 3, 1, 0, 'Store1 is OK')
INSERT INTO [post] ([id], [customer_id], [store_id], [is_banned],[content]) VALUES (2, 3, 2, 0, 'Store2 is OK')
INSERT INTO [post] ([id], [customer_id], [store_id], [is_banned],[content]) VALUES (1, 4, 2, 0, 'Store2 is OK')
INSERT INTO [post] ([id], [customer_id], [store_id], [is_banned],[content]) VALUES (2, 4, 3, 0, 'Store3 is OK')
INSERT INTO [post] ([id], [customer_id], [store_id], [is_banned],[content]) VALUES (3, 4, 4, 0, 'Store4 is OK')
INSERT INTO [post] ([id], [customer_id], [store_id], [is_banned],[content]) VALUES (1, 5, 5, 0, 'Store5 is OK')
*/
INSERT INTO [post] ([customer_id], [store_id], [is_banned],[content]) VALUES (1, 1, 0, 'Store1 is OK')
INSERT INTO [post] ([customer_id], [store_id], [is_banned],[content]) VALUES (1, 2, 0, 'Store2 is OK')
INSERT INTO [post] ([customer_id], [store_id], [is_banned],[content]) VALUES (2, 1, 0, 'Store1 is OK')
INSERT INTO [post] ([customer_id], [store_id], [is_banned],[content]) VALUES (2, 2, 0, 'Store2 is OK')
INSERT INTO [post] ([customer_id], [store_id], [is_banned],[content]) VALUES (3, 1, 0, 'Store1 is OK')
INSERT INTO [post] ([customer_id], [store_id], [is_banned],[content]) VALUES (3, 2, 0, 'Store2 is OK')
INSERT INTO [post] ([customer_id], [store_id], [is_banned],[content]) VALUES (4, 2, 0, 'Store2 is OK')
INSERT INTO [post] ([customer_id], [store_id], [is_banned],[content]) VALUES (4, 3, 0, 'Store3 is OK')
INSERT INTO [post] ([customer_id], [store_id], [is_banned],[content]) VALUES (4, 4, 0, 'Store4 is OK')
INSERT INTO [post] ([customer_id], [store_id], [is_banned],[content]) VALUES (5, 5, 0, 'Store5 is OK')


/*store_rating*/
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(1, 1, 4)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(1, 2, 3)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(1, 3, 4)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(1, 4, 5)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(1, 5, 4)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(2, 2, 3)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(2, 3, 2)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(2, 4, 4)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(3, 1, 1)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(3, 3, 4)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(3, 5, 2)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(4, 4, 3)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(4, 5, 5)
INSERT INTO [store_rating] ([customer_id], [store_id], [value]) VALUES(5, 3, 1)

/*item_rating*/
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (1, 1, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (1, 1, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (1, 1, 3, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (1, 2, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (1, 2, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (1, 3, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (1, 3, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (1, 4, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (1, 4, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (1, 4, 3, 5)

INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (2, 1, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (2, 1, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (2, 1, 3, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (2, 2, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (2, 2, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (2, 3, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (2, 3, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (2, 4, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (2, 4, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (2, 4, 3, 5)

INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (3, 1, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (3, 1, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (3, 1, 3, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (3, 2, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (3, 2, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (3, 3, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (3, 3, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (3, 4, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (3, 4, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (3, 4, 3, 5)

INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (4, 1, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (4, 1, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (4, 1, 3, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (4, 2, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (4, 2, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (4, 3, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (4, 3, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (4, 4, 1, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (4, 4, 2, 5)
INSERT INTO [item_rating] ([customer_id], [store_id], [item_id], [value]) VALUES (4, 4, 3, 5)