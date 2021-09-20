package Controller;

import Model.Student;
import Util.HibernateUtil;
import org.hibernate.*;
import java.util.*;
import org.hibernate.criterion.Restrictions;

/**
 * @author hirwa
 */
public class StudentDao implements IstudentInterface{
    Session ss = null;
    Transaction tx = null;

    @Override
    public Student save(Student st) {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.save(st);
        tx.commit();
        ss.close();
        return st;
    }

    @Override
    public List<Student> stList(){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        Query q = ss.createQuery("From Student");
        List<Student> thelist = q.list();
        tx.commit();
        ss.close();
        return thelist;
    }
    
    @Override
    public List<Student> nameList(){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        Query q = ss.createQuery("SELECT name FROM Student");
        List<Student> nameList = q.list();
        tx.commit();
        ss.close();
        return nameList;
    }
    
    @Override
    public List<Student> findNames () {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        List<Student> names = (List<Student>) ss.createCriteria(Student.class).list();
        return names;
    }
    
    public void finbyId(){
        ss.createCriteria(Student.class).add(Restrictions.eq("ID", 1)).list();
    }
}