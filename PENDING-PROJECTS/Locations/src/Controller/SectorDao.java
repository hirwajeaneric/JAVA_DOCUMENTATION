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
import Model.Sectors;
import Util.HibernateUtil;
/**
 *
 * @author hirwa
 */
public class SectorDao {
    Session ss = null;
    Transaction tx = null;
    
    
    public Sectors save(Sectors sc){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.save(sc);
        tx.commit();
        ss.close();
        return sc;
    }
    
    
    public Sectors update(Sectors sc){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.update(sc);
        tx.commit();
        ss.close();
        return sc;
    }
    
    
    public Sectors delete(Sectors sc){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.delete(sc);
        tx.commit();
        ss.close();
        return sc;
    }
    
    
    public Sectors importExcelSheet(Sectors sc){    
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.saveOrUpdate(sc);
        tx.commit();
        ss.close();
        return sc;
    }
    
    
    public List<Sectors> scList(){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        Query q = ss.createQuery("From Sectors");
        List<Sectors> sl = q.list();
        ss.getTransaction().commit();
        return sl;
    }

    /*This method will help us to find and select all scudents from the database table.*/
    
    public List<Sectors> findAll (Sectors sc) {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        List<Sectors> all = (List<Sectors>) ss.createCriteria(sc.getClass()).list();
        tx.commit();
        ss.close();
        return all;
    }
    
    public List<Sectors> findSectorNames (Sectors sc) {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        List<Sectors> sectorList = (List<Sectors>) ss.createCriteria(sc.getName()).list();
        tx.commit();
        ss.close();
        return sectorList;
    }
}
