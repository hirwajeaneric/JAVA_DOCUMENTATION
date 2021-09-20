/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.HibernateUtil;
import model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author hirwa
 */
public class StudentDao {
    Session session = null;
    //This method saves data int the database
    public void saveStudent(Student st){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(st);
        tx.commit();
        session.close();
    }
    
    public List<Student> displayStudent(){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Student> st = (List<Student>) session.createCriteria(Student.class).list();
        tx.commit();
        session.close();
        return st;
    }
    
    public List<Student> findAll(Student st){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Student> li = (List<Student>) session.createCriteria(st.getClass()).list();
        tx.commit();
        session.close();
        return li;
    }
    
}
