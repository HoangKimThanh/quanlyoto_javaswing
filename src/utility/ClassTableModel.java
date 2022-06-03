/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
//import model.LoaiSanPham;
import model.NhaCungCap;
import model.PhieuNhap;
import model.SanPham;
/**
 *
 * @author ASUS
 */
public class ClassTableModel {
    public DefaultTableModel setTableKhachHang(List<KhachHang> listItem, String[] listColumn) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        
        int rows = listItem.size();
        if (rows > 0) {
            for(int i = 0; i < rows; i++) {
                KhachHang khachHang = listItem.get(i);
                obj = new Object[columns];
                
                obj[0] = (i + 1);
                obj[1] = khachHang.getMaKhachHang();
                obj[2] = khachHang.getHoTen();
                obj[3] = khachHang.getDienThoai();
                obj[4] = khachHang.getDiaChi();
                
                dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
    
    public DefaultTableModel setTableNhaCungCap(List<NhaCungCap> listItem, String[] listColumn) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        
        int rows = listItem.size();
        if (rows > 0) {
            for(int i = 0; i < rows; i++) {
                NhaCungCap nhaCungCap = listItem.get(i);
                obj = new Object[columns];
                
                obj[0] = (i + 1);
                obj[1] = nhaCungCap.getMancc();
                obj[2] = nhaCungCap.getTenncc();
                obj[3] = nhaCungCap.getDienthoai();
                obj[4] = nhaCungCap.getDiachi();
                
                dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
    
    public DefaultTableModel setTableSanPham(List<SanPham> listItem, String[] listColumn) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        
        int rows = listItem.size();
        if (rows > 0) {
            for(int i = 0; i < rows; i++) {
                SanPham sanPham = listItem.get(i);
                obj = new Object[columns];
                
                obj[0] = (i + 1);
                obj[1] = sanPham.getMaSanPham();
                obj[2] = sanPham.getLoai();
                obj[3] = sanPham.getTen();
                obj[4] = sanPham.getSoLuong();
                obj[5] = sanPham.getGia();
                obj[6] = sanPham.getHanBaoHanh();
                obj[7] = sanPham.getAnh();
                
                dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
    
    public DefaultTableModel setTablePhieuNhap(List<PhieuNhap> listItem, String[] listColumn) {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        
        int rows = listItem.size();
        if (rows > 0) {
            for(int i = 0; i < rows; i++) {
                PhieuNhap phieuNhap = listItem.get(i);
                obj = new Object[columns];
                
                obj[0] = (i + 1);
                obj[1] = phieuNhap.getMaPhieuNhap();
                obj[2] = phieuNhap.getMaNhaCungCap();
                obj[3] = phieuNhap.getMaNhanVien();
                obj[4] = phieuNhap.getNgayLap();
                obj[5] = phieuNhap.getTongTien();
                
                dtm.addRow(obj);
            }
        }
        
        return dtm;
    }
}
