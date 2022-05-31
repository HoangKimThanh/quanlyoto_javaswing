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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton jBtnUpdate;
    private JButton jBtnDelete;
    private JButton jBtnReset;
    
    private JTextField jTfMaKhachHang;
    private JTextField jTfHoTen;
    private JTextField jTfDienThoai;
    private JTextArea jTaDiaChi;
    
    private KhachHang khachHang = null;
    private KhachHangDAO khachHangDAO = null;
    
    private String[] listColumn = {"STT", "Mã KH", "Họ tên", "Số điện thoại", "Địa chỉ"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyKhachHangController(JPanel jPnView, JTextField jTfSearch, JButton jBtnAdd, JButton jBtnUpdate, 
            JButton jBtnDelete, JButton jBtnReset, JTextField jTfMaKhachHang, JTextField jTfHoTen, JTextField jTfDienThoai, JTextArea jTaDiaChi) {
        this.jPnView = jPnView;
        this.jTfSearch = jTfSearch;
        this.jBtnAdd = jBtnAdd;
        this.jBtnUpdate = jBtnUpdate;
        this.jBtnDelete = jBtnDelete;
        this.jBtnReset = jBtnReset;
        this.jTfMaKhachHang = jTfMaKhachHang;
        this.jTfHoTen = jTfHoTen;
        this.jTfDienThoai = jTfDienThoai;
        this.jTaDiaChi = jTaDiaChi;
        
        this.khachHangDAO = new KhachHangDAOImpl();
        this.khachHang = new KhachHang();
    }
    
    public void setDataToTable() {
        jTfMaKhachHang.setVisible(false);
        jBtnAdd.setEnabled(true);
        jBtnUpdate.setEnabled(false);
        jBtnDelete.setEnabled(false);
        jBtnReset.setEnabled(false);
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
        
        table.getColumnModel().getColumn(1).setMaxWidth(0);
        table.getColumnModel().getColumn(1).setMinWidth(0);
        table.getColumnModel().getColumn(1).setPreferredWidth(0);
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
//                    selectedRowIndex = table.convertColumnIndexToModel(selectedRowIndex);
                    
                    KhachHang khachHang = new KhachHang();
                    khachHang.setMaKhachHang((int) model.getValueAt(selectedRowIndex, 1));
                    khachHang.setHoTen(model.getValueAt(selectedRowIndex, 2).toString());
                    khachHang.setDienThoai(model.getValueAt(selectedRowIndex, 3).toString());
                    khachHang.setDiaChi(model.getValueAt(selectedRowIndex, 4).toString());
                    
                    jTfMaKhachHang.setText(Integer.toString(khachHang.getMaKhachHang()));
                    jTfHoTen.setText(khachHang.getHoTen());
                    jTfDienThoai.setText(khachHang.getDienThoai());
                    jTaDiaChi.setText(khachHang.getDiaChi());
                    
                    jBtnAdd.setEnabled(false);
                    jBtnUpdate.setEnabled(true);
                    jBtnDelete.setEnabled(true);
                    jBtnReset.setEnabled(true);
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
        jBtnAdd.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) {
                try {
                    if (jTfHoTen.getText() == null && !jTfHoTen.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    } else {
                        khachHang.setHoTen(jTfHoTen.getText().trim());
                        khachHang.setDienThoai(jTfDienThoai.getText().trim());
                        khachHang.setDiaChi(jTaDiaChi.getText().trim());
                
                        int lastId = khachHangDAO.createKhachHang(khachHang);
                        if (lastId != 0) {
                            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công");
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }
                    jTfMaKhachHang.setText("");
                    jTfHoTen.setText("");
                    jTfDienThoai.setText("");
                    jTaDiaChi.setText("");
                    setDataToTable();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        jBtnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (jTfHoTen.getText() == null && !jTfHoTen.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    } else {
                        khachHang.setMaKhachHang(Integer.parseInt(jTfMaKhachHang.getText().trim()));
                        khachHang.setHoTen(jTfHoTen.getText().trim());
                        khachHang.setDienThoai(jTfDienThoai.getText().trim());
                        khachHang.setDiaChi(jTaDiaChi.getText().trim());
                
//                        int lastId = khachHangDAO.updateKhachHang(khachHang);                        
                        khachHangDAO.updateKhachHang(khachHang);
                        
                        JOptionPane.showMessageDialog(null, "Xử lý cập nhật dữ liệu thành công");
//                        if (lastId != 0) {
//                            JOptionPane.showMessageDialog(null, "Xử lý cập nhật dữ liệu thành công");
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
//                        }
                        jTfMaKhachHang.setText("");
                        jTfHoTen.setText("");
                        jTfDienThoai.setText("");
                        jTaDiaChi.setText("");
                        setDataToTable();
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        jBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (jTfHoTen.getText() == null && !jTfHoTen.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
                    } else {
                        int makh = Integer.parseInt(jTfMaKhachHang.getText().trim());                   
                        khachHangDAO.deleteKhachHang(makh);
                        
                        JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công");
                        jTfMaKhachHang.setText("");
                        jTfHoTen.setText("");
                        jTfDienThoai.setText("");
                        jTaDiaChi.setText("");
                        setDataToTable();
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        });
        
        jBtnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTfMaKhachHang.setText("");
                jTfHoTen.setText("");
                jTfDienThoai.setText("");
                jTaDiaChi.setText("");
                setDataToTable();
            }
        });
        
    }
}
