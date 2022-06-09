/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Statement;
import model.Quyen;

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
public class PhanQuyenDAOImpl implements PhanQuyenDAO {


    
    
    @Override
    public List<Quyen> getListQuyen() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM quyen";
            List<Quyen> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Quyen phanQuyen = new Quyen();
                phanQuyen.setChucVu(rs.getString("chucvu"));                
                phanQuyen.setTenloaiquanly(rs.getString("tenloaiquanly"));                
                phanQuyen.setThem(rs.getInt(1));
                phanQuyen.setXoa(rs.getInt(2));

                phanQuyen.setSua(rs.getInt(3));
                phanQuyen.setXem(rs.getInt(4));

                list.add(phanQuyen);
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
    public Quyen getQuyen(String quyen,String tenloaiquanly) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM quyen where chucvu = '"+ quyen +"' AND tenloaiquanly='"+tenloaiquanly+"'" ;
            PreparedStatement ps = cons.prepareCall(sql);
            ps.setString(1, quyen);
            ps.setString(2, tenloaiquanly);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Quyen phanQuyen = new Quyen();
                phanQuyen.setChucVu(rs.getString("chucvu"));                
                phanQuyen.setTenloaiquanly(rs.getString("tenloaiquanly"));
                phanQuyen.setThem(rs.getInt("them"));
                phanQuyen.setXoa(rs.getInt("xoa"));
                phanQuyen.setSua(rs.getInt("sua"));
                phanQuyen.setXem(rs.getInt("xem"));
                
                return phanQuyen;
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
        PhanQuyenDAO phanQuyen = new PhanQuyenDAOImpl();
//        System.out.println(phanQuyen.getQuyen(14));
    }
    
  @Override
    public boolean suaQuyen(Quyen phanQuyen) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "UPDATE quyen SET Thêm = ?, Xóa = ?,Sửa=?,Xem=? where chucvu=? and tenloaiquanly=?";
            PreparedStatement pre = cons.prepareCall(sql);
            
            pre.setInt(1, phanQuyen.getThem());
            pre.setInt(2, phanQuyen.getXoa());
            pre.setInt(3, phanQuyen.getSua());
            pre.setInt(4, phanQuyen.getXem());
                    pre.setString(5, phanQuyen.getChucVu());
                    pre.setString(6, phanQuyen.getTenloaiquanly());
            return pre.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    
}

