CREATE TABLE AccountUser (
    UserID varchar(10),
    UserName nchar(50),
	UserPassword nchar(25),
	UserEmail varchar(50),
	UserDate date,
	UserAddress varchar(70),
	UserPhoneNumber int,
	UserGender varchar(10),
	UserOrder varchar(20),
);

insert into AccountUser values ('101' ,'Nguyen Duc Phu','1234','ducphu.170720@gmail.com',GETDATE(),'93 Cao Thang',0903758384,'Nam','Da gui');
insert into AccountUser values ('102' ,'Nguyen Huu Dung','123','dung.nh0035@hsc.edu.vn',GETDATE(),'93 Cao Thang',0903758384,'Nam','Da thanh toan');

select * from AccountUser
