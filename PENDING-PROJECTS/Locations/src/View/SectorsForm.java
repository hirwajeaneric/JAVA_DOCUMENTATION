package View;

import Controller.SectorDao;
import Model.Sectors;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hirwa
 */
public class SectorsForm extends javax.swing.JInternalFrame {

    //Global Variables
    Sectors sector = new Sectors();
    SectorDao sectorDao = new SectorDao();
    DefaultTableModel model;
    
    public SectorsForm() {
        initComponents();
        updateSectorTable();
    }

    public void updateSectorTable() {
            model = (DefaultTableModel) SectorTable.getModel();
            model.setRowCount(0);
            
            List<Sectors> listOfSectors = sectorDao.findAll(sector);
            
            for (Sectors sectors : listOfSectors) {
                model.insertRow(model.getRowCount(), new Object[] {
                    sector.getId(),
                    sector.getName()
                });
        }
    }
    
    public void resetFields() {
        sectorNameField.setText("");
        sectorIdField.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sectorNameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        sectorIdField = new javax.swing.JComboBox<>();
        SaveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        SectorTable = new javax.swing.JTable();
        SectorsUpdateButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Sectors");

        jLabel1.setText("Sector Name");

        jLabel3.setText("Sector Id");

        sectorIdField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        SectorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "District Name", "District Id"
            }
        ));
        jScrollPane1.setViewportView(SectorTable);

        SectorsUpdateButton.setText("Update");
        SectorsUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SectorsUpdateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(31, 31, 31)
                                .addComponent(sectorIdField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sectorNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(SaveButton)
                        .addGap(18, 18, 18)
                        .addComponent(SectorsUpdateButton)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(sectorNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(sectorIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(SectorsUpdateButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        sector.setId(sectorNameField.getText());
        sector.setId(sectorIdField.getSelectedItem().toString());
        
        sectorDao.save(sector);
        updateSectorTable();
        resetFields();
        
        JOptionPane.showMessageDialog(this, "Sector saved Successfully!");
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void SectorsUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SectorsUpdateButtonActionPerformed
        
    }//GEN-LAST:event_SectorsUpdateButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SaveButton;
    private javax.swing.JTable SectorTable;
    private javax.swing.JButton SectorsUpdateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> sectorIdField;
    private javax.swing.JTextField sectorNameField;
    // End of variables declaration//GEN-END:variables
}
