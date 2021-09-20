/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author hirwa
 */
public class ReadDataFromExcel {
    public static void main(String[] args) throws IOException {
        
        //Opening the session and taking data from the file.
        Session session = HibernateUtil.getSessionFactory().openSession();
        FileInputStream file = new FileInputStream(new File("/home/hirwa/NetBeansProjects/04-Import-Export-Files/excelfile.xlsx"));
        
        //Creating the workbook
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        
        //Creating the sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row;
        for(int i=1; i<=sheet.getLastRowNum(); i++){//Points to the starting of excel i.e excel first row
            row = (Row) sheet.getRow(i); //Sheet number
            
            String id;
            if(row.getCell(0)==null) id = "0";
            else id = row.getCell(0).toString();
            
            String name;
            if(row.getCell(1)== null){ name = "null";} //Suppose excel cell is empty then its set to 0 the variable
            else name = row.getCell(1).toString(); //else copies cell data to name variable
            
            String address;
            if(row.getCell(2)== null) { address = "null"; } 
            else address = row.getCell(2).toString();
            
            //From here we start writing the data into the workbook
            Transaction t = session.beginTransaction();
            Student std = new Student();
            std.setId(Double.parseDouble(id));
            std.setName(name);
            std.setAddress(address);
            System.out.println(std.getId()+" "+std.getName()+" "+std.getAddress());
            session.saveOrUpdate(std);
            t.commit();
        }
        file.close();
    }
}
