/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Picture;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author The Crush
 */
public class PictureController {

    public String newPicture(Picture photo) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        sess.save(photo);
        sess.getTransaction().commit();
        sess.close();
        return "success";
    }

    public List<Picture> allPicture() {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Query query = (Query) sess.createQuery("from Picture");
        List<Picture> all = query.list();
        sess.close();
        return all;
    }
}
