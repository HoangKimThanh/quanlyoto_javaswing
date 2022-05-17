/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;

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
}
