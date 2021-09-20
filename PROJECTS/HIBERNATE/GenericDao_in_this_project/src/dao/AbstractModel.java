/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Yva
 */
import java.util.List;
import org.hibernate.*;

import util.NewHibernateUtil;

/**
 *
 * @author Aubin
 */
public class AbstractModel<X> {
    Class<X> type;
    public Transaction tx = null;
    public Session ss = null;
    public AbstractModel(Class<X> type){
        this.type = type;
    }
    public void create(X obj){
        ss =NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.save(obj);
        ss.getTransaction().commit();
        ss.close();
    }
    public void update(X obj){
        ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.update(obj);
        ss.getTransaction().commit();
        ss.close();
    }
    public List<X> retrieveAll(){
        ss = NewHibernateUtil.getSessionFactory().openSession();
        Query qry = ss.createQuery("from " + type.getName());
        List<X> list = qry.list();
        ss.close();
        return list;
    }
    public void delete(X obj){
        ss = NewHibernateUtil.getSessionFactory().openSession();
        ss.beginTransaction();
        ss.delete(obj);
        ss.getTransaction().commit();
        ss.close();
    }
}
