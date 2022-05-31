/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.SanPham;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class SanPhamDAOImpl implements SanPhamDAO{

    @Override
    public List<SanPham> getList() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM sanpham";
            List<SanPham> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSanPham(rs.getInt("masp"));
                sanPham.setLoai(rs.getString("loai"));
                sanPham.setTen(rs.getString("ten"));
                sanPham.setSoLuong(rs.getInt("soluong"));
                sanPham.setGia(rs.getInt("gia"));
                sanPham.setHanBaoHanh(rs.getInt("hanbaohanh"));
                sanPham.setAnh(rs.getString("anh"));
                list.add(sanPham);
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
        SanPhamDAO sanPham = new SanPhamDAOImpl();
        
        System.out.println(sanPham.getList());
    }

    @Override
    public int createSanPham(SanPham sanPham) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO sanpham(loai, ten, soluong, gia, hanbaohanh, anh) VALUES(?, ?, ?, ?, ?, ?) ";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, sanPham.getLoai());
            ps.setString(2, sanPham.getTen());
            ps.setInt(3, sanPham.getSoLuong());
            ps.setInt(4, sanPham.getGia());
            ps.setInt(5, sanPham.getHanBaoHanh());
            ps.setString(6, sanPham.getAnh());
            
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            ps.close();
            cons.close();
            return generatedKey;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public int updateSanPham(SanPham sanPham) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "UPDATE sanpham "
                    + "SET loai = ?, "
                    + "ten = ?, "
                    + "soluong = ?, "
                    + "gia = ?, "
                    + "hanbaohanh = ?, "
                    + "anh = ? "
                    + "WHERE masp = " + sanPham.getMaSanPham()+ "";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1, sanPham.getLoai());
            ps.setString(2, sanPham.getTen());
            ps.setInt(3, sanPham.getSoLuong());
            ps.setInt(4, sanPham.getGia());
            ps.setInt(5, sanPham.getHanBaoHanh());
            ps.setString(6, sanPham.getAnh());
            
            int result = ps.executeUpdate();    
            if (result == 1) return 1;
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    public int deleteSanPham(int maSP) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "DELETE FROM sanpham "
                    + "WHERE masp = " + maSP;
            PreparedStatement ps = cons.prepareStatement(sql);
            
            int result = ps.executeUpdate();    
            if (result == 1) return 1;
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}
