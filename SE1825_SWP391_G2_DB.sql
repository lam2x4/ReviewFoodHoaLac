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
	id int PRIMARY KEY,
	customer_id int FOREIGN KEY REFERENCES customer(id),
	store_id int FOREIGN KEY REFERENCES store (id),
	is_banned bit not null,
	content nvarchar(500),
)

CREATE TABLE store_rating(
	cusomter_id int FOREIGN KEY REFERENCES customer (id),
	store_id int FOREIGN KEY REFERENCES store (id),
	[value] int,
)

CREATE TABLE item_rating (
	cusomter_id int FOREIGN KEY REFERENCES customer (id),
	store_id int FOREIGN KEY REFERENCES store (id),
	item_id int FOREIGN KEY REFERENCES item(id),
	[value] int,
)

