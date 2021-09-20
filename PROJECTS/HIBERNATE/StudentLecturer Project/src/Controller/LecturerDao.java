package Controller;

import Model.Lecturer;
import Util.HibernateUtil;
import org.hibernate.*;
import java.util.*;

/**
 * @author hirwa
 */
public class LecturerDao implements IlecturerInterface{
    Session ss = null;
    Transaction tx = null;

    @Override
    public Lecturer save(Lecturer lc) {
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        ss.save(lc);
        tx.commit();
        ss.close();
        return lc;
    }
    
    @Override
    public List<Lecturer> lcList(){
        ss = HibernateUtil.getSessionFactory().openSession();
        tx = ss.beginTransaction();
        Query q = ss.createQuery("From Lecturer");
        List<Lecturer> lectList = q.list();
        tx.commit();
        ss.close();
        return lectList;
    }
}