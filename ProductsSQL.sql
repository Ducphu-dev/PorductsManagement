﻿/****** Script for SelectTopNRows command from SSMS  ******/
CREATE TABLE Products (
    ProductID varchar(10),
    ProductName nchar(50),
	ProductCountry nchar(20),
	ProductBrand varchar(20),
	ProductKind varchar(20),
	ProductAmount int,
	DateIn date,
	Price int,
);

insert into Products values ('L101' ,'Asus Tuf 15','USA','Asus','laptop',15,GETDATE(),799);
insert into Products values ('G102' ,'RTX 2060 ti 6GB','PhanLan','MSI','Card man hinh',20,GETDATE(),259);
insert into Products values ('G101','RTX 1660 ti 4GB','USA','Gigabyte','Card man hinh',15,GETDATE(),150);
insert into Products values ('L102' ,'Dell XPS 15','USA','Dell','laptop',15,GETDATE(),1500);

select * from Products
