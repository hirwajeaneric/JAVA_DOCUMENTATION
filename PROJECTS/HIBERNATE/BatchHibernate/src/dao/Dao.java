/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Department;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class Dao{
    private Connection conn=null;
    private Session session =null;
    private Transaction tx=null;
    
    public void saveDept(List<Department> departments){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            int batchSize = 200;
            int count=0;
            
            for(Department dept: departments){
                session.save(dept);
                count++;
                if(count%batchSize == 0){
                    tx.commit();
                    tx = session.beginTransaction();
                }
            }
            if(tx!=null){
                tx.commit();
            }
            JOptionPane.showMessageDialog(null, "Departments have been saved successfully!");
        }catch(HibernateException ex){
            if(tx!=null){
                tx.rollback();
            }
            JOptionPane.showMessageDialog(null, ex);
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }
    
    public List<Department> retrieveDepts(){
        List<Department> departments = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            departments = session.createQuery("FROM Department").list();
        }catch(HibernateException ex){
           JOptionPane.showMessageDialog(null, ex);
        }finally{
           if(session!=null){
               session.close();
           } 
        }
        
        return departments;
    }
}