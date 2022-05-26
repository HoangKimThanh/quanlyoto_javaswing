/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LoaiSanPhamDAO;
import dao.LoaiSanPhamDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.LoaiSanPham;
import utility.ClassTableModel;

/**
 *
 * @author Home
 */
public class QuanLyLoaiSanPhamController {
    private JPanel jPnView;
    private JButton jBtnAdd;    
    private JButton jBtnUpdate;    
    private JButton jBtnDelete;
    private JButton jBtnReset;
    
//    private JTextField jTfSearch;
    private JTextField jTfMaLoaiSP;    
    private JTextField jTfTenLoaiSP;

    
    private LoaiSanPham loaiSanPham = null;
    private LoaiSanPhamDAO loaiSanPhamDAO = null;
    
    private String[] listColumn = {"STT", "Mã LOAISP", "Tên LOAISP"};
    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyLoaiSanPhamController(JPanel jPnView, JButton jBtnAdd, JButton jBtnUpdate, 
            JButton jBtnDelete, JButton jBtnReset, JTextField jTfMaLoaiSP, JTextField jTfTenLoaiSP) {
        this.jPnView = jPnView;
        this.jBtnAdd = jBtnAdd;
        this.jBtnUpdate = jBtnUpdate;
        this.jBtnDelete = jBtnDelete;
        this.jBtnReset = jBtnReset;
     
        this.jTfMaLoaiSP = jTfMaLoaiSP;
        this.jTfTenLoaiSP = jTfTenLoaiSP;
        this.loaiSanPhamDAO = new LoaiSanPhamDAOImpl();
        this.loaiSanPham = new LoaiSanPham();
    }
    
    public void setDataToTable() {
        jTfMaLoaiSP.setText("");
        jTfTenLoaiSP.setText("");
        
        jBtnAdd.setEnabled(true);
        jBtnUpdate.setEnabled(false);
        jBtnDelete.setEnabled(false);
        jBtnReset.setEnabled(false);
       
        List<LoaiSanPham> listItem = loaiSanPhamDAO.getList();
        DefaultTableModel model = new ClassTableModel().setTableLoaiSanPham(listItem, listColumn);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        
        table.setRowSorter(rowSorter);
        
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    
                    LoaiSanPham loaiSanPham = new LoaiSanPham();
                    int mancc = (int) (model.getValueAt(selectedRowIndex, 1));
                    loaiSanPham.setMaloaisp(mancc);
                    loaiSanPham.setTenloaisp(model.getValueAt(selectedRowIndex, 2).toString());
                    
//                    System.out.println(nhaCungCap.getMacc());
                    
                    jTfMaLoaiSP.setText((loaiSanPham.getMaloaisp()) + "");
                    jTfTenLoaiSP.setText(loaiSanPham.getTenloaisp());
                    
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
                    String tenloaisp = jTfTenLoaiSP.getText();
                    if (tenloaisp.equals("")) {
                        JOptionPane.showMessageDialog(null, "Ten loai san pham khong duoc de trong!");
                    } else {
                        loaiSanPham.setTenloaisp(tenloaisp.trim());
                        
                        boolean result = loaiSanPhamDAO.create(loaiSanPham);
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
                    String maloaisp = jTfMaLoaiSP.getText();
                    String tenloaisp = jTfTenLoaiSP.getText();
                    if (maloaisp.equals("") || tenloaisp.equals("")) {
                        JOptionPane.showMessageDialog(null, "Ma LoaiSP, Ten LoaiSP khong duoc de trong!");
                    } else {
                        loaiSanPham.setMaloaisp(Integer.parseInt(maloaisp));
                        loaiSanPham.setTenloaisp(tenloaisp.trim());
                        
                        boolean result = loaiSanPhamDAO.update(loaiSanPham);
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
                    String maloaisp = jTfMaLoaiSP.getText();
                    String tenloaisp = jTfTenLoaiSP.getText();
                    if (maloaisp.equals("")) {
                        JOptionPane.showMessageDialog(null, "Vui long chon Loai san pham!");
                    } else {
                        loaiSanPham.setMaloaisp(Integer.parseInt(maloaisp));
                        loaiSanPham.setTenloaisp(tenloaisp.trim());
                        
//                        System.out.println(nhaCungCap);
                
                        boolean result = loaiSanPhamDAO.delete(loaiSanPham);
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
        
        jBtnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDataToTable();
            }
        });
    }
    
}
