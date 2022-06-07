package dao;

import java.sql.Connection;
import model.Quyen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PhanQuyenDAO {

    public ArrayList<Quyen> getListQuyen() {
//        System.out.println("1");
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM quyen";
            Statement st = cons.createStatement();
            ResultSet rs = st.executeQuery(sql);
//            System.out.println(rs);
            ArrayList<Quyen> dspq = new ArrayList<>();
            while (rs.next()) {
                
//                System.out.println(rs.getString(1));
                Quyen phanQuyen = new Quyen();
//                                System.out.println(rs.getString(1));
                phanQuyen.setChucVu(rs.getString(1));
                phanQuyen.setQuyen(rs.getString(2));
                phanQuyen.setNhapHang(rs.getInt(3));
                phanQuyen.setQlSanPham(rs.getInt(4));
                phanQuyen.setQlNhanVien(rs.getInt(5));
                phanQuyen.setQlKhachHang(rs.getInt(6));
                phanQuyen.setThongKe(rs.getInt(7));
//                                System.out.println(rs.getString(2));
                dspq.add(phanQuyen);
            }
            return dspq;
        } catch (Exception e) {
        }
        return null;
    }

    public Quyen getQuyen(String quyen) {
        try {
                        Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM quyen WHERE quyen='" + quyen + "'";
            Statement st;
            st = cons.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                Quyen phanQuyen = new Quyen();
                phanQuyen.setChucVu(quyen);
                phanQuyen.setQuyen(quyen);
                phanQuyen.setNhapHang(rs.getInt(2));
                phanQuyen.setQlSanPham(rs.getInt(3));
                phanQuyen.setQlNhanVien(rs.getInt(4));
                phanQuyen.setQlKhachHang(rs.getInt(5));
                phanQuyen.setThongKe(rs.getInt(6));
                return phanQuyen;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean suaQuyen(Quyen phanQuyen) {
        try {
                        Connection cons = DBConnection.getConnection();
            String sql = "UPDATE quyen SET NhapHang=?,QLSanPham=?,QLNhanVien=?,QLKhachHang=?,ThongKe=? WHERE quyen=?";
            PreparedStatement pre;
            pre = cons.prepareStatement(sql);
            pre.setInt(1, phanQuyen.getNhapHang());
            pre.setInt(2, phanQuyen.getQlSanPham());
            pre.setInt(3, phanQuyen.getQlNhanVien());
            pre.setInt(4, phanQuyen.getQlKhachHang());
            pre.setInt(5, phanQuyen.getThongKe());
            pre.setString(6, phanQuyen.getQuyen());
            pre.setString(7, phanQuyen.getChucVu());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean themQuyen(Quyen phanQuyen) {
        try {
                        Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO quyen VALUES (?,?,?,?,?,?)";
            PreparedStatement pre;
            pre = cons.prepareStatement(sql);
            pre.setString(1, phanQuyen.getChucVu());
            pre.setString(1, phanQuyen.getQuyen());
            pre.setInt(2, phanQuyen.getNhapHang());
            pre.setInt(3, phanQuyen.getQlSanPham());
            pre.setInt(4, phanQuyen.getQlNhanVien());
            pre.setInt(5, phanQuyen.getQlKhachHang());
            pre.setInt(6, phanQuyen.getThongKe());
            return pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean xoaQuyen(String phanQuyen) {
        try {
                        Connection cons = DBConnection.getConnection();
            String sql1 = "UPDATE nhanvien SET quyen='Default' WHERE quyen='" + phanQuyen + "'";
            Statement st1;
            st1 =cons.createStatement();
            st1.executeUpdate(sql1);
            String sql = "DELETE FROM quyen WHERE quyen='" + phanQuyen + "'";
            Statement st;
            st = cons.createStatement();
            return st.executeUpdate(sql) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
