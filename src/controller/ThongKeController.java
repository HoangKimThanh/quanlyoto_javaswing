/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DBConnection;
import dao.KhachHangDAOImpl;
import dao.SanPhamDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.SanPham;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**

/**
 *
 * @author ASUS
 */
public class ThongKeController {
    private JLabel jLbTongOTo;
    private JLabel jLbTongPhuTung;
    private JLabel jLbTongKhachHang;
    private JLabel jLbTongDoanhThu;
    
    private JComboBox jCbNam;
    
    private JLabel jLbQuy1;
    private JLabel jLbQuy2;
    private JLabel jLbQuy3;
    private JLabel jLbQuy4;
    private JLabel jLbDoanhThuNam;
    
    private DefaultTableModel jTbTopSP;
    private JPanel jPnChart;

    public ThongKeController(JLabel jLbTongOTo, JLabel jLbTongPhuTung, JLabel jLbTongKhachHang, JLabel jLbTongDoanhThu,
            JComboBox jCbNam, JLabel jLbQuy1, JLabel jLbQuy2, JLabel jLbQuy3, JLabel jLbQuy4, JLabel jLbDoanhThuNam, JTable jTbTopSP, JPanel jPnChart) {
        this.jLbTongOTo = jLbTongOTo;
        this.jLbTongPhuTung = jLbTongPhuTung;
        this.jLbTongKhachHang = jLbTongKhachHang;
        this.jLbTongDoanhThu = jLbTongDoanhThu;
        
        this.jCbNam = jCbNam;
        
        this.jLbQuy1 = jLbQuy1;
        this.jLbQuy2 = jLbQuy2;
        this.jLbQuy3 = jLbQuy3;
        this.jLbQuy4 = jLbQuy4;
        this.jLbDoanhThuNam = jLbDoanhThuNam;
        
        this.jTbTopSP = (DefaultTableModel) jTbTopSP.getModel();
        this.jPnChart = jPnChart;
    }
    
    public void setDataToView() {
        // Thong ke tong quat
        int tongOTo = SanPhamDAOImpl.getTotalByLoai(1);
        jLbTongOTo.setText(tongOTo + "");
        
        int tongPhuTung = SanPhamDAOImpl.getTotalByLoai(2);
        jLbTongPhuTung.setText(tongPhuTung + "");
        
        int tongKhachHang = KhachHangDAOImpl.getTotal();
        jLbTongKhachHang.setText(tongKhachHang + "");
        
        int tongDoanhThu = getTongDoanhThu();
        jLbTongDoanhThu.setText(tongDoanhThu + "");
        
        
        // Thong ke theo nam, quy
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = year; i >= 2021; i--)
            jCbNam.addItem(i);
        
        setDoanhThu(year);
        
        // Top san pham ban chay
        List<SanPham> ListTopSP;
        ListTopSP = getListTopSP();
        int i = 1;
        for (SanPham sanPham: ListTopSP) {
            jTbTopSP.addRow(new Object[]{i++, sanPham.getMaSanPham(), sanPham.getTen(), sanPham.getSoLuong()});
        }
        
        // Ve chart
        ChartPanel chartPanel = new ChartPanel(createChart());
        
        jPnChart.setLayout(new BorderLayout());
        jPnChart.setOpaque(false);
        jPnChart.setBounds(0, 398, 1030, 441);
        jPnChart.add(chartPanel, BorderLayout.NORTH);
    }
    
    public void setEvent() {
        jCbNam.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                int nam = (int) jCbNam.getSelectedItem();
                setDoanhThu(nam);
            }
        });
    }
    
    public void setDoanhThu(int nam) {
        int doanhThuQuy1 = getDoanhThuTheoQuy(nam, 1);
        int doanhThuQuy2 = getDoanhThuTheoQuy(nam, 2);
        int doanhThuQuy3 = getDoanhThuTheoQuy(nam, 3);
        int doanhThuQuy4 = getDoanhThuTheoQuy(nam, 4);
        int tongDoanhThu = doanhThuQuy1 + doanhThuQuy2 + doanhThuQuy3 + doanhThuQuy4;

        jLbQuy1.setText(doanhThuQuy1 + "");
        jLbQuy2.setText(doanhThuQuy2 + "");
        jLbQuy3.setText(doanhThuQuy3 + "");
        jLbQuy4.setText(doanhThuQuy4 + "");
        
        jLbDoanhThuNam.setText(tongDoanhThu + "");
    }
    
    public int getDoanhThuTheoQuy(int nam, int quarter) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT sum(tongtien) "
                    + "FROM hoadon WHERE YEAR(ngaylap) = '" + nam + "' AND QUARTER(ngaylap) = '" + quarter + "'";
            PreparedStatement ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int doanhThu = 0;
            if (rs.next()) {
                doanhThu = rs.getInt(1);
            }

            ps.close();
            cons.close();
            return doanhThu;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public int getTongDoanhThu() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT sum(tongtien) "
                    + "FROM hoadon";
            PreparedStatement ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            int doanhThu = 0;
            if (rs.next()) {
                doanhThu = rs.getInt(1);
            }

            ps.close();
            cons.close();
            return doanhThu;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public List<SanPham> getListTopSP() {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT sanpham.masp, sanpham.ten, sum(cthd.soluong) as total "
                    + "FROM sanpham JOIN cthd ON sanpham.masp = cthd.masp GROUP BY sanpham.masp LIMIT 5";
            PreparedStatement ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<SanPham> listTopSP = new ArrayList<>();
            if (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setMaSanPham(rs.getInt(1));
                sanPham.setTen(rs.getString(2));
                sanPham.setSoLuong(rs.getInt(3));
                listTopSP.add(sanPham);
            }
            
            ps.close();
            cons.close();
            
            return listTopSP;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public int getDoanhThuTheoThang(int thang, int nam) {
        try {
            Connection cons = DBConnection.getConnection();
            String sql = "SELECT sum(tongtien) "
                    + "FROM hoadon WHERE MONTH(ngaylap) = '" + thang + "' AND YEAR(ngaylap) = '" + nam + "'";
            PreparedStatement ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int doanhThu = 0;
            if (rs.next()) {
                doanhThu = rs.getInt(1);
            }

            ps.close();
            cons.close();
            return doanhThu;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public JFreeChart createChart() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        JFreeChart barChart = ChartFactory.createBarChart(
                "DOANH THU THEO THÁNG NĂM " + year,
                "Tháng", "Doanh thu (vnđ)",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = 1; i <= 12; i++) {
            int value = getDoanhThuTheoThang(i, year);
            dataset.setValue(value, "Doanh thu (vnđ)", i + "");
        }
        return dataset;
    }
}
