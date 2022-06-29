/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobagui;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aldino
 */
public class frmhs extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmhs
     */
    private DefaultTableModel model;
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    dbkoneksi cnn = new dbkoneksi();
    loadIMG img = new loadIMG();
    
    public frmhs() {
    initComponents();
    this.cmdBatal.setVisible(false);
    this.initTable();
    this.getdata();
    this.navbar(false);
    this.cmdClose.setEnabled(true);
    }
    
    
    private void initTable(){
        model = new DefaultTableModel();
        tMHS.setModel(model);
        model.addColumn("NIM");
        model.addColumn("Nama");
        model.addColumn("Tgl Lahir");
        model.addColumn("Jurusan");
        model.addColumn("Jenis Kelamin");
    }


private void getdata(){
    String sql = "SELECT NIM, NAMA, JURUSAN, TGLLAHIR, JK FROM mhs";
    model.getDataVector().removeAllElements();
    model.fireTableDataChanged();
    try{
        this.tampildata(sql);
        while(this.rs.next()){
            Object[] obj = new Object[5];
            obj[0] = this.rs.getString("NIM");
            obj[1] = this.rs.getString("NAMA");
            obj[2] = this.rs.getString("TGLLAHIR");
            obj[3] = this.rs.getString("JURUSAN");
            String txJK = this.rs.getString("JK");
            if(txJK.equals("L")){
                obj[4] = "Laki-Laki";
            }else{
                obj[4] = "Perempuan";
            }
            model.addRow(obj);
            
        }
        stmt.close();
        cnn.koneksi().close();
    }catch(Exception err){
    
    }
}

    private void tampildata(String sql){
        try{
            Statement stmt = cnn.koneksi().createStatement();
            this.rs = stmt.executeQuery(sql);
        }catch(Exception e){
            System.out.println("terjadi kendala pada query");
        }
    }
    
    private boolean updatedata(String sql){
        boolean hsl = false;
        try{
            Statement stmt = cnn.koneksi().createStatement();
            stmt.executeQuery(sql);
            hsl = true;
        }catch(Exception e){
            System.out.println("terjadi kendala pada query");
            JOptionPane.showMessageDialog(null, "terjadi kendala pada proses store/update data");
        }
        return hsl;
    }
    
    private void storedata(){
        String NIM = this.txNIM.getText();
        String NAMA = this.txNAMA.getText();
        String JURUSAN = this.txJUR.getSelectedItem().toString();
        String JK = "P";
        if(this.txJKL.isSelected()){
            JK = "L";
        }
        String TGLLAHIR = this.txTGL.getText();
        String sql = "INSERT INTO mhs(NIM, NAMA, JURUSAN, JK, TGLLAHIR) VALUES('"+NIM+"','"+NAMA+"','"+JURUSAN+"','"+JK+"','"+TGLLAHIR+"');";
        if(this.updatedata(sql)){
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        }
        this.getdata();
        this.cmdStore.setEnabled(false);
        this.cmdBatal.setVisible(false);
    }
    
    private void updatedataform(){
        String NIM = this.txNIM.getText();
        String NAMA = this.txNAMA.getText();
        String JURUSAN = this.txJUR.getSelectedItem().toString();
        String JK = "P";
        if(this.txJKL.isSelected()){
            JK = "L";
        }
        String TGLLAHIR = this.txTGL.getText();
        String sql = "UPDATE mhs SET NAMA='"+NAMA+"', JURUSAN='"+JURUSAN+"', JK='"+JK+"', TGLLAHIR='"+TGLLAHIR+"' WHERE NIM='"+NIM+"';";
        if(this.updatedata(sql)){
            JOptionPane.showMessageDialog(null, "Data mahasiswa "+NAMA+" ("+NIM+")\ntelah di update");
        }
        this.getdata();
    }
    
    private void Deletedata(){
        String NIM = this.txNIM.getText();
        String NAMA = this.txNAMA.getText();
        
        String sql = "DELETE FROM mhs WHERE NIM='"+NIM+"';";
        
        int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data dari:\n"+NAMA+" ("+NIM+")\n", "Penghapusan Data", JOptionPane.YES_NO_OPTION);
        if(opsi == JOptionPane.YES_OPTION){
            if(this.updatedata(sql)){
            JOptionPane.showMessageDialog(null, "Data mahasiswa"+NAMA+" ("+NIM+")\nTelah dihapus");
            }
            this.getdata();
        }
    }
   
    
    private void clearform(){
        this.txNIM.setText("");
        this.txNAMA.setText("");
        this.txTGL.setText("");
        this.txJKL.setSelected(false);
        this.txJKP.setSelected(false);
        this.txJUR.setSelectedIndex(0);
        this.navbar(false);
        this.cmdStore.setEnabled(true);
        this.cmdBatal.setVisible(true);
    }
    
    private void navbar(Boolean tf){
        this.cmdStore.setEnabled(tf);
        this.cmdUpdate.setEnabled(tf);
        this.cmdDelete.setEnabled(tf);
        this.cmdClose.setEnabled(tf);
    }
    
    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt){
        this.dispose();
    }
    
    private void cmdBatalActionPerformed(java.awt.event.ActionEvent evt){
        this.navbar(false);
        this.cmdBatal.setVisible(false);
        this.cmdClose.setEnabled(true);
    }
   
    private void cmdStoreActionPerformed(java.awt.event.ActionEvent evt){
        this.storedata();
    }
    
    private void cmdUpdateActionPerformed(java.awt.event.ActionEvent evt){
        this.updatedataform();
    }
    
    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt){
        this.Deletedata();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmdBaru = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tMHS = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txNIM = new javax.swing.JTextField();
        txNAMA = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txJKL = new javax.swing.JRadioButton();
        txJKP = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        txTGL = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txJUR = new javax.swing.JComboBox<>();
        cmdStore = new javax.swing.JButton();
        cmdUpdate = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdBatal = new javax.swing.JButton();
        cmdClose = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("LIST DATA MAHASISWA");

        cmdBaru.setText("DataBaru");

        tMHS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tMHS);

        jLabel2.setText("Edit Data");

        jLabel3.setText(" NIM");

        jLabel4.setText("NAMA Mahasiswa");

        jLabel5.setText("Jenis Kelamin");

        txJKL.setText("Laki-Laki");
        txJKL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txJKLActionPerformed(evt);
            }
        });

        txJKP.setText("Perempuan");

        jLabel6.setText("TGL Lahir");

        jLabel7.setText("Jurusan");

        txJUR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Jurusan", "TI-KAB", "TI-DGM", "TI-MTI", "SK" }));

        cmdStore.setText("Simpan Data");

        cmdUpdate.setText("Update Data");

        cmdDelete.setText("Hapus Data");

        cmdBatal.setText("Batal");

        cmdClose.setText("Tutup Data Mahasiswa");

        jLabel8.setText("Gambar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdBaru))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdStore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDelete)
                        .addGap(18, 18, 18)
                        .addComponent(cmdBatal)
                        .addGap(18, 18, 18)
                        .addComponent(cmdClose))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txNIM, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(jLabel4)
                            .addComponent(txNAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txJKL)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txTGL))
                            .addComponent(txJKP)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txJUR, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmdBaru))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txNIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txJKL)
                            .addComponent(jLabel6)
                            .addComponent(txTGL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txJKP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txNAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txJUR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdStore)
                    .addComponent(cmdUpdate)
                    .addComponent(cmdDelete)
                    .addComponent(cmdBatal)
                    .addComponent(cmdClose))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txJKLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txJKLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txJKLActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmhs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmhs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdBaru;
    private javax.swing.JButton cmdBatal;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdStore;
    private javax.swing.JButton cmdUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tMHS;
    private javax.swing.JRadioButton txJKL;
    private javax.swing.JRadioButton txJKP;
    private javax.swing.JComboBox<String> txJUR;
    private javax.swing.JTextField txNAMA;
    private javax.swing.JTextField txNIM;
    private javax.swing.JTextField txTGL;
    // End of variables declaration//GEN-END:variables
}
