/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.KhachHangDAO;
import dao.KhachHangDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import model.KhachHang;
import utility.ClassTableModel;

/**
 *
 * @author ASUS
 */
public class QuanLyKhachHangController {
    private JPanel jPnView;
    private JTextField jTfSearch;
    private JButton jBtnAdd;
    
    private JTextField jTfMaKhachHang;
    private JTextField jTfHoTen;
    private JTextField jTfDienThoai;
    private JTextArea jTaDiaChi;
    
    private KhachHang khachHang = null;
    private KhachHangDAO khachHangDAO = null;
    
    private String[] listColumn = {"STT", "Mã KH", "Họ tên", "Số điện thoại", "Địa chỉ"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyKhachHangController(JPanel jPnView, JTextField jTfSearch, JButton jBtnAdd, JTextField jTfMaKhachHang, JTextField jTfHoTen, JTextField jTfDienThoai, JTextArea jTaDiaChi) {
        this.jPnView = jPnView;
        this.jTfSearch = jTfSearch;
        this.jBtnAdd = jBtnAdd;
        this.jTfMaKhachHang = jTfMaKhachHang;
        this.jTfHoTen = jTfHoTen;
        this.jTfDienThoai = jTfDienThoai;
        this.jTaDiaChi = jTaDiaChi;
        
        this.khachHangDAO = new KhachHangDAOImpl();
        this.khachHang = new KhachHang();
    }
    
    public void setDataToTable() {
        List<KhachHang> listItem = khachHangDAO.getList();
        
        DefaultTableModel model = new ClassTableModel().setTableKhachHang(listItem, listColumn);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        
        table.setRowSorter(rowSorter);
        
        jTfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jTfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jTfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertColumnIndexToModel(selectedRowIndex);
                    
                    KhachHang khachHang = new KhachHang();
                    khachHang.setMaKhachHang(model.getValueAt(selectedRowIndex, 1).toString());
                    khachHang.setHoTen(model.getValueAt(selectedRowIndex, 2).toString());
                    khachHang.setDienThoai(model.getValueAt(selectedRowIndex, 3).toString());
                    khachHang.setDiaChi(model.getValueAt(selectedRowIndex, 4).toString());
                    
                    jTfMaKhachHang.setText(khachHang.getMaKhachHang());
                    jTfHoTen.setText(khachHang.getHoTen());
                    jTfDienThoai.setText(khachHang.getDienThoai());
                    jTaDiaChi.setText(khachHang.getDiaChi());
                }
            }
            
        });
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
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

    public void setEvent() {
        jBtnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (jTfHoTen.getText() == null && !jTfHoTen.getText().equalsIgnoreCase("")) {
                        System.out.println(jTfHoTen.getText());
                        JOptionPane.showMessageDialog(null, "Vui long nhap day du thong tin");
                    } else {
                        khachHang.setMaKhachHang(jTfMaKhachHang.getText().trim());
                        khachHang.setHoTen(jTfHoTen.getText().trim());
                        khachHang.setDienThoai(jTfDienThoai.getText().trim());
                        khachHang.setDiaChi(jTaDiaChi.getText().trim());
                        
                        System.out.println(khachHang);
                
                        String lastId = khachHangDAO.createOrUpdate(khachHang);
                        if (!lastId.equals("")) {
                            khachHang.setMaKhachHang(lastId);
                            JOptionPane.showMessageDialog(null, "Xử lý cập nhật dữ liệu thành công");
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
            
        });
    }
}
