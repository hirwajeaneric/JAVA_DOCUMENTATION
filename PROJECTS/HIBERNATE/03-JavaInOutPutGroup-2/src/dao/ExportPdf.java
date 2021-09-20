/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.*;
import javax.swing.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hirwa
 */
public class ExportPdf {
    
    public ExportPdf(){
    
    }
    //This method is for creating the name of the file.
    public String getFileName(String baseName){
        DateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateTimeInfo = dateformat.format(new Date());
        return baseName.concat(String.format("_%s.pdf",dateTimeInfo));
    }
    //This method is for doing the export file
    public void exportPdfData(JTable clientTable){
        
        String path = getFileName("client".concat("_Export"));
        
        try {
            DefaultTableModel mo = (DefaultTableModel) clientTable.getModel();
            String col[] = {mo.getColumnName(0), mo.getColumnName(1), mo.getColumnName(2), mo.getColumnName(3), mo.getColumnName(4), mo.getColumnName(5), mo.getColumnName(6)};
            
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            document.addTitle("Information from Client");
            
            PdfPTable tab = new PdfPTable(col.length);
            tab.setWidthPercentage(100);
            for (int i = 0; i < col.length; i++) {
                tab.addCell(col[i]);
            }
            tab.completeRow();
            for (int i = 0; i < mo.getRowCount(); i++) {
                for(int j = 0; j < mo.getColumnCount(); j++){
                    tab.addCell(mo.getValueAt(i, j).toString());
                }
                tab.completeRow();
            }
            document.add(tab);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            Logger.getLogger(ExportPdf.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
