/****** Script for SelectTopNRows command from SSMS  ******/
CREATE TABLE AccountAdmin (
    AdminID varchar(10),
    AdminName varchar(50),
	AdminPassword varchar(30),
	AdminDate date,
	Position varchar(10),
);

  select * from AccountAdmin

insert into AccountAdmin values (13,'huudung','123',GETDATE(),'Quan ly');
insert into AccountAdmin values (15,'ducphu','1234',GETDATE(),'Quan ly');

UPDATE AccountAdmin SET AdminName = 'huudung', AdminPassword = '123' where AdminID = 13;

DELETE FROM AccountUser WHERE UserName = 'ducphhu';