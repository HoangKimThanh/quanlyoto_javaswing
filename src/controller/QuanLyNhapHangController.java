/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ChiTietPhieuNhapDAO;
import dao.ChiTietPhieuNhapDAOImpl;
import dao.NhaCungCapDAO;
import dao.NhaCungCapDAOImpl;
import dao.PhieuNhapDAO;
import dao.PhieuNhapDAOImpl;
import dao.SanPhamDAO;
import dao.SanPhamDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import model.ChiTietPhieuNhap;
import model.NhaCungCap;
import model.PhieuNhap;
import model.SanPham;
import utility.ClassTableModel;

/**
 *
 * @author Home
 */
public class QuanLyNhapHangController {
    private JScrollPane jScrollPaneKhoHang;
    private JScrollPane jScrollPaneHangChoNhap;

    private JTable jTableKhoHang;
    private JTable jTableHangChoNhap;

    private JTextField jTfSearch;
    
    private JTextField jTfMaSP;
    private JTextField jTfTenSP;
    private JTextField jTfDonGia;
    private JTextField jTfSLNhap;
    
    private JTextField jTfNhanVien;
    
    private JButton jBtnNhapHang;
    private JButton jBtnXoaHang;
    private JButton jBtnXacNhan;
    private JButton jBtnThayDoiSoLuong;

    private JComboBox jComboBoxNhaCC;

    
    private List<ChiTietPhieuNhap> danhSachSanPhamNhap;
    
    
//    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyNhapHangController(JScrollPane jScrollPaneKhoHang, JScrollPane jScrollPaneHangChoNhap,
            JTable jTableKhoHang, JTable jTableHangChoNhap,
            JTextField jTfSearch, JTextField jTfMaSP, JTextField jTfTenSP, JTextField jTfDonGia,
            JTextField jTfSLNhap, JTextField jTfNhanVien,
            JButton jBtnNhapHang, JButton jBtnXoaHang, JButton jBtnXacNhan, JButton jBtnThayDoiSoLuong,
            JComboBox jComboBoxNhaCC) {
        this.jScrollPaneKhoHang = jScrollPaneKhoHang;
        this.jScrollPaneHangChoNhap = jScrollPaneHangChoNhap;

        this.jTableKhoHang = jTableKhoHang;
        this.jTableHangChoNhap = jTableHangChoNhap;
        this.jTfSearch = jTfSearch;
        
        this.jTfMaSP = jTfMaSP;
        this.jTfTenSP = jTfTenSP;
        this.jTfDonGia = jTfDonGia;
        this.jTfSLNhap = jTfSLNhap;
        this.jTfNhanVien = jTfNhanVien;
        
        this.jBtnNhapHang = jBtnNhapHang;
        this.jBtnXoaHang = jBtnXoaHang;
        this.jBtnXacNhan = jBtnXacNhan;
        this.jBtnThayDoiSoLuong = jBtnThayDoiSoLuong;

        this.jComboBoxNhaCC = jComboBoxNhaCC;
        
        this.danhSachSanPhamNhap = new ArrayList<>();
        
        loadSanPhamToTable();
        loadNhaCungCapToComboBox();
        
        resetUI();
        
       
       
    }
    
    
    public void loadSanPhamToTable() {
        SanPhamDAO sanPhamDAO = new SanPhamDAOImpl();
        
        List<SanPham> listSanPham = sanPhamDAO.getList();
        
        DefaultTableModel model = (DefaultTableModel)jTableKhoHang.getModel();
        jTableKhoHang.setDefaultEditor(Object.class, null);

        model.setRowCount(0);
        for (int i = 0; i < listSanPham.size(); i++) {
            model.addRow(new Object[]{
                listSanPham.get(i).getMaSanPham(), 
                listSanPham.get(i).getTen(), 
                listSanPham.get(i).getSoLuong(),

            });
        }
        jTableKhoHang.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        jTableKhoHang.getTableHeader().setPreferredSize(new Dimension(50, 30));
        jTableKhoHang.setRowHeight(30);
        
        jScrollPaneKhoHang.getViewport().add(jTableKhoHang);
        
        
        jTableKhoHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jTableKhoHang.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) jTableKhoHang.getModel();
                    int selectedRowIndex = jTableKhoHang.getSelectedRow();
                    
                   
                    SanPham sanPham = new SanPham();
                    sanPham.setMaSanPham((int) model.getValueAt(selectedRowIndex, 0));
                    sanPham.setTen(model.getValueAt(selectedRowIndex, 1).toString());
                    sanPham.setSoLuong((int) model.getValueAt(selectedRowIndex, 2));
                    
                    
                    jTfMaSP.setText((sanPham.getMaSanPham()) + "");
                    jTfTenSP.setText(sanPham.getTen());
                    
                    jBtnNhapHang.setEnabled(true);
                  
                }
            }
            
        });
    
    }
    
    
    public void loadNhaCungCapToComboBox() {
        NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAOImpl();
        
        List<NhaCungCap> listNhaCC = nhaCungCapDAO.getList();
        for (int i = 0; i < listNhaCC.size(); i++) {
            jComboBoxNhaCC.addItem(listNhaCC.get(i));
        }
    }
    
    public void showDanhSachSanPhamNhap() {
        resetUI();
        if (danhSachSanPhamNhap.size() > 0) {
            jBtnThayDoiSoLuong.setEnabled(true);
            jBtnXoaHang.setEnabled(true);
            jBtnXacNhan.setEnabled(true);
        }
        
        DefaultTableModel model = (DefaultTableModel)jTableHangChoNhap.getModel();
        jTableHangChoNhap.setDefaultEditor(Object.class, null);

        model.setRowCount(0);
        for (int i = 0; i < danhSachSanPhamNhap.size(); i++) {
            model.addRow(new Object[]{
                danhSachSanPhamNhap.get(i).getMaSanPham(), 
                danhSachSanPhamNhap.get(i).getTenSanPham(), 
                danhSachSanPhamNhap.get(i).getDonGia(),                
                danhSachSanPhamNhap.get(i).getSoLuong(),
                danhSachSanPhamNhap.get(i).getThanhTien(),
            });
        }
        jTableHangChoNhap.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        jTableHangChoNhap.getTableHeader().setPreferredSize(new Dimension(50, 30));
        jTableHangChoNhap.setRowHeight(30);

        jScrollPaneHangChoNhap.getViewport().add(jTableHangChoNhap);
        
    }
    
    
    public void themSanPhamVaoDanhSachNhap(ChiTietPhieuNhap ctPhieuNhap) {
        boolean isAdded = false;
        for(int i = 0; i < danhSachSanPhamNhap.size(); i++) {
            if (ctPhieuNhap.getMaSanPham() == danhSachSanPhamNhap.get(i).getMaSanPham()) {
                int ghiDe = JOptionPane.showConfirmDialog(null, 
                        "Sản phẩm " + ctPhieuNhap.getMaSanPham() + "-" + ctPhieuNhap.getTenSanPham() 
                        + " đã tồn tại. Bạn có tiếp tục và tăng số lượng không?",
                        "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (ghiDe != JOptionPane.YES_OPTION) {
                    return;
                }
                if (ctPhieuNhap.getDonGia() != danhSachSanPhamNhap.get(i).getDonGia()) {
                    Object[] options = {"Dùng đơn giá trước", "Dùng đơn giá mới"};
                    int luaChon = JOptionPane.showOptionDialog(null, 
                        "Đơn giá không giống nhau. Hạy chọn một lựa chọn dưới đây:", "Đơn giá không giống nhau",
                         JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                         null, options, options[0]);
                    if (luaChon == 0) {
                        ctPhieuNhap.setDonGia(danhSachSanPhamNhap.get(i).getDonGia());
                    } else {
                        danhSachSanPhamNhap.get(i).setDonGia(ctPhieuNhap.getDonGia());
                    }
                    
                }
                
                isAdded = true;
               
                int sl = danhSachSanPhamNhap.get(i).getSoLuong();
                int newSL = sl + ctPhieuNhap.getSoLuong();
                danhSachSanPhamNhap.get(i).setSoLuong(newSL > 0 ? newSL : 1);
                danhSachSanPhamNhap.get(i).setThanhTien(newSL * ctPhieuNhap.getDonGia());
                break;
            }
        }
        // Truong hop: sp chua nam trong "Hang Cho Nhap"
        if (!isAdded) {
            danhSachSanPhamNhap.add(ctPhieuNhap);
            return;
        }        
    }
    
    public void xoaSanPhamKhoiDanhSachNhap(int maSanPham) {
        int index = -1;
        for(int i = 0; i < danhSachSanPhamNhap.size(); i++) {
            if (danhSachSanPhamNhap.get(i).getMaSanPham() == maSanPham) {
                index = i;
                break;
            }
        }
        
        if (index != -1) {
            danhSachSanPhamNhap.remove(index);
        }
    
    }
    
    public void capNhatSoLuongSanPham(int maSanPham, int newSL) {
    
        for(int i = 0; i < danhSachSanPhamNhap.size(); i++) {
            if (danhSachSanPhamNhap.get(i).getMaSanPham() == maSanPham) {
                danhSachSanPhamNhap.get(i).setSoLuong(newSL);
                danhSachSanPhamNhap.get(i).setThanhTien(newSL * danhSachSanPhamNhap.get(i).getDonGia());
                break;
            }
        }
        
    }
    
    public int tinhTongHangChoNhap() {
        int tong = 0;
        for(int i = 0; i < danhSachSanPhamNhap.size(); i++) {
            tong += danhSachSanPhamNhap.get(i).getThanhTien();
        }
        return tong;
    }
    
     private void resetUI() {
        jTfMaSP.setText("");
        jTfTenSP.setText("");
        jTfDonGia.setText("");
        jTfSLNhap.setText("");
        
        jBtnNhapHang.setEnabled(false);
        jBtnThayDoiSoLuong.setEnabled(false);
        jBtnXoaHang.setEnabled(false);
        jBtnXacNhan.setEnabled(false);
        
    }
    
    public void setDataToTable() {
    }
        
//        jTfSearch.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                String text = jTfSearch.getText();
//                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
//                }
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                String text = jTfSearch.getText();
//                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
//                }
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//            }
//        });
//        
        

//    }

    public void setEvent() {
        jBtnNhapHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (jTfMaSP.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để nhập!");
                        return;
                    }
                    
                    if (jTfDonGia.getText().equals("") || jTfSLNhap.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Đơn giá, số lượng không được để trống!!");
                        return;
                    }
                    
                    int maSanPham = Integer.valueOf(jTfMaSP.getText());
                    String tenSanPham = jTfTenSP.getText();
                    
                    int donGia = Integer.valueOf(jTfDonGia.getText());
                    int soLuongNhap = Integer.valueOf(jTfSLNhap.getText());
                    
                    if (donGia <= 0 || soLuongNhap <= 0) {
                        JOptionPane.showMessageDialog(null, "Đơn giá, số lượng phải là số nguyên dương!!");
                        return;
                    }

                    ChiTietPhieuNhap ctPhieuNhap = new ChiTietPhieuNhap();
                    ctPhieuNhap.setMaSanPham(maSanPham);
                    ctPhieuNhap.setTenSanPham(tenSanPham);
                    ctPhieuNhap.setDonGia(donGia);
                    ctPhieuNhap.setSoLuong(soLuongNhap);
                    ctPhieuNhap.setThanhTien(donGia * soLuongNhap);
                    
                    themSanPhamVaoDanhSachNhap(ctPhieuNhap);
                    showDanhSachSanPhamNhap();
                    
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
        jBtnXoaHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (jTableHangChoNhap.getSelectedRow() != -1) {
                        DefaultTableModel model = (DefaultTableModel) jTableHangChoNhap.getModel();
                        int selectedRowIndex = jTableHangChoNhap.getSelectedRow();

                        int maSanPham = (int) model.getValueAt(selectedRowIndex, 0);
                        
                        xoaSanPhamKhoiDanhSachNhap(maSanPham);
                        showDanhSachSanPhamNhap();
                        return;
                    }
                    
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng để xóa!");
                    
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
        jBtnXacNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    NhaCungCap nhaCC = (NhaCungCap) jComboBoxNhaCC.getSelectedItem();
                    
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    
                    String ngayLap = sdf.format(date);
                    
                    int tongTien = tinhTongHangChoNhap();
                    
                    PhieuNhap phieuNhap = new PhieuNhap();
                    phieuNhap.setMaNhaCungCap(nhaCC.getMancc());
                    phieuNhap.setMaNhanVien(1);
                    phieuNhap.setNgayLap(ngayLap);
                    phieuNhap.setTongTien(tongTien);
                    
                    PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAOImpl();
                    ChiTietPhieuNhapDAO ctPhieuNhapDAO = new ChiTietPhieuNhapDAOImpl();
                    SanPhamDAO sanPhamDAO =  new SanPhamDAOImpl();
                    
                    int maPhieuNhap = phieuNhapDAO.create(phieuNhap);
                    if (maPhieuNhap !=0) {
                        for(int i = 0; i < danhSachSanPhamNhap.size(); i++) {
                            danhSachSanPhamNhap.get(i).setMaPhieuNhap(maPhieuNhap);
                            ctPhieuNhapDAO.create(danhSachSanPhamNhap.get(i));
                            sanPhamDAO.updateSoLuong(
                                danhSachSanPhamNhap.get(i).getMaSanPham(), 
                                danhSachSanPhamNhap.get(i).getSoLuong()
                            );
                        }
                        JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công");
                        loadSanPhamToTable();
                        danhSachSanPhamNhap.clear();
                        showDanhSachSanPhamNhap();
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
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
        jBtnThayDoiSoLuong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    
                    if (jTableHangChoNhap.getSelectedRow() != -1) {
                        DefaultTableModel model = (DefaultTableModel) jTableHangChoNhap.getModel();
                        int selectedRowIndex = jTableHangChoNhap.getSelectedRow();

                        int maSanPham = (int) model.getValueAt(selectedRowIndex, 0);
                        int sl = (int) model.getValueAt(selectedRowIndex, 3);
                        
                        String response = (String)JOptionPane.showInputDialog(
                            null,
                            "Vui lòng nhập số lượng mới!!",
                            "Cập nhật số lượng cho sản phẩm " + maSanPham,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            sl);
                        if (response == null) {
                            return;
                        }
                        if (response.equals("")) {
                            JOptionPane.showMessageDialog(null, "Không để trống!");
                            return;
                        }
                        try {
                            int newSL = Integer.parseInt(response);
                            if (newSL <= 0) {
                                JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương!!");
                                return;
                            }
                            capNhatSoLuongSanPham(maSanPham, newSL);
                            showDanhSachSanPhamNhap();
                        } catch (NumberFormatException  nfe) {
                            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên dương!!");
                        }
                        
                      
                        return;
                    }
                    
                    JOptionPane.showMessageDialog(null, "Bạn chưa chọn hàng để cập nhật!");
                    
                    
                    
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
//        jBtnAdd.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    String tenncc = jTfTen.getText();
//                    String sdt = jTfSDT.getText();
//                    String diachi = jTfDiaChi.getText();
//                    if (tenncc.equals("") || sdt.equals("") || diachi.equals("")) {
//                        JOptionPane.showMessageDialog(null, "Ten NCC, So dien thoai, Dia chi khong duoc de trong!");
//                    } else {
//                        nhaCungCap.setTenncc(tenncc.trim());
//                        nhaCungCap.setDienthoai(sdt);
//                        nhaCungCap.setDiachi(diachi.trim());
//                        
////                        System.out.println(nhaCungCap);
//                
//                        boolean result = nhaCungCapDAO.create(nhaCungCap);
//                        if (result) {
//                            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công");
//                            
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
        
//        jBtnUpdate.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    String mancc = jTfMaNcc.getText();
//                    String tenncc = jTfTen.getText();
//                    String sdt = jTfSDT.getText();
//                    String diachi = jTfDiaChi.getText();
//                    if (mancc.equals("") || tenncc.equals("") || sdt.equals("") || diachi.equals("")) {
//                        JOptionPane.showMessageDialog(null, "Ma NCC, Ten NCC, So dien thoai, Dia chi khong duoc de trong!");
//                    } else {
//                        nhaCungCap.setMancc(Integer.parseInt(mancc));
//                        nhaCungCap.setTenncc(tenncc.trim());
//                        nhaCungCap.setDienthoai(sdt);
//                        nhaCungCap.setDiachi(diachi.trim());
//                        
//                        boolean result = nhaCungCapDAO.update(nhaCungCap);
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
//        jBtnDelete.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    String mancc = jTfMaNcc.getText();
//                    String tenncc = jTfTen.getText();
//                    String sdt = jTfSDT.getText();
//                    String diachi = jTfDiaChi.getText();
//                    if (mancc.equals("")) {
//                        JOptionPane.showMessageDialog(null, "Vui long chon NCC!");
//                    } else {
//                        nhaCungCap.setMancc(Integer.parseInt(mancc));
//                        nhaCungCap.setTenncc(tenncc.trim());
//                        nhaCungCap.setDienthoai(sdt);
//                        nhaCungCap.setDiachi(diachi.trim());
//                        
////                        System.out.println(nhaCungCap);
//                
//                        boolean result = nhaCungCapDAO.delete(nhaCungCap);
//                        if (result) {
//                            JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công");
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
        
//        jBtnReset.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                setDataToTable();
//            }
//
//        });
    }
}

