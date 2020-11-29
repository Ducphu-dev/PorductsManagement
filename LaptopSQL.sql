CREATE TABLE Laptop (
    ProductID nchar(10),
    ProductName nchar(50),
	ProductScreenSize int,
	ProductScreenPixel nchar(50),
	ProductScreenHertz int,
	ProductWeight nchar(50),
	ProductWidth int,
	ProductHeight int,
	ProductThickness int,
	ProductOS nchar(50),
	ProductCPU nchar(100),
	ProductRam nchar(50),
	ProductGPU nchar(50),
	ProductStorage nchar(50),
	ProductWifi nchar(50),
	ProductBaterry nchar(50),
	ProductConnect nchar(50)
);

insert into Laptop values ('L101','ASUS TUF FX505DY',15,'1080 x 1920px',144,'2,2 Kg',360,262,25,'Window 10 HOME','Ryzen 5 - 3550H 2.10 GHz boot clock 3.50GHz 4 cores 8 threads','8GB DDR4 2666MZ','GTX 1650 TI 4GB','128 SSB M.2 MVMe 2500MB/s','Wi-Fi 5 (802.11 ac)','6 Cell 48WHrs','2x USB 2.0, 1x USB 3.0, 1x Type C USB 3.1, HDMI');
insert into Laptop values ('L102','Dell XPS 15',15,'1080 x 1920px',60,'1.2 Kg',360,262,25,'Window 10 HOME','Core I5-10500H 3.50GHz boot clock 3,80 GHz 6 core 10 threads','16GB DDR4 2666MZ','GTX 1650 4GB','256 SSB M.2 MVMe 2500MB/s','Wi-Fi 6 (802.11 ac)','10 Cell 65WHrs','2x USB 2.0, 1x USB 3.0, 1x Type C USB 3.1, HDMI');

select * from Laptop 
