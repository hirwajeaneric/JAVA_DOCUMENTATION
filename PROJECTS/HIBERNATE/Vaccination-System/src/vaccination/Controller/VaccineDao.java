/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccination.Controller;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vaccination.Model.Vaccine;
import vaccination.Services.IvaccineService;
import vaccination.Util.HibernateUtil;

/**
 *
 * @author hirwa
 */
public class VaccineDao implements IvaccineService{
    Session ss = null;
    Transaction tx = null;
    @Override
    public Vaccine save(Vaccine vx){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.save(vx);
        tx.commit();
        ss.close();
        return vx;
    }
    @Override
    public List<Vaccine> findAll (Vaccine vx) {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        List<Vaccine> all = (List<Vaccine>) ss.createCriteria(vx.getClass()).list();
        tx.commit();
        ss.close();
        return all;
    }
    
    @Override
    public List<Vaccine> findThemAll() {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Query query = (Query) sess.createQuery("from Vaccine");
        List<Vaccine> all = query.list();
        tx.commit();
        ss.close();
        return all;
    }
}
