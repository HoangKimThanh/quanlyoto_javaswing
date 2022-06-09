/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CTHDDAO;
import dao.CTHDDAOImpl;
import dao.HoaDonDAO;
import dao.HoaDonDAOImpl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.CTHD;
import model.HoaDon;
import utility.ClassTableModel;
import view.*;

/**
 *
 * @author ASUS
 */
public class QuanLyHoaDonController {

    private HoaDonDAO hoaDonDAO = null;

    private String[] listColumn = {"STT", "Mã HD", "Mã NV", "Mã KH", "Ngày lập", "Tổng tiền", "Ghi chú"};
    private JPanel showHoaDon, showCTHD;
    private JTextField jTMaHD, jTMaKH, jTMaNV, jTNgay, jTTienHD, jTMaHDCT,
            jTMaSPCT, jTSoLuongCT, jTGiaCT, jTTienCT;
    private JButton jBReLoad, jBDeleteb;
    private JTabbedPane jTab;

    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyHoaDonController(JPanel showHoaDon, JPanel showCTHD, JTextField jTMaHD, JTextField jTMaKH, JTextField jTMaNV, JTextField jTNgay, JTextField jTTienHD, JTextField jTMaHDCT,
            JTextField jTMaSPCT, JTextField jTSoLuongCT, JTextField jTGiaCT, JTextField jTTienCT, JButton jBReLoad, JButton jBDeleteb, JTabbedPane jTab) {
        this.showHoaDon = showHoaDon;
        this.showCTHD = showCTHD;
        this.jTMaHD = jTMaHD;
        this.jTMaKH = jTMaKH;
        this.jTMaNV = jTMaNV;
        this.jTNgay = jTNgay;
        this.jTTienHD = jTTienHD;
        this.jTMaHDCT = jTMaHDCT;
        this.jTMaSPCT = jTMaSPCT;
        this.jTSoLuongCT = jTSoLuongCT;
        this.jTGiaCT = jTGiaCT;
        this.jTTienCT = jTTienCT;
        this.hoaDonDAO = new HoaDonDAOImpl();
        this.jBReLoad = jBReLoad;
        this.jBDeleteb = jBDeleteb;
        this.jTab = jTab;

    }

    public void setDataToTable() {
        List<HoaDon> listItem = hoaDonDAO.getList();

        DefaultTableModel model = new ClassTableModel().setTableHoaDon(listItem, listColumn);
        JTable table = new JTable(model);
        rowSorter = new TableRowSorter<>(table.getModel());

        table.setRowSorter(rowSorter);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    jTMaHD.setText(model.getValueAt(selectedRowIndex, 1).toString());
                    jTMaKH.setText(model.getValueAt(selectedRowIndex, 3).toString());
                    jTMaNV.setText(model.getValueAt(selectedRowIndex, 2).toString());
                    jTNgay.setText(model.getValueAt(selectedRowIndex, 4).toString());
                    jTTienHD.setText(model.getValueAt(selectedRowIndex, 5).toString());

                    setDataToCTHD(Integer.parseInt(jTMaHD.getText()));
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

        showHoaDon.removeAll();
        showHoaDon.setLayout(new BorderLayout());
        showHoaDon.add(scrollPane);
        showHoaDon.validate();
        showHoaDon.repaint();
    }

    public void setDataToCTHD(int MaHD) {
        CTHDDAO cthdDAO = new CTHDDAOImpl();
        List<CTHD> listItem = cthdDAO.getList();
        List<CTHD> listCTHD = new ArrayList<>();
        String[] listColumnCTHD = {"STT", "Mã HD", "Mã SP", "Số lượng", "Đơn giá", "Thành tiền"};
        if (MaHD != -1) {
            for (CTHD a : listItem) {
                if (a.getMaHD().equals(MaHD)) {
                    listCTHD.add(a);
                }
            }
        }

        DefaultTableModel model = new ClassTableModel().setTableCTHD(listCTHD, listColumnCTHD);
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

                    jTMaHDCT.setText(model.getValueAt(selectedRowIndex, 1).toString());
                    jTMaSPCT.setText(model.getValueAt(selectedRowIndex, 2).toString());
                    jTSoLuongCT.setText(model.getValueAt(selectedRowIndex, 3).toString());
                    jTGiaCT.setText(model.getValueAt(selectedRowIndex, 4).toString());
                    jTTienCT.setText(model.getValueAt(selectedRowIndex, 5).toString());

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

        showCTHD.removeAll();
        showCTHD.setLayout(new BorderLayout());
        showCTHD.add(scrollPane);
        showCTHD.validate();
        showCTHD.repaint();
    }

    public void setEvent() {
        jBReLoad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setDataToTable();
            }
        });

        jBDeleteb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CTHDDAO cthdDAO = new CTHDDAOImpl();
                cthdDAO.deleteCTHD(Integer.parseInt(jTMaHD.getText()));
                HoaDonDAO hoaDonDAO = new HoaDonDAOImpl();
                hoaDonDAO.deleteHoaDon(Integer.parseInt(jTMaHD.getText()));
                setDataToTable();
                setDataToCTHD(-2);
            }

        });
        jTab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = jTab.getSelectedIndex();
                if (index == 1) {
                    setDataToTable();
                }
            }
        });
    }

}
