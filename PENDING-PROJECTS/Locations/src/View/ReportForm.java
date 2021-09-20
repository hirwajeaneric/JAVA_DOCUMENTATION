package View;

import Controller.*;
import Model.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hirwa
 */
public class ReportForm extends javax.swing.JInternalFrame {
    
    //Global variables
    Report rp = new Report();
    Districts districts = new Districts();
    DistrictDao districtDao = new DistrictDao();
    Sectors sectors = new Sectors();
    SectorDao sectorDao = new SectorDao();
    ReportDao reportDao = new ReportDao();
    DefaultTableModel model;
    
    public ReportForm() {
        initComponents();
        updateReportTable();
        updateDistrictComboBox();
        updateSectorComboBox();
    }
    //Method that updates table data
    public void updateReportTable() {
        model = (DefaultTableModel) ReportTable.getModel();
        model.setRowCount(0);
        
        List<Report> reportList = reportDao.findAll(rp);
        
        for (Report report : reportList) {
            model.insertRow(model.getRowCount(), new Object[]{
                report.getName(),
                report.getId(),
                report.getProvince(),
                report.getDistrict(),
                report.getSector()
            });
        }
    }

    //Method that updates data in the District Combo Box
    public void updateDistrictComboBox() {
        List<Districts> allofthem = districtDao.findAll(districts);
        DistrictComboBox.removeAllItems();
        
        String choosenProvince = DistrictComboBox.getSelectedItem().toString();
        /*
        if("Kigali".equals(DistrictComboBox.getSelectedItem().toString())){
            choosenProvince = "0";
        }else if ("North".equals(DistrictComboBox.getSelectedItem().toString())) {
            choosenProvince = "1";
        }else if("South".equals(DistrictComboBox.getSelectedItem().toString())){
            choosenProvince = "2";
        }else if ("East".equals(DistrictComboBox.getSelectedItem().toString())) {
            choosenProvince = "3";
        }else if ("West".equals(DistrictComboBox.getSelectedItem().toString())) {
            choosenProvince = "4";
        }*/
        /*
        for (Districts districts : allofthem) {
            if(districts.getId().equalsIgnoreCase(choosenProvince)){
                DistrictComboBox.addItem(districts.getName());
            }
        }
        */
        allofthem.forEach((Districts dis)-> {
            if(dis.getId().equalsIgnoreCase(choosenProvince)){
                DistrictComboBox.addItem(dis.getName());
            }
        });
    }
    
    //Method that updates data in the District Combo Box
    public void updateSectorComboBox() {
        
    
    }
    
    //Method that resets fields after saving
    public void resetFields() {
        nameField.setText("");
        idField.setText("");
        ProvinceComboBox.setSelectedIndex(0);
        DistrictComboBox.setSelectedIndex(0);
        SectorComboBox.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ProvinceComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SectorComboBox = new javax.swing.JComboBox<>();
        SaveButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ReportTable = new javax.swing.JTable();
        DistrictComboBox = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Report Form");

        jLabel1.setText("Name");

        jLabel2.setText("Id");

        jLabel3.setText("Province");

        ProvinceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kigali", "North", "South", "East", "West" }));
        ProvinceComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ProvinceComboBoxItemStateChanged(evt);
            }
        });

        jLabel4.setText("District");

        jLabel5.setText("Sector");

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        ReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Id", "Province", "District", "Sector"
            }
        ));
        jScrollPane1.setViewportView(ReportTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProvinceComboBox, 0, 153, Short.MAX_VALUE)
                            .addComponent(SectorComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameField)
                            .addComponent(idField)
                            .addComponent(DistrictComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(SaveButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ProvinceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(DistrictComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(SectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SaveButton)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    //Saving data from input components.
    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        rp.setName(nameField.getText());
        rp.setId(idField.getText());
        rp.setProvince(ProvinceComboBox.getSelectedItem().toString());
        rp.setDistrict(DistrictComboBox.getSelectedItem().toString());
        rp.setSector(SectorComboBox.getSelectedItem().toString());
        
        reportDao.save(rp);
        updateReportTable();
        resetFields();
        JOptionPane.showMessageDialog(this, "Client Saved!");
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void ProvinceComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ProvinceComboBoxItemStateChanged
        updateDistrictComboBox();
    }//GEN-LAST:event_ProvinceComboBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DistrictComboBox;
    private javax.swing.JComboBox<String> ProvinceComboBox;
    private javax.swing.JTable ReportTable;
    private javax.swing.JButton SaveButton;
    private javax.swing.JComboBox<String> SectorComboBox;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nameField;
    // End of variables declaration//GEN-END:variables
}
