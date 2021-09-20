/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculating;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hirwa
 */
public class IncomeDao {
    Session ss = null;
    Transaction tx = null;
    
    public Income save(Income in){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.save(in);
        tx.commit();
        ss.close();
        return in;
    }
    
     public List<Income> findAll (Income in) {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        List<Income> all = (List<Income>) ss.createCriteria(in.getClass()).list();
        tx.commit();
        ss.close();
        return all;
     }
}
