/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CTHDDAO;
import dao.CTHDDAOImpl;
import dao.HoaDonDAO;
import dao.HoaDonDAOImpl;
import dao.SanPhamDAO;
import dao.SanPhamDAOImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.CTHD;
import model.HoaDon;
import model.SanPham;
import view.AddHoaDon;
import view.AddKhachHang;
import view.BanHangJPanel;

/**
 *
 * @author ASUS
 */
public class ThemHoaDonController {
    private JTextField jTTenNV;
    private JComboBox jCbMaNV;
    private JComboBox jCbMaKH;
    private JButton jBAddHD;
    private JFrame root;
    private JButton jBtnAddKH;
    public ThemHoaDonController(JTextField jTTenNV, JComboBox jCbMaNV, JComboBox jCbMaKH, JButton jBAddHD, JFrame root,JButton jBtnAddKH) {
        this.jTTenNV = jTTenNV;
        this.jCbMaNV = jCbMaNV;
        this.jCbMaKH = jCbMaKH;
        this.jBAddHD=jBAddHD;
        this.root = root;
        this.jBtnAddKH=jBtnAddKH;
    }
    
    public void setEvent(HoaDon hoaDon, List<SanPham> listCart) {
        jBAddHD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                        
                        hoaDon.setMaKH(Integer.parseInt(jCbMaKH.getSelectedItem().toString()));
                        hoaDon.setMaNV(Integer.parseInt(jCbMaNV.getSelectedItem().toString()));
                        
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
                        HoaDonDAO hoaDonDAO =new HoaDonDAOImpl();
                        List<HoaDon> listHoaDon = hoaDonDAO.getList();
                        Integer tongTien=0;
                        CTHDDAO cthdDAO=new CTHDDAOImpl();
                        
                        for (SanPham sanPham : listCart)
                        {
                            
                            
                            tongTien=tongTien+sanPham.getGia()*sanPham.getSoLuong();
                            
                            
                        }
                        hoaDon.setTongTien(tongTien);
                        
                        int check= hoaDonDAO.createHoaDon(hoaDon);
                        SanPhamDAO sanPhamDAO=new SanPhamDAOImpl();
                        List<SanPham> listA=sanPhamDAO.getList();
                        
                        for (SanPham sanPham : listCart)
                        {
                            for (SanPham a:listA){
                                if (a.getMaSanPham()==sanPham.getMaSanPham())
                                    a.setSoLuong(a.getSoLuong()-sanPham.getSoLuong());
                                sanPhamDAO.updateSanPham(a);
                            }
                            CTHD cthd=new CTHD();
                            cthd.setMaHD(listHoaDon.get(listHoaDon.size()-1).getMaHD()+1);
                            cthd.setMaSP(sanPham.getMaSanPham());
                            cthd.setSoLuong(sanPham.getSoLuong());
                            cthd.setGia(sanPham.getGia());
                            System.out.println(sanPham.getSoLuong());
                            cthd.setTien(sanPham.getGia()*sanPham.getSoLuong());
                            int checkb=cthdDAO.createCTHD(cthd);
                            
                        }
                        
                        
                        if (check != 0) {
                            new BanHangJPanel().setVisible(true);
                            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công","Hóa đơn", JOptionPane.INFORMATION_MESSAGE);
                            root.dispose();
                            
                        } else {
                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại", "Hóa đơn", JOptionPane.ERROR_MESSAGE);
                        }
                        
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

            
            
        });
        jBtnAddKH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                        AddKhachHang a=new AddKhachHang();
                        a.setVisible(true);
                        
                        
                       
                        
                        
//                        if (check != 0) {
//                            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công","Hóa đơn", JOptionPane.INFORMATION_MESSAGE);   
//                        } else {
//                            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra, vui lòng thử lại", "Hóa đơn", JOptionPane.ERROR_MESSAGE);
//                        }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

            
            
        });
    }
}
