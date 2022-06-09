/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Statement;
import model.NhanVien;

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
public class NhanVienDAOImpl implements NhanVienDAO {

    @Override
    public List<NhanVien> getList() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM nhanvien";
            List<NhanVien> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getInt("manv"));                
                nhanVien.setHoTen(rs.getString("hoten"));                
                
                nhanVien.setTaiKhoan(rs.getString("taikhoan"));
                nhanVien.setMatKhau(rs.getString("matkhau"));
                nhanVien.setChucVu(rs.getString("chucvu"));
                list.add(nhanVien);
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
    
    @Override
    public NhanVien getByMaNV(int maNV) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM nhanvien where manv = ?";
            PreparedStatement ps = cons.prepareCall(sql);
            ps.setInt(1, maNV);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getInt("manv"));                
                nhanVien.setHoTen(rs.getString("hoten"));
                
                nhanVien.setTaiKhoan(rs.getString("taikhoan"));
                nhanVien.setMatKhau(rs.getString("matkhau"));
                nhanVien.setChucVu(rs.getString("chucvu"));
                
                return nhanVien;
            }
            rs.close();
            ps.close();
            cons.close();

            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        NhanVienDAO nhanVien = new NhanVienDAOImpl();
        System.out.println(nhanVien.getByMaNV(14));
    }
    
    @Override
    public boolean create(NhanVien nhanVien) {
        try {
            Connection cons = DBConnection.getConnection();
             Statement stmt1 = (Statement) cons.createStatement();
             Statement stmt2 = (Statement) cons.createStatement();
             stmt1.executeUpdate("SET FOREIGN_KEY_CHECKS=0");
            String sql =  " INSERT INTO nhanvien(hoten,taikhoan,matkhau,chucvu) VALUES(?,?,?,?)";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setString(1, nhanVien.getHoTen());
           
            prep.setString(2, nhanVien.getTaiKhoan());
            prep.setString(3, nhanVien.getMatKhau());
             prep.setString(4, nhanVien.getChucVu());
            int result = prep.executeUpdate();   
              stmt2.executeUpdate("SET FOREIGN_KEY_CHECKS=1");
            prep.close();
            cons.close();
            if (result == 1) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    @Override
    public boolean update(NhanVien nhanVien) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "UPDATE nhanvien SET hoten = ?, chucvu = ? where manv = ?";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setString(1, nhanVien.getHoTen());
            prep.setString(2, nhanVien.getChucVu());
            
            prep.setInt(4, nhanVien.getMaNV());
            int result = prep.executeUpdate();  
            prep.close();
            cons.close();
            if (result == 1) return true;
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    @Override
    public boolean delete(NhanVien nhanVien) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "DELETE FROM nhanvien where manv = ?";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setInt(1, nhanVien.getMaNV());
            int result = prep.executeUpdate();  
            prep.close();
            cons.close();
            if (result == 1) return true;
            return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    
}
