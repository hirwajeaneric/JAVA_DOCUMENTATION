/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Student;
import Utils.NewHibernateUtil;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Aime
 */
public class StudentController {

    public void save(Student cl) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(cl);
        s.getTransaction().commit();
        s.close();

    }

    public void update(Student cl) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(cl);
        s.getTransaction().commit();
        s.close();
    }

    public void delete(Student cl) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete(cl);
        s.getTransaction().commit();
        s.close();
    }

    public List<Student> stlist() {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Query q = s.createQuery("FROM Student");
        java.util.List<Student> sl;
        sl = q.list();
        s.getTransaction().commit();
        return sl;

    }
}
