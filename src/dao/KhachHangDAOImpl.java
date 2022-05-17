/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.KhachHang;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class KhachHangDAOImpl implements KhachHangDAO{

    @Override
    public List<KhachHang> getList() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM khachhang";
            List<KhachHang> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setMaKhachHang(rs.getString("makh"));
                khachHang.setHoTen(rs.getString("hoten"));
                khachHang.setDienThoai(rs.getString("dienthoai"));
                khachHang.setDiaChi(rs.getString("diachi"));
                list.add(khachHang);
            }
            rs.close();
            ps.close();
            cons.close();

            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        KhachHangDAO khachHang = new KhachHangDAOImpl();
        
        System.out.println(khachHang.getList());
    }

    @Override
    public String createOrUpdate(KhachHang khachHang) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT MAX(makh) AS makh FROM khachhang";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            String lastMakh = "";
            while(rs.next()) {
                lastMakh = rs.getString("makh");
            }
            try {
                sql = "INSERT INTO khachhang(makh, hoten, dienthoai, diachi) VALUES(?, ?, ?, ?) "
                        + "ON DUPLICATE KEY "
                        + "UPDATE hoten = VALUES(hoten), "
                        + "dienthoai = VALUES(dienthoai), "
                        + "diachi = VALUES(diachi)";
                PreparedStatement ps2 = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                String makh = khachHang.getMaKhachHang();
                if (makh.equals("")) {
                    makh = "KH003";
                }
                ps2.setString(1, makh);
                ps2.setString(2, khachHang.getHoTen());
                ps2.setString(3, khachHang.getDienThoai());
                ps2.setString(4, khachHang.getDiaChi());
                ps2.execute();
                rs = ps2.getGeneratedKeys();
                String generatedKey = "";
                if (rs.next()) {
                    generatedKey = rs.getString(1);
                }
                ps.close();
                cons.close();
                return generatedKey;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return "";
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
