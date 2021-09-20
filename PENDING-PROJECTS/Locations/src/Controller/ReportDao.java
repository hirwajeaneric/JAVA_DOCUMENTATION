/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Model.Report;
import Util.HibernateUtil;

/**
 *
 * @author hirwa
 */
public class ReportDao {
    Session ss = null;
    Transaction tx = null;
    
    
    public Report save(Report rp){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.save(rp);
        tx.commit();
        ss.close();
        return rp;
    }
    
    
    public Report update(Report rp){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.update(rp);
        tx.commit();
        ss.close();
        return rp;
    }
    
    
    public Report delete(Report rp){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.delete(rp);
        tx.commit();
        ss.close();
        return rp;
    }
    
    
    public Report importExcelSheet(Report rp){    
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.saveOrUpdate(rp);
        tx.commit();
        ss.close();
        return rp;
    }
    
    
    public List<Report> rpList(){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        Query q = ss.createQuery("From Report");
        List<Report> sl = q.list();
        ss.getTransaction().commit();
        return sl;
    }

    /*This method will help us to find and select all rpudents from the database table.*/
    
    public List<Report> findAll (Report rp) {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        List<Report> all = (List<Report>) ss.createCriteria(rp.getClass()).list();
        tx.commit();
        ss.close();
        return all;
    }
}
