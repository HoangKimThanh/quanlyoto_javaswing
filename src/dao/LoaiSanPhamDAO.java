/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.LoaiSanPham;

/**
 *
 * @author Home
 */
public interface LoaiSanPhamDAO {
     public List<LoaiSanPham> getList();
     public boolean create(LoaiSanPham loaiSanPham);
     public boolean update(LoaiSanPham loaiSanPham);
     public boolean delete(LoaiSanPham loaiSanPham);
}
