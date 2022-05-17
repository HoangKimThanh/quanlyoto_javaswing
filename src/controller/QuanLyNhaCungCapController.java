/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.NhaCungCapDAO;
import dao.NhaCungCapDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.NhaCungCap;
import utility.ClassTableModel;

/**
 *
 * @author Home
 */
public class QuanLyNhaCungCapController {
    private JPanel jPnView;
    private JButton jBtnAdd;
    
    private JTextField jTfMaNcc;    
    private JTextField jTfTen;

    private JTextField jTfSDT;
    private JTextField jTfDiaChi;
    
    private NhaCungCap nhaCungCap = null;
    private NhaCungCapDAO nhaCungCapDAO = null;
    
    private String[] listColumn = {"STT", "Mã NCC", "Tên NCC", "Số điện thoại", "Địa chỉ"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyNhaCungCapController(JPanel jPnView, JButton jBtnAdd, JTextField jTfMaNcc, JTextField jTfTen, JTextField jTfSDT, JTextField jTfDiaChi) {
        this.jPnView = jPnView;
        this.jBtnAdd = jBtnAdd;
        this.jTfTen = jTfTen;        
        this.jTfMaNcc = jTfMaNcc;

        this.jTfSDT = jTfSDT;
        this.jTfDiaChi = jTfDiaChi;
        
        this.nhaCungCapDAO = new NhaCungCapDAOImpl();
        this.nhaCungCap = new NhaCungCap();
    }
    
    public void setDataToTable() {
        List<NhaCungCap> listItem = nhaCungCapDAO.getList();
        
        DefaultTableModel model = new ClassTableModel().setTableNhaCungCap(listItem, listColumn);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        
        table.setRowSorter(rowSorter);
        
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertColumnIndexToModel(selectedRowIndex);
                    
                    NhaCungCap nhaCungCap = new NhaCungCap();
                    int mancc = (int) (model.getValueAt(selectedRowIndex, 1));
                    int sdt = (int) (model.getValueAt(selectedRowIndex, 3));
                    nhaCungCap.setMancc(mancc);
                    nhaCungCap.setTenncc(model.getValueAt(selectedRowIndex, 2).toString());
                    nhaCungCap.setDienthoai(sdt);
                    nhaCungCap.setDiachi(model.getValueAt(selectedRowIndex, 4).toString());
                    
//                    System.out.println(nhaCungCap.getMacc());
                    
                    jTfMaNcc.setText((nhaCungCap.getMancc()) + "");
                    jTfTen.setText(nhaCungCap.getTenncc());
                    jTfSDT.setText(nhaCungCap.getDienthoai() + "");
                    jTfDiaChi.setText(nhaCungCap.getDiachi());
                }
            }
            
        });
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(30);
        table.validate();
        table.repaint();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));
        
        jPnView.removeAll();
        jPnView.setLayout(new BorderLayout());
        jPnView.add(scrollPane);
        jPnView.validate();
        jPnView.repaint();
    }

//    public void setEvent() {
//        jBtnAdd.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    if (jTfHoTen.getText() == null && !jTfHoTen.getText().equalsIgnoreCase("")) {
//                        System.out.println(jTfHoTen.getText());
//                        JOptionPane.showMessageDialog(null, "Vui long nhap day du thong tin");
//                    } else {
//                        khachHang.setMaKhachHang(jTfMaKhachHang.getText().trim());
//                        khachHang.setHoTen(jTfHoTen.getText().trim());
//                        khachHang.setDienThoai(jTfDienThoai.getText().trim());
//                        khachHang.setDiaChi(jTaDiaChi.getText().trim());
//                        
//                        System.out.println(khachHang);
//                
//                        String lastId = khachHangDAO.createOrUpdate(khachHang);
//                        if (!lastId.equals("")) {
//                            khachHang.setMaKhachHang(lastId);
//                            JOptionPane.showMessageDialog(null, "Xử lý cập nhật dữ liệu thành công");
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
//                        }
//                    }
//                }
//                catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, ex.toString());
//                }
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {}
//
//            @Override
//            public void mouseReleased(MouseEvent e) {}
//
//            @Override
//            public void mouseEntered(MouseEvent e) {}
//
//            @Override
//            public void mouseExited(MouseEvent e) {}
//            
//        });
//    }
}
