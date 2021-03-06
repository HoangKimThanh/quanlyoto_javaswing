/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.PhanQuyenController;
import controller.QuanLyNhanVienController;
import controller.DangNhapController;
import dao.PhanQuyenDAO;
import dao.PhanQuyenDAOImpl;
import model.Quyen;

/**
 *
 * @author Home
 */
public class NhanVienJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienJPanel
     */
    public NhanVienJPanel() {
        initComponents();
        
        DangNhapController controllerDangNhap = new DangNhapController();
        
        PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAOImpl();
        Quyen quyen = phanQuyenDAO.getQuyen(controllerDangNhap.taiKhoanLogin.getChucVu(), 
                "Quản lý nhân viên");
        
        if (quyen.getCreate() == 0) {
            jBtnAdd.setVisible(false);
        }
        
        if (quyen.getUpdate()== 0) {
            jBtnUpdate.setVisible(false);
        }
        
        if (quyen.getDelete()== 0) {
            jBtnDelete.setVisible(false);
        }
        
        QuanLyNhanVienController controller = new QuanLyNhanVienController(
                jPnView, 
                jBtnAdd, 
                jBtnUpdate,
                jBtnDelete, 
                jBtnReset,
                jBtnXuatExcel,
                jTfMaNv, 
                jTfHoTen,
                jCbChucVu1,
                jTfTaiKhoan,
                jTfMatKhau, 
                jTfSeach
        );
        PhanQuyenController controllerPhanQuyen = new PhanQuyenController(
                jCbChucVu,
                jCbQuyen,
                jCheckBoxRead,
                jCheckBoxCreate,
                jCheckBoxUpdate,
                jCheckBoxDelete,
                jBtnUpdateAll
        );
        controller.setDataToTable();
        controller.setEvent();
        controllerPhanQuyen.setEvent();
        
        if (controllerDangNhap.taiKhoanLogin.getChucVu().equals("Quản trị")) {
            jTabbedPane1.setEnabled(true);
        }
    }
  

  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPnThongTinTK = new javax.swing.JPanel();
        jPnTabNhânViên = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTfSeach = new javax.swing.JTextField();
        jLbSearch = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLbHoTen = new javax.swing.JLabel();
        jTfHoTen = new javax.swing.JTextField();
        jLbChucVu = new javax.swing.JLabel();
        jBtnAdd = new javax.swing.JButton();
        jLbMaNv = new javax.swing.JLabel();
        jTfMaNv = new javax.swing.JTextField();
        jBtnUpdate = new javax.swing.JButton();
        jBtnDelete = new javax.swing.JButton();
        jBtnReset = new javax.swing.JButton();
        jLbChucVu1 = new javax.swing.JLabel();
        jTfTaiKhoan = new javax.swing.JTextField();
        jLbChucVu2 = new javax.swing.JLabel();
        jCbChucVu1 = new javax.swing.JComboBox();
        jTfMatKhau = new javax.swing.JTextField();
        jBtnXuatExcel = new javax.swing.JButton();
        jPnView = new javax.swing.JPanel();
        jPnQuyen = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPnPhanQuyen = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jCbChucVu = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jCbQuyen = new javax.swing.JComboBox();
        jBtnUpdateAll = new javax.swing.JButton();
        jCheckBoxRead = new javax.swing.JCheckBox();
        jCheckBoxCreate = new javax.swing.JCheckBox();
        jCheckBoxUpdate = new javax.swing.JCheckBox();
        jCheckBoxDelete = new javax.swing.JCheckBox();

        jTabbedPane1.setEnabled(false);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1176, 1200));

        jPnTabNhânViên.setMaximumSize(new java.awt.Dimension(5000, 5000));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("QUẢN LÝ NHÂN VIÊN");

        jTfSeach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTfSeachActionPerformed(evt);
            }
        });

        jLbSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbSearch.setText("Tìm kiếm");

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        jLbHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbHoTen.setText("Tên nhân viên");

        jTfHoTen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLbChucVu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbChucVu.setText("Chức vụ");

        jBtnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnAdd.setText("Thêm");

        jLbMaNv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbMaNv.setText("Mã nhân viên");

        jTfMaNv.setEditable(false);
        jTfMaNv.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jBtnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnUpdate.setText("Lưu");
        jBtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUpdate6ActionPerformed(evt);
            }
        });

        jBtnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnDelete.setText("Xóa");
        jBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDelete6ActionPerformed(evt);
            }
        });

        jBtnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnReset.setText("Reset");
        jBtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnReset6ActionPerformed(evt);
            }
        });

        jLbChucVu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbChucVu1.setText("Tài khoản");

        jLbChucVu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbChucVu2.setText("Mật khẩu");

        jCbChucVu1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nhân viên" }));
        jCbChucVu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbChucVu1ActionPerformed(evt);
            }
        });

        jBtnXuatExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnXuatExcel.setText("Xuất Excel");
        jBtnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnXuatExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jBtnAdd)
                        .addGap(27, 27, 27)
                        .addComponent(jBtnUpdate)
                        .addGap(32, 32, 32)
                        .addComponent(jBtnDelete)
                        .addGap(28, 28, 28)
                        .addComponent(jBtnReset)
                        .addGap(42, 42, 42)
                        .addComponent(jBtnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLbChucVu)
                            .addComponent(jLbMaNv)
                            .addComponent(jLbHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLbChucVu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLbChucVu2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTfMaNv)
                            .addComponent(jTfHoTen)
                            .addComponent(jCbChucVu1, 0, 576, Short.MAX_VALUE)
                            .addComponent(jTfTaiKhoan)
                            .addComponent(jTfMatKhau))))
                .addGap(29, 29, 29))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbMaNv)
                    .addComponent(jTfMaNv, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbHoTen)
                    .addComponent(jTfHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jCbChucVu1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbChucVu))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbChucVu1)
                    .addComponent(jTfTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbChucVu2)
                    .addComponent(jTfMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAdd)
                    .addComponent(jBtnUpdate)
                    .addComponent(jBtnDelete)
                    .addComponent(jBtnReset)
                    .addComponent(jBtnXuatExcel))
                .addGap(14, 14, 14))
        );

        jPnView.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPnViewLayout = new javax.swing.GroupLayout(jPnView);
        jPnView.setLayout(jPnViewLayout);
        jPnViewLayout.setHorizontalGroup(
            jPnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPnViewLayout.setVerticalGroup(
            jPnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPnTabNhânViênLayout = new javax.swing.GroupLayout(jPnTabNhânViên);
        jPnTabNhânViên.setLayout(jPnTabNhânViênLayout);
        jPnTabNhânViênLayout.setHorizontalGroup(
            jPnTabNhânViênLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTabNhânViênLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPnTabNhânViênLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(jPnTabNhânViênLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLbSearch)
                .addGap(92, 92, 92)
                .addComponent(jTfSeach, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPnTabNhânViênLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPnTabNhânViênLayout.setVerticalGroup(
            jPnTabNhânViênLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnTabNhânViênLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addGroup(jPnTabNhânViênLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTfSeach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbSearch))
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPnThongTinTKLayout = new javax.swing.GroupLayout(jPnThongTinTK);
        jPnThongTinTK.setLayout(jPnThongTinTKLayout);
        jPnThongTinTKLayout.setHorizontalGroup(
            jPnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongTinTKLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPnTabNhânViên, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPnThongTinTKLayout.setVerticalGroup(
            jPnThongTinTKLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnThongTinTKLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPnTabNhânViên, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nhân viên", jPnThongTinTK);

        jPnQuyen.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("QUẢN LÝ PHÂN QUYỀN");

        jPnPhanQuyen.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Thông tin các quyền");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Chức vụ:");

        jCbChucVu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Chọn chức vụ --", "Quản lý", "Nhân viên" }));
        jCbChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbChucVuActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Nhóm quyền:");

        jCbQuyen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Chọn quyền --", "Quản lý sản phẩm", "Quản lý hóa đơn", "Quản lý nhân viên", "Quản lý khách hàng", "Quản lý nhập hàng", "Quản lý nhà cung cấp", "Thống kê" }));
        jCbQuyen.setEnabled(false);
        jCbQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCbQuyenActionPerformed(evt);
            }
        });

        jBtnUpdateAll.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnUpdateAll.setText("Cập nhật quyền");

        jCheckBoxRead.setText("Xem");
        jCheckBoxRead.setEnabled(false);
        jCheckBoxRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxReadActionPerformed(evt);
            }
        });

        jCheckBoxCreate.setText("Thêm");
        jCheckBoxCreate.setEnabled(false);

        jCheckBoxUpdate.setText("Sửa");
        jCheckBoxUpdate.setEnabled(false);

        jCheckBoxDelete.setText("Xóa");
        jCheckBoxDelete.setEnabled(false);

        javax.swing.GroupLayout jPnPhanQuyenLayout = new javax.swing.GroupLayout(jPnPhanQuyen);
        jPnPhanQuyen.setLayout(jPnPhanQuyenLayout);
        jPnPhanQuyenLayout.setHorizontalGroup(
            jPnPhanQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnPhanQuyenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnPhanQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPnPhanQuyenLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addGroup(jPnPhanQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnPhanQuyenLayout.createSequentialGroup()
                                .addGroup(jPnPhanQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxRead)
                                    .addComponent(jCheckBoxDelete)
                                    .addComponent(jCheckBoxUpdate))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPnPhanQuyenLayout.createSequentialGroup()
                                .addComponent(jCheckBoxCreate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBtnUpdateAll))))
                    .addGroup(jPnPhanQuyenLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jCbQuyen, 0, 301, Short.MAX_VALUE))
                    .addGroup(jPnPhanQuyenLayout.createSequentialGroup()
                        .addGroup(jPnPhanQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPnPhanQuyenLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jCbChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(333, 333, 333))
        );
        jPnPhanQuyenLayout.setVerticalGroup(
            jPnPhanQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnPhanQuyenLayout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(jPnPhanQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jCbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPnPhanQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jCbQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxRead)
                .addGap(20, 20, 20)
                .addGroup(jPnPhanQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxCreate)
                    .addComponent(jBtnUpdateAll))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxUpdate)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxDelete)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPnQuyenLayout = new javax.swing.GroupLayout(jPnQuyen);
        jPnQuyen.setLayout(jPnQuyenLayout);
        jPnQuyenLayout.setHorizontalGroup(
            jPnQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnQuyenLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPnQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPnPhanQuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52))
        );
        jPnQuyenLayout.setVerticalGroup(
            jPnQuyenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnQuyenLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPnPhanQuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quyền", jPnQuyen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnReset6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnReset6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnReset6ActionPerformed

    private void jBtnDelete6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDelete6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnDelete6ActionPerformed

    private void jBtnUpdate6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUpdate6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnUpdate6ActionPerformed

    private void jTfSeachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTfSeachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTfSeachActionPerformed

    private void jCbChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbChucVuActionPerformed

    private void jCbQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbQuyenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbQuyenActionPerformed

    private void jCheckBoxReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxReadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxReadActionPerformed

    private void jCbChucVu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCbChucVu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCbChucVu1ActionPerformed

    private void jBtnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnXuatExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnXuatExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAdd;
    private javax.swing.JButton jBtnDelete;
    private javax.swing.JButton jBtnReset;
    private javax.swing.JButton jBtnUpdate;
    private javax.swing.JButton jBtnUpdateAll;
    private javax.swing.JButton jBtnXuatExcel;
    private javax.swing.JComboBox jCbChucVu;
    private javax.swing.JComboBox jCbChucVu1;
    private javax.swing.JComboBox jCbQuyen;
    private javax.swing.JCheckBox jCheckBoxCreate;
    private javax.swing.JCheckBox jCheckBoxDelete;
    private javax.swing.JCheckBox jCheckBoxRead;
    private javax.swing.JCheckBox jCheckBoxUpdate;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLbChucVu;
    private javax.swing.JLabel jLbChucVu1;
    private javax.swing.JLabel jLbChucVu2;
    private javax.swing.JLabel jLbHoTen;
    private javax.swing.JLabel jLbMaNv;
    private javax.swing.JLabel jLbSearch;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPnPhanQuyen;
    private javax.swing.JPanel jPnQuyen;
    private javax.swing.JPanel jPnTabNhânViên;
    private javax.swing.JPanel jPnThongTinTK;
    private javax.swing.JPanel jPnView;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTfHoTen;
    private javax.swing.JTextField jTfMaNv;
    private javax.swing.JTextField jTfMatKhau;
    private javax.swing.JTextField jTfSeach;
    private javax.swing.JTextField jTfTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
