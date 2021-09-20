/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Client;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author hirwa
 */
public class ClientDao {
    Session session = null;
    //This method saves data int the database
    public void saveClient(Client c){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(c);
        tx.commit();
        session.close();
    }
    //This method is for updating data
    public void updateClient(Client c){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(c);
        tx.commit();
        session.close();
    }
    //This method is for deleting data
    public void deleteClient(Client c){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(c);
        tx.commit();
        session.close();
    }
    //This method is for displaying data 
    public List<Client> displayClient(){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Client> clients = (List<Client>) session.createCriteria(Client.class).list();
        tx.commit();
        session.close();
        return clients;
    }
    //This method is for searching Clients using their IDs
    public List<Client> findByClientId(String id){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Client> clients = (List<Client>) session.createCriteria(Client.class).add(Restrictions.eq("regno", id)).list();
        tx.commit();
        session.close();
        return clients;
    }
    //This method is used to get any client using the firstname, in few words, this method helps to select REGNOs
    public String getRegno(String name){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Object id = session.createQuery("select regno from Client where firstname||' '||lastname ='"+name+"'").uniqueResult();
        tx.commit();
        session.close();
        return id.toString();
    }
    //This method just like the previous one, helps to find client names using their REGNOs
    public String getClientName(String regno){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Object id = session.createQuery("select firstname||' '||lastname as name from Client where regno = '"+regno+"'").uniqueResult();
        tx.commit();
        session.close();
        return id.toString();
    }
}
