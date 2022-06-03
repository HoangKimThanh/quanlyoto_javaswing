/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.PhieuNhap;

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
public class PhieuNhapDAOImpl implements PhieuNhapDAO {

    
    public static void main(String[] args) {
    }
    
    @Override
    public int create(PhieuNhap phieNhap) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO phieunhap(mancc, manv, ngaylap, tongtien) VALUES(?,?,?,?)";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setInt(1, phieNhap.getMaNhaCungCap());
            prep.setInt(2, phieNhap.getMaNhanVien());
            prep.setString(3, phieNhap.getNgayLap());            
            prep.setInt(4, phieNhap.getTongTien());

            prep.executeUpdate(); 
            ResultSet rs = prep.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            
            return generatedKey;
        } catch (SQLException ex) {
            System.out.println(ex);
            return 0;
        }
    }
   
    
}
