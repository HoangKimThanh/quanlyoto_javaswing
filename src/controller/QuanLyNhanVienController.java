/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.NhanVienDAO;
import dao.NhanVienDAOImpl;
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
import model.NhanVien;
import utility.ClassTableModel;

/**
 *
 * @author Home
 */
public class QuanLyNhanVienController {
    
    
    private final JPanel jPnView;
    private final JButton jBtnAdd;    
    private final JButton jBtnUpdate;    
    private final JButton jBtnDelete;    
    
    private final JButton jBtnReset;


    
    private final JTextField jTfSearch;
    private final JTextField jTfMaNv;    
    private final JTextField jTfHoTen;

    private final JTextField jTfChucVu;
   
    
    private NhanVien nhanVien = null;
    private NhanVienDAO nhanVienDAO = null;
    
    private final String[] listColumn = {"STT", "Mã NV", "Tên NV", "Chức vụ"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyNhanVienController(JPanel jPnView, JButton jBtnAdd, JButton jBtnUpdate, 
            JButton jBtnDelete, JButton jBtnReset, JTextField jTfMaNv, JTextField jTfHoTen, 
            JTextField jTfChucVu, JTextField jTfSearch) {
        this.jPnView = jPnView;
        this.jBtnAdd = jBtnAdd;
        this.jBtnUpdate = jBtnUpdate;
        this.jBtnDelete = jBtnDelete;
        this.jBtnReset = jBtnReset;
        this.jTfHoTen = jTfHoTen;        
        this.jTfMaNv = jTfMaNv;

      
        this.jTfChucVu = jTfChucVu;
        this.jTfSearch = jTfSearch;
        
        this.nhanVienDAO = new NhanVienDAOImpl();
        this.nhanVien = new NhanVien();
       
    }
    
    public void setDataToTable() {
        
        jTfMaNv.setText("");
        jTfHoTen.setText("");        
        jTfChucVu.setText("");
       
        
        jBtnAdd.setEnabled(true);
        jBtnUpdate.setEnabled(false);
        jBtnDelete.setEnabled(false);
        jBtnReset.setEnabled(false);
        
         
        List<NhanVien> listItem = nhanVienDAO.getList();
        
        DefaultTableModel model = new ClassTableModel().setTableNhanVien(listItem, listColumn);
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
                    
                    NhanVien nhanVien = new NhanVien();
                    int manv = (int) (model.getValueAt(selectedRowIndex, 1));
                    nhanVien.setMaNV(manv);
                    nhanVien.setHoTen(model.getValueAt(selectedRowIndex, 2).toString());
                    nhanVien.setChucVu(model.getValueAt(selectedRowIndex, 3).toString());
                  
                    
//                    System.out.println(nhanVien.getMacc());
                    
                    jTfMaNv.setText((nhanVien.getMaNV()) + "");
                    jTfHoTen.setText(nhanVien.getHoTen());
                    
                    jTfChucVu.setText(nhanVien.getChucVu());
                    
                    jBtnAdd.setEnabled(false);
                    jBtnUpdate.setEnabled(true);
                    jBtnDelete.setEnabled(true);
                    jBtnReset.setEnabled(true);
                }
            }
            
        });
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(30);
        table.validate();
        table.repaint();
//        JScrollPane scrollPane = new JScrollPane();
        JScrollPane scrollPane = new JScrollPane(null, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 280));
        
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
                    String tennv = jTfHoTen.getText();
                    
                    String chucVu = jTfChucVu.getText();
                    if (tennv.equals("")  || chucVu.equals("")) {
                        JOptionPane.showMessageDialog(null, "Ten NV,  ChucVu khong duoc de trong!");
                    } else {
                        nhanVien.setHoTen(tennv.trim());
                        nhanVien.setChucVu(chucVu.trim());
                        
                        
//                        System.out.println(nhanVien);
                
                        boolean result = nhanVienDAO.create(nhanVien);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công");
                            
                            setDataToTable();
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
        
        jBtnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String manv = jTfMaNv.getText();
                    String tennv = jTfHoTen.getText();
                    
                    String chucVu = jTfChucVu.getText();
                    if (manv.equals("") || tennv.equals("")|| chucVu.equals("")) {
                        JOptionPane.showMessageDialog(null, "Ma NV, Ten NV, chuc vu khong duoc de trong!");
                    } else {
                        nhanVien.setMaNV(Integer.parseInt(manv));
                        nhanVien.setHoTen(tennv.trim());
                        
                        nhanVien.setChucVu(chucVu.trim());
                        
                        boolean result = nhanVienDAO.update(nhanVien);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công");
                            setDataToTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });
        
        jBtnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String manv = jTfMaNv.getText();
                    String tennv = jTfHoTen.getText();
                   
                    String chucVu = jTfChucVu.getText();
                    if (manv.equals("")) {
                        JOptionPane.showMessageDialog(null, "Vui long chon NV!");
                    } else {
                        nhanVien.setMaNV(Integer.parseInt(manv));
                        nhanVien.setHoTen(tennv.trim());
                        nhanVien.setChucVu(chucVu.trim());
                        
                        
//                        System.out.println(nhaCungCap);
                
                        boolean result = nhanVienDAO.delete(nhanVien);
                        if (result) {
                            JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công");
                            setDataToTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
                        }
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });
        
        jBtnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setDataToTable();
            }

        });
    }
}

