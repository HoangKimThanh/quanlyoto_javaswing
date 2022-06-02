/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.ChiTietPhieuNhap;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class ChiTietPhieuNhapDAOImpl implements ChiTietPhieuNhapDAO {

    
    public static void main(String[] args) {
    }
    
    @Override
    public boolean create(ChiTietPhieuNhap ctPhieNhap) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO ctpn(mapn, masp, soluong, dongia, thanhtien) VALUES(?,?,?,?, ?)";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setInt(1, ctPhieNhap.getMaPhieuNhap());
            prep.setInt(2, ctPhieNhap.getMaSanPham());
            prep.setInt(3, ctPhieNhap.getSoLuong());            
            prep.setInt(4, ctPhieNhap.getDonGia());
            prep.setInt(5, ctPhieNhap.getThanhTien());
            
            int result = prep.executeUpdate();   
            if (result == 1) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
   
    
}
