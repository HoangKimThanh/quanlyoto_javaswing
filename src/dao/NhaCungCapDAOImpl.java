/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.NhaCungCap;

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
public class NhaCungCapDAOImpl implements NhaCungCapDAO {

    @Override
    public List<NhaCungCap> getList() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT * FROM nhacungcap";
            List<NhaCungCap> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                NhaCungCap nhaCungCap = new NhaCungCap();
                nhaCungCap.setMancc(rs.getInt("mancc"));                
                nhaCungCap.setTenncc(rs.getString("tenncc"));
                nhaCungCap.setDiachi(rs.getString("diachi"));
                nhaCungCap.setDienthoai(rs.getInt("dienthoai"));
                list.add(nhaCungCap);
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
        NhaCungCapDAO nhaCungCap = new NhaCungCapDAOImpl();
        System.out.println(nhaCungCap.getList());
    }
    
    
    
}
