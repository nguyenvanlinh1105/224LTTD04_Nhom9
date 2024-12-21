use `db_App`;
CREATE TABLE ThongTinThanhVien(
	maSinhVien varchar(30),
    hoTen nvarchar(255) not null,
    lop nvarchar(255) not null,
    gioiTinh boolean not null,
    ngaySinh date not null,
    sdt char(10) not null,
    CONSTRAINT `PK_THONGTINTHANHVIEN` PRIMARY KEY (`maSinhVien`)
);

CREATE TABLE SanPham(
	idSanPham varchar(30),
    idDanhMuc varchar(30) not null,
    tenSanPham longtext not null,
    trangThai varchar(15) not null,
    giaTien decimal(15,2) not null,
    images longtext,
    moTa longtext,
    soLuongDaBan int not null,
    donViTinh varchar(15) null,
    ngayTao timestamp null,
	deleted BOOLEAN NOT NULL DEFAULT FALSE,
    primary key (idSanPham)
);

CREATE TABLE DanhMuc(
	idDanhMuc varchar(30),
    tenDanhMuc nvarchar(255) not null,
    moTa longtext null,
	ngayTao date null,
     deleted BOOLEAN NOT NULL DEFAULT FALSE,
    primary key (idDanhMuc)
);


insert into ThongTinThanhVien(maSinhVien,hoTen,lop,gioiTinh,ngaySinh,sdt)
values ('22115053122225','Nguyễn Văn Linh ','124LTTD04',1,'2004-05-11','0945233655'),
	   ('22115053122302','Nguyễn Ngọc Tú Anh ','124LTTD04',0,'2004-02-12','0935393466'),
        ('22115053122117','Phạm Lê Văn Huy', '124LTTD04',1,'2004-05-01','0935393499');
select * from ThongTinThanhVien;