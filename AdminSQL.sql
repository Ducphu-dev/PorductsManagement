﻿/****** Script for SelectTopNRows command from SSMS  ******/
CREATE TABLE AccountAdmin (
    AdminID varchar(10),
    AdminName varchar(50),
	AdminPassword varchar(30),
	AdminDate date,
	Position varchar(10),
);

insert into AccountAdmin values (13,'huudung','123',GETDATE(),'Quan ly');
insert into AccountAdmin values (15,'ducphu','1234',GETDATE(),'Quan ly');

select * from AccountAdmin
