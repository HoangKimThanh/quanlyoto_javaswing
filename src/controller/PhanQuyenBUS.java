package controller;

import dao.PhanQuyenDAOImpl;
import model.Quyen;
import MyCustom.MyDialog;

import java.util.ArrayList;

public class PhanQuyenBUS {

    public static Quyen quyenTK = null;
    private PhanQuyenDAOImpl phanQuyenDAO = new PhanQuyenDAOImpl();
    private ArrayList<Quyen> listPhanQuyen = null;

    public void docDanhSachQuyen() {
        this.listPhanQuyen = (ArrayList<Quyen>) phanQuyenDAO.getListQuyen();
    }

    public void kiemTraQuyen(String chucVu,String tenLoaiquanly) {
        quyenTK = phanQuyenDAO.getQuyen(chucVu,tenLoaiquanly);
    }

    public ArrayList<Quyen> getListQuyen() {
        if (listPhanQuyen == null)
            docDanhSachQuyen();
        return this.listPhanQuyen;
    }

    public boolean suaQuyen(String tenQuyen, String tenloaiquanly, int them, int sua, int xoa, int xem) {
        Quyen phanQuyen = new Quyen(tenQuyen, tenloaiquanly, them, sua, xoa, xem);
        boolean flag = phanQuyenDAO.suaQuyen(phanQuyen);
        if (flag) {
            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
        } else {
            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
        }
        return flag;
    }

   

    private boolean kiemTonTaiTraQuyen(String tenQuyen) {
        docDanhSachQuyen();
        for (Quyen q : listPhanQuyen) {
            if (q.getChucVu().equalsIgnoreCase(tenQuyen))
                return true;
        }
        return false;
    }

   
}
