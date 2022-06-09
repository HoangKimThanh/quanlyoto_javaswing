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
import javax.swing.SpinnerNumberModel;
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
    private JButton jBAddToCart, jBDelete, jBXuatHoaDon, jBUpdate;
    private JSpinner jSoLuong;
    private JButton jBTest;
    private SanPhamDAO sanPhamDAO = null;
    private HoaDonDAO hoaDonDAO = null;
    private CTHDDAO cthdDAO = null;
    private List<SanPham> listCart = null;
    private String[] listColumn = {"STT", "Mã SP", "Tên SP", "GIÁ", "SỐ LƯỢNG"};
    private TableRowSorter<TableModel> rowSorter = null;
    File fileAnhSP;
    Integer tongTien = 0;

    public QuanLyBanHangController(JPanel showTable, JPanel showTableCart, JTextField jTMaSP, JTextField jTTenSP, JTextField jTGia, JSpinner jSoLuong, JButton jBAddToCart, JButton jBDelete, JLabel jLbAnh, JButton jBXuatHoaDon, JButton jBTest, JButton jBUpdate) {
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
        this.jBTest = jBTest;
        this.jBUpdate = jBUpdate;
    }

    public void setDataToTable() {
        List<SanPham> listItem = sanPhamDAO.getList();
        DefaultTableModel model = new ClassTableModel().setTableBanHang(listItem, listColumn);
        JTable table = new JTable(model);
        jBDelete.setEnabled(true);
        jBUpdate.setEnabled(true);
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    jTMaSP.setText(model.getValueAt(selectedRowIndex, 1).toString());
                    jTTenSP.setText(model.getValueAt(selectedRowIndex, 2).toString());
                    jTGia.setText(model.getValueAt(selectedRowIndex, 3).toString());
                    SpinnerNumberModel md = new SpinnerNumberModel(1, 1, Integer.parseInt(model.getValueAt(selectedRowIndex, 4).toString()), 1);
                    jSoLuong.setModel(md);
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
    
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(table);
        scrollPane.setPreferredSize(new Dimension(1300, 400));
        
        showTable.removeAll();
        showTable.setLayout(new BorderLayout());
        showTable.add(scrollPane);
        showTable.validate();
        showTable.repaint();
    }

    public void setDataToCart() {
        List<SanPham> listItemA = sanPhamDAO.getList();
        jBUpdate.setEnabled(false);
        jBDelete.setEnabled(false);
        
        if (listCart.size() != 0) {
            jBTest.setEnabled(true);
            jBXuatHoaDon.setEnabled(true);
        } else {
            jBTest.setEnabled(false);
            jBXuatHoaDon.setEnabled(false);
        }
        DefaultTableModel model = new ClassTableModel().setTableBanHang(listCart, listColumn);
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
                    setDataToTable();
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
                    Boolean isInCart = false;
                    SanPham sanPham = new SanPham();
                    Integer MaSP = Integer.parseInt(jTMaSP.getText());
                    Integer soLuong = Integer.parseInt(jSoLuong.getValue().toString());
                    sanPham.setMaSanPham(Integer.parseInt(jTMaSP.getText()));
                    sanPham.setTen(jTTenSP.getText());
                    sanPham.setGia(Integer.parseInt(jTGia.getText()));
                    sanPham.setSoLuong(soLuong);

                    for (SanPham SPham : listCart) {
                        if (SPham.getMaSanPham() == MaSP) {
                            Integer a = SPham.getSoLuong() + soLuong;
                            isInCart = true;
                            SPham.setSoLuong(a);
                            break;
                        }
                    }
                    if (!isInCart) {
                        listCart.add(sanPham);
                    }
                    setDataToCart();
                    
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
            }
        });
        jBTest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setNgayLap(java.time.LocalDate.now().toString());
                hoaDon.setGhiChu("");

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
            }

        });
        jBXuatHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    listCart = new ArrayList<>();
                    setDataToCart();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

        });

    }

    private void loadAnh(String anh) {
        jLbAnh.setText("");
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
