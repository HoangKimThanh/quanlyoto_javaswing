/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LoaiSanPham;

/**
 *
 * @author Home
 */
public class LoaiSanPhamDAOImpl implements LoaiSanPhamDAO {
    @Override
    public List<LoaiSanPham> getList() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM loaisp";
            List<LoaiSanPham> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                LoaiSanPham loaiSanPham = new LoaiSanPham();
                loaiSanPham.setMaloaisp(rs.getInt("maloaisp"));                
                loaiSanPham.setTenloaisp(rs.getString("tenloaisp"));
                list.add(loaiSanPham);
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
    public boolean create(LoaiSanPham loaiSanPham) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "INSERT INTO loaisp(tenloaisp) VALUES(?)";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setString(1, loaiSanPham.getTenloaisp());
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
    
    @Override
    public boolean update(LoaiSanPham loaiSanPham) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "UPDATE loaisp SET tenloaisp = ? where maloaisp = ?";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setString(1, loaiSanPham.getTenloaisp());
            prep.setInt(2, loaiSanPham.getMaloaisp());
            int result = prep.executeUpdate();    
            if (result == 1) return true;
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    @Override
    public boolean delete(LoaiSanPham loaiSanPham) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "DELETE FROM loaisp where maloaisp = ?";
            PreparedStatement prep = cons.prepareCall(sql);
            prep.setInt(1, loaiSanPham.getMaloaisp());
            int result = prep.executeUpdate();    
            if (result == 1) return true;
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
}
