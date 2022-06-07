/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ASUS
 */
import dao.SanPhamDAO;
import dao.SanPhamDAOImpl;
import dao.CTHDDAO;
import dao.CTHDDAOImpl;
import dao.HoaDonDAO;
import dao.HoaDonDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.CTHD;
import model.HoaDon;
import model.KhachHang;
import model.SanPham;
import quanlyoto.QuanLyOTo;
import view.*;
import utility.ClassTableModel;

public class QuanLyBanHangController {

    private JPanel showTable;
    private JPanel showTableCart;
    private JTextField jTMaSP;
    private JTextField jTTenSP;
    private JTextField jTGia;
    private JLabel jLbAnh;
    private JButton jBAddToCart, jBDelete, jBXuatHoaDon,jBUpdate;
    private JSpinner jSoLuong;
    private JButton jBTest;
    private JComboBox jCbKH, jCbNV;
    private SanPhamDAO sanPhamDAO = null;
    private HoaDonDAO hoaDonDAO = null;
    private CTHDDAO cthdDAO = null;
    private List<SanPham> listCart = null;
    private String[] listColumn = {"STT", "Mã SP", "Tên SP", "GIÁ", "SỐ LƯỢNG"};
    private TableRowSorter<TableModel> rowSorter = null;
    File fileAnhSP;
    Integer tongTien = 0;

    public QuanLyBanHangController(JPanel showTable, JPanel showTableCart, JTextField jTMaSP, JTextField jTTenSP, JTextField jTGia, JSpinner jSoLuong, JButton jBAddToCart, JButton jBDelete, JLabel jLbAnh, JButton jBXuatHoaDon, JComboBox jCbKH, JComboBox jCbNV, JButton jBTest,JButton jBUpdate) {
        this.showTable = showTable;
        this.showTableCart = showTableCart;
        this.jTMaSP = jTMaSP;
        this.jTTenSP = jTTenSP;
        this.jTGia = jTGia;
        this.jLbAnh = jLbAnh;
        this.sanPhamDAO = new SanPhamDAOImpl();
        this.hoaDonDAO = new HoaDonDAOImpl();
        this.cthdDAO = new CTHDDAOImpl();
        this.jSoLuong = jSoLuong;
        this.jBDelete = jBDelete;
        this.listCart = new ArrayList<>();
        this.jBXuatHoaDon = jBXuatHoaDon;
        this.jBAddToCart = jBAddToCart;
        this.jCbKH = jCbKH;
        this.jCbNV = jCbNV;
        this.jBTest = jBTest;
        this.jBUpdate=jBUpdate;
    }

    public void setDataToTable() {
        List<SanPham> listItem = sanPhamDAO.getList();

        DefaultTableModel model = new ClassTableModel().setTableBanHang(listItem, listColumn);
        JTable table = new JTable(model);
        
        jBDelete.setVisible(true);
        jBUpdate.setVisible(true);
        
        rowSorter = new TableRowSorter<>(table.getModel());

        table.setRowSorter(rowSorter);
//        
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertColumnIndexToModel(selectedRowIndex);

//                    KhachHang khachHang = new KhachHang();
//                    khachHang.setMaKhachHang(model.getValueAt(selectedRowIndex, 1).toString());
//                    khachHang.setHoTen(model.getValueAt(selectedRowIndex, 2).toString());
//                    khachHang.setDienThoai(model.getValueAt(selectedRowIndex, 3).toString());
//                    khachHang.setDiaChi(model.getValueAt(selectedRowIndex, 4).toString());
                    jTMaSP.setText(model.getValueAt(selectedRowIndex, 1).toString());
                    jTTenSP.setText(model.getValueAt(selectedRowIndex, 2).toString());
                    jTGia.setText(model.getValueAt(selectedRowIndex, 3).toString());
                    jSoLuong.setValue(1);
//                    jBAddToCart.setEnabled(true);
                    for (SanPham SP : listItem) {
                        if ((model.getValueAt(selectedRowIndex, 1)).equals(SP.getMaSanPham())) {
                            loadAnh("src/images/SanPham/" + SP.getAnh());
                        }
                    }
                    jBAddToCart.setEnabled(true);
                    setDataToCart();

                }

            }

        });

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();

//        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));
//        
        showTable.removeAll();
        showTable.setLayout(new BorderLayout());
        showTable.add(scrollPane);
        showTable.validate();
        showTable.repaint();
    }

    public void setDataToCart() {
        List<SanPham> listItemA = sanPhamDAO.getList();
//        List<SanPham> listItem = listCart;
        jBUpdate.setVisible(false);
        jBDelete.setVisible(false);

        DefaultTableModel model = new ClassTableModel().setTableBanHang(listCart, listColumn);
        JTable table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());
        
        table.setRowSorter(rowSorter);
//        
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

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertColumnIndexToModel(selectedRowIndex);

//                    KhachHang khachHang = new KhachHang();
//                    khachHang.setMaKhachHang(model.getValueAt(selectedRowIndex, 1).toString());
//                    khachHang.setHoTen(model.getValueAt(selectedRowIndex, 2).toString());
//                    khachHang.setDienThoai(model.getValueAt(selectedRowIndex, 3).toString());
//                    khachHang.setDiaChi(model.getValueAt(selectedRowIndex, 4).toString());
                    jTMaSP.setText(model.getValueAt(selectedRowIndex, 1).toString());
                    jTTenSP.setText(model.getValueAt(selectedRowIndex, 2).toString());
                    jTGia.setText(model.getValueAt(selectedRowIndex, 3).toString());
                    jSoLuong.setValue(model.getValueAt(selectedRowIndex, 4));
                    for (SanPham SP : listItemA) {
                        if ((model.getValueAt(selectedRowIndex, 1)).equals(SP.getMaSanPham())) {
                            loadAnh("src/images/SanPham/" + SP.getAnh());
                        }
                    }

                    jBAddToCart.setEnabled(false);
//                    jBDelete.setEnabled(true);
                    setDataToTable();

                }
            }

        });

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();

//        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));
//        
        showTableCart.removeAll();
        showTableCart.setLayout(new BorderLayout());
        showTableCart.add(scrollPane);
        showTableCart.validate();
        showTableCart.repaint();

    }

    public void setEvent(QuanLyBanHangController testController) {
        jBAddToCart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Boolean b = false;
                    SanPham sanPham = new SanPham();
                    Integer MaSP = Integer.parseInt(jTMaSP.getText());
                    Integer soLuong = Integer.parseInt(jSoLuong.getValue().toString());
                    sanPham.setMaSanPham(Integer.parseInt(jTMaSP.getText()));
                    sanPham.setTen(jTTenSP.getText());
                    sanPham.setGia(Integer.parseInt(jTGia.getText()));
                    sanPham.setSoLuong(soLuong);

//                        
//                        List<SanPham> listA=sanPhamDAO.getList();
//                        for (SanPham a:listA){
//                            if (a.getMaSanPham()==sanPham.getMaSanPham())
//                                sanPhamDAO.updateSoLuong(MaSP, a.getSoLuong()-soLuong);
//                        }
                    for (SanPham SPham : listCart) {
                        if (SPham.getMaSanPham() == MaSP) {
                            Integer a = SPham.getSoLuong() + soLuong;
                            b = true;
                            SPham.setSoLuong(a);
                            break;
                        }
                    }
                    if (!b) {
                        listCart.add(sanPham);
                    }
                    setDataToCart();
//                        setDataToTable();

//                        String lastId = khachHangDAO.createOrUpdate(khachHang);
//                        if (!lastId.equals("")) {
//                            khachHang.setMaKhachHang(lastId);
//                            JOptionPane.showMessageDialog(null, "Xử lý cập nhật dữ liệu thành công");
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
//                        }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });
        jBDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Integer MaSP = Integer.parseInt(jTMaSP.getText());

                for (SanPham SP : listCart) {
                    if (SP.getMaSanPham() == MaSP) {
                        listCart.remove(SP);
                        break;
                    }
                }
                setDataToCart();
                jTMaSP.setText("");
                jTTenSP.setText("");
                jTGia.setText("");
                jSoLuong.setValue(0);

//                try {
////                        SanPham sanPham = new SanPham();
////                        sanPham.setMaSP(jTMaSP.getText());
////                        sanPham.setTenSP(jTTenSP.getText());
////                        sanPham.setGia(jTGia.getText());
//                        
////                        sanPham.setSoLuong(jSoLuong.getValue().toString());
////                        listCart.add(sanPham);
//                        
//                        
//                        
//                        
//                
////                        String lastId = khachHangDAO.createOrUpdate(khachHang);
////                        if (!lastId.equals("")) {
////                            khachHang.setMaKhachHang(lastId);
////                            JOptionPane.showMessageDialog(null, "Xử lý cập nhật dữ liệu thành công");
////                        } else {
////                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
////                        }
//                    
//                }
//                catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, ex.toString());
//                }
            }

        });
        jBTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setNgayLap(java.time.LocalDate.now().toString());
                hoaDon.setGhiChu("");
//                new AddHoaDon(hoaDon,listCart).setVisible(true);
                AddHoaDon frameAddHoaDon;

                frameAddHoaDon = new AddHoaDon(hoaDon, listCart);
                frameAddHoaDon.setVisible(true);

                frameAddHoaDon.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                

            }

        });
        jBUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Integer MaSP = Integer.parseInt(jTMaSP.getText());

                for (SanPham SP : listCart) {
                    if (SP.getMaSanPham() == MaSP) {
                        SP.setSoLuong(Integer.parseInt(jSoLuong.getValue().toString()));
                        break;
                    }
                }
                setDataToCart();
                jTMaSP.setText("");
                jTTenSP.setText("");
                jTGia.setText("");
                jSoLuong.setValue(0);

//                try {
////                        SanPham sanPham = new SanPham();
////                        sanPham.setMaSP(jTMaSP.getText());
////                        sanPham.setTenSP(jTTenSP.getText());
////                        sanPham.setGia(jTGia.getText());
//                        
////                        sanPham.setSoLuong(jSoLuong.getValue().toString());
////                        listCart.add(sanPham);
//                        
//                        
//                        
//                        
//                
////                        String lastId = khachHangDAO.createOrUpdate(khachHang);
////                        if (!lastId.equals("")) {
////                            khachHang.setMaKhachHang(lastId);
////                            JOptionPane.showMessageDialog(null, "Xử lý cập nhật dữ liệu thành công");
////                        } else {
////                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
////                        }
//                    
//                }
//                catch (Exception ex) {
//                    JOptionPane.showMessageDialog(null, ex.toString());
//                }
            }

        });
        jBXuatHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setMaKH(Integer.parseInt(jCbKH.getSelectedItem().toString()));
                    hoaDon.setMaNV(Integer.parseInt(jCbNV.getSelectedItem().toString()));
                    hoaDon.setNgayLap(java.time.LocalDate.now().toString());
                    hoaDon.setTongTien(tongTien);
                    hoaDon.setGhiChu("");
//                        sanPham.setTenSP(jTTenSP.getText());
//                        sanPham.setGia(jTGia.getText());

//                        sanPham.setSoLuong(jSoLuong.getValue().toString());
//                        listCart.add(sanPham);
//                        String MaSP=jTMaSP.getText();
//                        
//                        for(SanPham SP: listCart) {
//                            if(SP.getMaSP().equals(MaSP)) {
//                            listCart.remove(SP);
//                            }
//                        }
//                        setDataToCart();
                    int check = hoaDonDAO.createHoaDon(hoaDon);
                    List<HoaDon> listHoaDon = hoaDonDAO.getList();
                    for (SanPham sanPham : listCart) {
                        CTHD cthd = new CTHD();
                        cthd.setMaHD(listHoaDon.get(listHoaDon.size() - 1).getMaHD());
                        cthd.setMaSP(sanPham.getMaSanPham());
                        cthd.setSoLuong(sanPham.getSoLuong());
                        cthd.setGia(sanPham.getGia());
                        System.out.println(sanPham.getSoLuong());
                        cthd.setTien(sanPham.getGia() * sanPham.getSoLuong());
                        tongTien = tongTien + sanPham.getGia() * sanPham.getSoLuong();
                        int checkb = cthdDAO.createCTHD(cthd);

                    }
                    listCart = new ArrayList<>();
                    setDataToCart();

                    if (check != 0) {

                        JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công", "Hóa đơn", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại", "Hóa đơn", JOptionPane.ERROR_MESSAGE);
                    }

//                        String lastId = khachHangDAO.createOrUpdate(khachHang);
//                        if (!lastId.equals("")) {
//                            khachHang.setMaKhachHang(lastId);
//                            JOptionPane.showMessageDialog(null, "Xử lý cập nhật dữ liệu thành công");
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại");
//                        }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });

    }

    private void loadAnh(String anh) {
        jLbAnh.setIcon(getAnhSP(anh));
    }

    private ImageIcon getAnhSP(String src) {
        src = src.trim().equals("") ? "default.png" : src;

        //Xử lý ảnh
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            src = "default.png";
            fileImg = new File("src/images/SanPham/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            fileAnhSP = new File(src);
        } catch (IOException e) {
            fileAnhSP = new File("src/images/default.png");
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }

}
