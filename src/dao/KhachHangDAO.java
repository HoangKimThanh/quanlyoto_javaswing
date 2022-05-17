/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.KhachHang;

/**
 *
 * @author ASUS
 */
public interface KhachHangDAO {
    public List<KhachHang> getList();
    
    public int createKhachHang(KhachHang khachHang);
    
    public void updateKhachHang(KhachHang khachHang);
    
    public void deleteKhachHang(int makh);
}
