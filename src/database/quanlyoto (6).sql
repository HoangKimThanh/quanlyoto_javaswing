-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 14, 2022 lúc 04:17 PM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlyoto`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cthd`
--

CREATE TABLE `cthd` (
  `mahd` int(11) NOT NULL,
  `masp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` bigint(11) NOT NULL,
  `thanhtien` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cthd`
--

INSERT INTO `cthd` (`mahd`, `masp`, `soluong`, `dongia`, `thanhtien`) VALUES
(16, 18, 1, 1100000000, 1100000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctpn`
--

CREATE TABLE `ctpn` (
  `mapn` int(11) NOT NULL,
  `masp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` bigint(11) NOT NULL,
  `thanhtien` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ctpn`
--

INSERT INTO `ctpn` (`mapn`, `masp`, `soluong`, `dongia`, `thanhtien`) VALUES
(4, 15, 1000, 1000000, 1000000000),
(5, 16, 100, 1000000, 100000000),
(6, 15, 10, 1000000, 10000000),
(6, 16, 10, 10000, 100000),
(6, 18, 10, 10000, 100000),
(6, 19, 10, 10000, 100000),
(6, 20, 10, 10000, 100000),
(6, 31, 10, 10000, 100000),
(6, 33, 10, 1000, 10000),
(7, 18, 10, 10000, 100000),
(8, 18, 12, 1000, 12000),
(8, 19, 10, 1000, 10000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `mahd` int(11) NOT NULL,
  `makh` int(5) NOT NULL,
  `manv` int(11) DEFAULT NULL,
  `ngaylap` datetime NOT NULL DEFAULT current_timestamp(),
  `tongtien` bigint(11) NOT NULL,
  `ghichu` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`mahd`, `makh`, `manv`, `ngaylap`, `tongtien`, `ghichu`) VALUES
(16, 32, 1, '2022-06-13 00:00:00', 1100000000, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` int(5) NOT NULL,
  `hoten` varchar(200) NOT NULL,
  `dienthoai` varchar(10) NOT NULL,
  `diachi` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`makh`, `hoten`, `dienthoai`, `diachi`) VALUES
(12, 'Nguyễn Văn A', '0321234567', 'Thủ Đức, TPHCM'),
(13, 'Trần Văn B', '0331234567', 'Quận 1, TPHCM'),
(15, 'Đinh Văn D', '0351234567', 'Quận 4, TPHCM'),
(16, 'Lê Văn E', '0361234567', 'Quận 5, TPHCM'),
(17, 'Lý Văn F', '0371234567', 'Quận 6, TPHCM'),
(24, 'Thanh', '0331234567', 'TPHCM'),
(32, 'Thanh', '0345678999', 'TPHCM');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `mancc` int(11) NOT NULL,
  `tenncc` varchar(200) NOT NULL,
  `diachi` varchar(200) NOT NULL,
  `dienthoai` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`mancc`, `tenncc`, `diachi`, `dienthoai`) VALUES
(2, 'Mercedes', 'UK', '1234567'),
(14, 'BMW', 'UA', '801234567'),
(15, '78978a', '00090', 'a'),
(16, 'b', 'b', 'b'),
(17, 'c', 'c', 'c'),
(19, 'Test', 'test', 'test'),
(21, 'TEST', 'TEST', 'TEST');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(11) NOT NULL,
  `hoten` varchar(100) NOT NULL,
  `taikhoan` varchar(100) NOT NULL,
  `matkhau` varchar(100) NOT NULL,
  `chucvu` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `hoten`, `taikhoan`, `matkhau`, `chucvu`) VALUES
(1, 'Admin', 'admin', 'admin', 'Quản trị'),
(3, 'Hoang Kim Thanh', 'Thanh123', 'Thanh123', 'Nhân viên'),
(4, 'Hoàng Kim Thành', 'Hkthanh123', 'Hkthanh123', 'Quản lý');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `mapn` int(11) NOT NULL,
  `mancc` int(11) NOT NULL,
  `manv` int(11) NOT NULL,
  `ngaylap` datetime NOT NULL DEFAULT current_timestamp(),
  `tongtien` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`mapn`, `mancc`, `manv`, `ngaylap`, `tongtien`) VALUES
(4, 2, 1, '2021-06-03 09:56:04', 1000000000),
(5, 2, 1, '2020-06-03 22:33:40', 100000000),
(6, 2, 1, '2022-06-09 20:56:14', 10510000),
(7, 2, 1, '2022-06-10 06:25:28', 100000),
(8, 2, 1, '2022-06-10 19:04:15', 22000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quyen`
--

CREATE TABLE `quyen` (
  `chucvu` varchar(100) NOT NULL,
  `tenloaiquanly` varchar(100) NOT NULL,
  `them` int(1) NOT NULL DEFAULT 1,
  `xem` int(1) NOT NULL DEFAULT 1,
  `sua` int(1) NOT NULL DEFAULT 1,
  `xoa` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `quyen`
--

INSERT INTO `quyen` (`chucvu`, `tenloaiquanly`, `them`, `xem`, `sua`, `xoa`) VALUES
('Nhân viên', 'Quản lý hóa đơn', 1, 1, 0, 0),
('Nhân viên', 'Quản lý khách hàng', 1, 1, 1, 1),
('Nhân viên', 'Quản lý nhà cung cấp', 0, 0, 0, 0),
('Nhân viên', 'Quản lý nhân viên', 0, 0, 0, 0),
('Nhân viên', 'Quản lý nhập hàng', 0, 0, 0, 0),
('Nhân viên', 'Quản lý sản phẩm', 0, 1, 0, 0),
('Nhân viên', 'Thống kê', 0, 0, 0, 0),
('Quản lý', 'Quản lý hóa đơn', 1, 1, 1, 1),
('Quản lý', 'Quản lý khách hàng', 1, 1, 1, 1),
('Quản lý', 'Quản lý nhà cung cấp', 0, 1, 0, 0),
('Quản lý', 'Quản lý nhân viên', 1, 1, 1, 1),
('Quản lý', 'Quản lý nhập hàng', 1, 1, 1, 1),
('Quản lý', 'Quản lý sản phẩm', 1, 1, 1, 1),
('Quản lý', 'Thống kê', 1, 1, 1, 1),
('Quản trị', 'Quản lý hóa đơn', 1, 1, 1, 1),
('Quản trị', 'Quản lý khách hàng', 1, 1, 1, 1),
('Quản trị', 'Quản lý nhà cung cấp', 1, 1, 1, 1),
('Quản trị', 'Quản lý nhân viên', 1, 1, 1, 1),
('Quản trị', 'Quản lý nhập hàng', 1, 1, 1, 1),
('Quản trị', 'Quản lý sản phẩm', 1, 1, 1, 1),
('Quản trị', 'Thống kê', 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `masp` int(11) NOT NULL,
  `loai` varchar(50) NOT NULL,
  `ten` varchar(200) NOT NULL,
  `soluong` int(11) DEFAULT NULL,
  `gia` bigint(11) NOT NULL,
  `hanbaohanh` int(11) NOT NULL,
  `anh` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`masp`, `loai`, `ten`, `soluong`, `gia`, `hanbaohanh`, `anh`) VALUES
(15, 'Ô tô', 'Lexus ES250', 10, 1900000000, 4, 'lexus-es250.png'),
(16, 'Ô tô', 'Lexus RX300', 6, 1000000000, 5, 'lexus-rx300.jpg'),
(18, 'Ô tô', 'Lexus RX350', 28, 1100000000, 3, 'lexus-rx350.png'),
(19, 'Ô tô', 'BMW X5', 16, 1200000000, 5, 'bmw-x5.jpg'),
(20, 'Ô tô', 'BMW X7', 8, 1400000000, 5, 'bmw-x7.jpg'),
(31, 'Phụ tùng', 'Mâm ép', 10, 2000000, 0, 'mam-ep.png'),
(33, 'Phụ tùng', 'Cao su chân máy trước', 10, 900000, 0, 'cao-su-chan-may-truoc.png');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `cthd`
--
ALTER TABLE `cthd`
  ADD PRIMARY KEY (`mahd`,`masp`),
  ADD KEY `masp` (`masp`);

--
-- Chỉ mục cho bảng `ctpn`
--
ALTER TABLE `ctpn`
  ADD PRIMARY KEY (`mapn`,`masp`),
  ADD KEY `masp` (`masp`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahd`),
  ADD KEY `makh` (`makh`),
  ADD KEY `manv` (`manv`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`mancc`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`),
  ADD KEY `chucvu` (`chucvu`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`mapn`),
  ADD KEY `mancc` (`mancc`),
  ADD KEY `manv` (`manv`);

--
-- Chỉ mục cho bảng `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`chucvu`,`tenloaiquanly`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masp`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `mahd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `makh` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `mancc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `mapn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `masp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `cthd`
--
ALTER TABLE `cthd`
  ADD CONSTRAINT `cthd_ibfk_1` FOREIGN KEY (`mahd`) REFERENCES `hoadon` (`mahd`),
  ADD CONSTRAINT `cthd_ibfk_2` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`);

--
-- Các ràng buộc cho bảng `ctpn`
--
ALTER TABLE `ctpn`
  ADD CONSTRAINT `ctpn_ibfk_1` FOREIGN KEY (`mapn`) REFERENCES `phieunhap` (`mapn`),
  ADD CONSTRAINT `ctpn_ibfk_2` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_3` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`),
  ADD CONSTRAINT `hoadon_ibfk_4` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`chucvu`) REFERENCES `quyen` (`chucvu`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`mancc`) REFERENCES `nhacungcap` (`mancc`),
  ADD CONSTRAINT `phieunhap_ibfk_3` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
