///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controller;
//
//import MyCustom.MyDialog;
//import dao.PhanQuyenDAO;
//import dao.PhanQuyenDAOImpl;
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.RowFilter;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableModel;
//import javax.swing.table.TableRowSorter;
//import model.Quyen;
//import utility.ClassTableModel;
//
///**
// *
// * @author Home
// */
//
//public class PhanQuyenController {
//    
//    private final JPanel jPnView;
//
//
//    private Quyen phanQuyen = null;
//    private PhanQuyenDAO phanQuyenDAO = null;
//    
//    private final String[] listColumn = {"STT", "Mã NV", "Tên NV", "Chức vụ"};
//    
//    private TableRowSorter<TableModel> rowSorter = null;
//    private final JButton jBtnUpdate1;
//    private final JComboBox jCbChucVu;
//    private final JComboBox jCbQuyen;
//       
//    
//
//    public PhanQuyenController(JPanel jPnView, JButton jBtnUpdate1, JComboBox jCbChucVu, JComboBox jCbQuyen, JCheckBox jCheckBox1, JCheckBox jCheckBox2, JCheckBox jCheckBox3, JCheckBox jCheckBox4) {
//        this.jPnView = jPnView;
//        
//        this.jBtnUpdate1 = jBtnUpdate1;
//        this.jCbChucVu=jCbChucVu;
//        this.jCbQuyen=jCbQuyen;
//        this.phanQuyenDAO = new PhanQuyenDAOImpl();
//        this.phanQuyen = new Quyen();
//    }
//    
//   
//
//    public void setEvent() {
//        jCbChucVu.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String chucVu = jCbChucVu.getSelectedItem() + "";
//                String quyen = jCbQuyen.getSelectedItem() + "";
//
//                switch(chucVu){
//                    case "Nhân viên":
//                        if(quyen=="Quản lý sản phẩm"){
//                            
//                        }
//                }
//            }
//        });
//    }
//        
//        jBtnUpdate1.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    String chucVu = jCbChucVu.getName();
//                    String tennv = jTfHoTen.getText();
//                    
//                    String chucVu = jTfChucVu.getText();
//                    if (manv.equals("") || tennv.equals("")|| chucVu.equals("")) {
//                        JOptionPane.showMessageDialog(null, "Ma NV, Ten NV, chuc vu khong duoc de trong!");
//                    } else {
//                        nhanVien.setMaNV(Integer.parseInt(manv));
//                        nhanVien.setHoTen(tennv.trim());
//                        
//                        nhanVien.setChucVu(chucVu.trim());
//                        
//                        boolean result = nhanVienDAO.update(nhanVien);
//                        if (result) {
//                            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công");
//                            setDataToTable();
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
//        });
//        
//        
//
//}
////
////package controller;
////
////import dao.PhanQuyenDAO;
////import model.Quyen;
////import MyCustom.MyDialog;
////
////import java.util.ArrayList;
////
////public class PhanQuyenController {
////
////    public static Quyen quyenTK = null;
////    private PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAO();
////    private ArrayList<Quyen> listPhanQuyen = null;
////
////    public void docDanhSachQuyen() {
////        this.listPhanQuyen = phanQuyenDAO.getListQuyen();
////    }
////
////    public void kiemTraQuyen(String quyen) {
////        quyenTK = phanQuyenDAO.getQuyen(quyen);
////    }
////
////    public ArrayList<Quyen> getListQuyen() {
////        if (listPhanQuyen == null)
////            docDanhSachQuyen();
////        return this.listPhanQuyen;
////    }
////
//////    public boolean suaQuyen(String tenQuyen, int nhapHang, int sanPham, int nhanVien, int khachHang, int thongKe) {
//////        Quyen phanQuyen = new Quyen(tenQuyen, nhapHang, sanPham, nhanVien, khachHang, thongKe);
//////        boolean flag = phanQuyenDAO.suaQuyen(phanQuyen);
//////        if (flag) {
//////            new MyDialog("Sửa thành công!", MyDialog.SUCCESS_DIALOG);
//////        } else {
//////            new MyDialog("Sửa thất bại!", MyDialog.ERROR_DIALOG);
//////        }
//////        return flag;
//////    }
////
//////    public boolean themQuyen(String tenQuyen,String chucVu) {
//////        if (tenQuyen == null || tenQuyen.trim().equals("")) {
//////            return false;
//////        }
//////
//////        if (kiemTonTaiTraQuyen(tenQuyen)) {
//////            new MyDialog("Thêm thất bại! Quyền đã tồn tại", MyDialog.ERROR_DIALOG);
//////            return false;
//////        }
//////
//////        Quyen phanQuyen = new Quyen(tenQuyen,chucVu, 0, 0, 0, 0);
//////        boolean flag = phanQuyenDAO.themQuyen(phanQuyen);
//////        if (flag) {
//////            new MyDialog("Thêm thành công! Hãy hiệu chỉnh quyền", MyDialog.SUCCESS_DIALOG);
//////        } else {
//////            new MyDialog("Thêm thất bại! Quyền đã tồn tại", MyDialog.ERROR_DIALOG);
//////        }
//////        return flag;
//////    }
////
//////    private boolean kiemTonTaiTraQuyen(String tenQuyen) {
//////        docDanhSachQuyen();
//////        for (Quyen q : listPhanQuyen) {
//////            if (q.getQuyen().equalsIgnoreCase(tenQuyen))
//////                return true;
//////        }
//////        return false;
//////    }
////
////    public boolean xoaQuyen(String tenQuyen) {
////        boolean flag = phanQuyenDAO.xoaQuyen(tenQuyen);
////        if (flag) {
////            new MyDialog("Xoá thành công!", MyDialog.SUCCESS_DIALOG);
////        } else {
////            new MyDialog("Xoá thất bại!", MyDialog.ERROR_DIALOG);
////        }
////        return flag;
////    }
////}
