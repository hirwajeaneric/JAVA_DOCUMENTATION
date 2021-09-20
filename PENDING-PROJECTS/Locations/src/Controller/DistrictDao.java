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
import Model.Districts;
import Util.HibernateUtil;

/**
 *
 * @author hirwa
 */
public class DistrictDao {
    Session ss = null;
    Transaction tx = null;
    
    
    public Districts save(Districts dis){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.save(dis);
        tx.commit();
        ss.close();
        return dis;
    }
    
    
    public Districts update(Districts dis){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.update(dis);
        tx.commit();
        ss.close();
        return dis;
    }
    
    
    public Districts delete(Districts dis){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.delete(dis);
        tx.commit();
        ss.close();
        return dis;
    }
    
    
    public Districts importExcelSheet(Districts dis){    
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.saveOrUpdate(dis);
        tx.commit();
        ss.close();
        return dis;
    }
    
    
    public List<Districts> disList(){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        Query q = ss.createQuery("From Districts");
        List<Districts> sl = q.list();
        ss.getTransaction().commit();
        return sl;
    }

    /*This method will help us to find and select all disudents from the database table.*/
    
    public List<Districts> findAll (Districts dis) {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        List<Districts> all = (List<Districts>) ss.createCriteria(dis.getClass()).list();
        tx.commit();
        ss.close();
        return all;
    }
    
    public List<Districts> findDistrictNames (Districts dis) {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        List<Districts> districtList = (List<Districts>) ss.createCriteria(dis.getName()).list();
        tx.commit();
        ss.close();
        return districtList;
    }
}
