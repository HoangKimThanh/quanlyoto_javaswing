/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import controller.QuanLyKhachHangController;
import controller.DangNhapController;
import dao.PhanQuyenDAO;
import dao.PhanQuyenDAOImpl;
import javax.swing.JButton;
import model.Quyen;

/**
 *
 * @author ASUS
 */
public class KhachHangJPanel extends javax.swing.JPanel {

    /**
     * Creates new form KhachHangJPanel
     */
    public KhachHangJPanel() {
        initComponents();
        DangNhapController controllerDangNhap = new DangNhapController();
        
        PhanQuyenDAO phanQuyenDAO = new PhanQuyenDAOImpl();
        Quyen quyen = phanQuyenDAO.getQuyen(controllerDangNhap.taiKhoanLogin.getChucVu(), 
                "Quản lý khách hàng");
        
        if (quyen.getCreate() == 0) {
            jBtnAdd.setVisible(false);
        }
        
        if (quyen.getUpdate()== 0) {
            jBtnUpdate.setVisible(false);
        }
        
        if (quyen.getDelete()== 0) {
            jBtnDelete.setVisible(false);
        }
        
        
        QuanLyKhachHangController controller = new QuanLyKhachHangController(
                jPnView, 
                jTfSeach, 
                jBtnAdd, 
                jBtnUpdate, 
                jBtnDelete, 
                jBtnReset,
                jBtnXuatExcel,
                jTfMaKH, 
                jTfHoTen, 
                jTfSDT, 
                jTaDiaChi
        );
        controller.setDataToTable();
        controller.setEvent();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLbSearch = new javax.swing.JLabel();
        jTfSeach = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLbMaKH = new javax.swing.JLabel();
        jTfMaKH = new javax.swing.JTextField();
        jLbHoTen = new javax.swing.JLabel();
        jTfHoTen = new javax.swing.JTextField();
        jLbSDT = new javax.swing.JLabel();
        jTfSDT = new javax.swing.JTextField();
        jLbDiaChi = new javax.swing.JLabel();
        jBtnAdd = new javax.swing.JButton();
        jBtnUpdate = new javax.swing.JButton();
        jBtnDelete = new javax.swing.JButton();
        jBtnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTaDiaChi = new javax.swing.JTextArea();
        jBtnXuatExcel = new javax.swing.JButton();
        jPnView = new javax.swing.JPanel();

        jLbSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbSearch.setText("Tìm kiếm");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jLbMaKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbMaKH.setText("Mã khách hàng");

        jTfMaKH.setEditable(false);
        jTfMaKH.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLbHoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbHoTen.setText("Họ tên khách hàng");

        jTfHoTen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLbSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbSDT.setText("Số điện thoại");

        jLbDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLbDiaChi.setText("Địa chỉ");

        jBtnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnAdd.setText("Thêm");

        jBtnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnUpdate.setText("Lưu");
        jBtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnUpdateActionPerformed(evt);
            }
        });

        jBtnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnDelete.setText("Xóa");
        jBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteActionPerformed(evt);
            }
        });

        jBtnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnReset.setText("Reset");
        jBtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnResetActionPerformed(evt);
            }
        });

        jTaDiaChi.setColumns(20);
        jTaDiaChi.setRows(5);
        jScrollPane1.setViewportView(jTaDiaChi);

        jBtnXuatExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnXuatExcel.setText("Xuất Excel");
        jBtnXuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnXuatExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLbMaKH)
                            .addComponent(jLbDiaChi)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLbHoTen)
                                    .addComponent(jLbSDT))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                                    .addComponent(jTfHoTen)
                                    .addComponent(jTfMaKH)
                                    .addComponent(jTfSDT)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jBtnAdd)
                        .addGap(28, 28, 28)
                        .addComponent(jBtnUpdate)
                        .addGap(34, 34, 34)
                        .addComponent(jBtnDelete)
                        .addGap(31, 31, 31)
                        .addComponent(jBtnReset)
                        .addGap(34, 34, 34)
                        .addComponent(jBtnXuatExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbMaKH)
                    .addComponent(jTfMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbHoTen)
                    .addComponent(jTfHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbSDT)
                    .addComponent(jTfSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLbDiaChi)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnAdd)
                    .addComponent(jBtnUpdate)
                    .addComponent(jBtnDelete)
                    .addComponent(jBtnReset)
                    .addComponent(jBtnXuatExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
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
            .addGap(0, 175, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLbSearch)
                .addGap(103, 103, 103)
                .addComponent(jTfSeach, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPnView, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTfSeach, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLbSearch))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnUpdateActionPerformed

    private void jBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnDeleteActionPerformed

    private void jBtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnResetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnResetActionPerformed

    private void jBtnXuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnXuatExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnXuatExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAdd;
    private javax.swing.JButton jBtnDelete;
    private javax.swing.JButton jBtnReset;
    private javax.swing.JButton jBtnUpdate;
    private javax.swing.JButton jBtnXuatExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLbDiaChi;
    private javax.swing.JLabel jLbHoTen;
    private javax.swing.JLabel jLbMaKH;
    private javax.swing.JLabel jLbSDT;
    private javax.swing.JLabel jLbSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPnView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTaDiaChi;
    private javax.swing.JTextField jTfHoTen;
    private javax.swing.JTextField jTfMaKH;
    private javax.swing.JTextField jTfSDT;
    private javax.swing.JTextField jTfSeach;
    // End of variables declaration//GEN-END:variables
}
