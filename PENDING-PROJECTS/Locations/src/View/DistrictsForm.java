package View;

import java.util.List;
import Controller.DistrictDao;
import Model.Districts;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hirwa
 */
public class DistrictsForm extends javax.swing.JInternalFrame {

    //Global variables
    DefaultTableModel model;
    Districts districts = new Districts();
    DistrictDao districtDao = new DistrictDao();
    
    public DistrictsForm() {
        initComponents();
        updateTableDistrict();
    }

    //Displaying data to the table
    public void updateTableDistrict() {
           model = (DefaultTableModel) DistrictTable.getModel();
           model.setRowCount(0);
           
           List<Districts> listOfDistricts = districtDao.findAll(districts);
           
           for (Districts districts : listOfDistricts) {
               model.insertRow(model.getRowCount(), new Object[] {
                   districts.getId(),
                   districts.getName(),
                   districts.getProvince()
               });
           }
    }
    
    //Updating input components to empty
    public void resetFields(){
        DistrictIdComboBox.setSelectedIndex(0);
        DistrictNameField.setText("");
        ProvinceComboBox.setSelectedIndex(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        DistrictNameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        DistrictIdComboBox = new javax.swing.JComboBox<>();
        SaveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DistrictTable = new javax.swing.JTable();
        DistrictUpdateButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ProvinceComboBox = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Districts");

        jLabel1.setText("District name");

        jLabel3.setText("District Id");

        DistrictIdComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        DistrictTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "District Id", "District Name", "Province"
            }
        ));
        DistrictTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DistrictTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(DistrictTable);

        DistrictUpdateButton.setText("Update");
        DistrictUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DistrictUpdateButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Province");

        ProvinceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kigali", "North", "South", "East", "West" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(DistrictIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SaveButton)
                            .addComponent(jLabel1))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(DistrictUpdateButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ProvinceComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DistrictNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(DistrictIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(DistrictNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ProvinceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton)
                    .addComponent(DistrictUpdateButton))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
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

    //Save Button actions
    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        districts.setId(DistrictIdComboBox.getSelectedItem().toString());
        districts.setName(DistrictNameField.getText());
        districts.setProvince(ProvinceComboBox.getSelectedItem().toString());
        
        districtDao.save(districts);
        updateTableDistrict();
        resetFields();
        
        JOptionPane.showMessageDialog(this, "Districts Successfully saved!");
    }//GEN-LAST:event_SaveButtonActionPerformed

    //Update Button actions
    private void DistrictUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DistrictUpdateButtonActionPerformed
        districts.setId(DistrictIdComboBox.getSelectedItem().toString());
        districts.setName(DistrictNameField.getText());
        districts.setProvince(ProvinceComboBox.getSelectedItem().toString());
        
        districtDao.update(districts);
        updateTableDistrict();
        resetFields();
        
        JOptionPane.showMessageDialog(this, "Districts Successfully Updated!");
    }//GEN-LAST:event_DistrictUpdateButtonActionPerformed

    //When we click on the table
    private void DistrictTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DistrictTableMouseClicked
        // TODO add your handling code here:
        model = (DefaultTableModel) DistrictTable.getModel();
        int row = DistrictTable.getSelectedRow();
        
        DistrictIdComboBox.setSelectedItem(model.getValueAt(row, 0).toString());
        DistrictNameField.setText((String) model.getValueAt(row, 1));
        ProvinceComboBox.setSelectedItem(model.getValueAt(row, 2).toString());
    }//GEN-LAST:event_DistrictTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DistrictIdComboBox;
    private javax.swing.JTextField DistrictNameField;
    private javax.swing.JTable DistrictTable;
    private javax.swing.JButton DistrictUpdateButton;
    private javax.swing.JComboBox<String> ProvinceComboBox;
    private javax.swing.JButton SaveButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
