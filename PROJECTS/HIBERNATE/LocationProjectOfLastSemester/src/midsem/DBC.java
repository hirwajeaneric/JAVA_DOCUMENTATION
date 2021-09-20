/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midsem;

import java.sql.*;


/**
 *
 * @author AAAA
 */
public class DBC {

    /**
     * 
     */
    public String driver="com.mysql.jdbc.Driver";
    public String URL="jdbc:mysql://localhost:3306/mid_sem";
    public String user="root";
    public String passwd="";
    private Connection con;
    private static DBC dbc;
    public DBC()
    {
    
    
        try {
            Class.forName(driver);
             System.out.println("Driver loaded successfully!!");
              con= DriverManager.getConnection(URL,user,passwd);
              System.out.println("Connection established successfully!!");
        } catch (Exception ex) {
            System.out.println(ex);
        }
      
       
    
    
    
    }
    public static DBC getDBC(){
    if (dbc==null){
    dbc = new DBC();
    
    }
    
    return dbc;
    }
    public Connection getConnection(){
    return con;
    }
    
    

    
}
