package controller;

import dao.PhanQuyenDAO;
import model.Quyen;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class PhanQuyenBUS {

    public static Quyen quyenTK = null;
    private PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();
    private ArrayList<Quyen> listPhanQuyen = null;

    public void docDanhSachQuyen() {
        this.listPhanQuyen = phanQuyenDAO.getListQuyen();
    }

    public void kiemTraQuyen(String quyen) {
        quyenTK = phanQuyenDAO.getQuyen(quyen);
    }

    public ArrayList<Quyen> getListQuyen() {
        if (listPhanQuyen == null)
            docDanhSachQuyen();
        return this.listPhanQuyen;
    }

    public boolean suaQuyen(String tenChucVu,String tenQuyen, int nhapHang, int sanPham, int nhanVien, int khachHang, int thongKe) {
        Quyen phanQuyen = new Quyen(tenChucVu, tenQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe);
        boolean flag = phanQuyenDAO.suaQuyen(phanQuyen);
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    public boolean themQuyen(String tenChucVu,String tenQuyen) {
        if (tenQuyen == null || tenQuyen.trim().equals("")) {
            return false;
        }

        if (kiemTonTaiTraQuyen(tenQuyen)) {
            new MyDialog("Thêm thất bại! Quyền đã tồn tại", MyDialog.ERROR_DIALOG);
            return false;
        }

        Quyen phanQuyen = new Quyen(tenChucVu,tenQuyen, 0, 0, 0, 0, 0);
        boolean flag = phanQuyenDAO.themQuyen(phanQuyen);
        if (flag) {
            new MyDialog("Thêm thành công! Hãy hiệu chỉnh quyền", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Thêm thất bại! Quyền đã tồn tại", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

    private boolean kiemTonTaiTraQuyen(String tenQuyen) {
        docDanhSachQuyen();
        for (Quyen q : listPhanQuyen) {
            if (q.getQuyen().equalsIgnoreCase(tenQuyen))
                return true;
        }
        return false;
    }

    public boolean xoaQuyen(String tenQuyen) {
        boolean flag = phanQuyenDAO.xoaQuyen(tenQuyen);
        if (flag) {
            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }
}
