/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Vaccination;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author The Crush
 */
public class VaccinationController {

    public String newVaccination(Vaccination vaccine) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        sess.beginTransaction();
        sess.save(vaccine);
        sess.getTransaction().commit();
        sess.close();
        return "success";
    }

    public List<Vaccination> allVaccination() {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        Query query = (Query) sess.createQuery("from Vaccination");
        List<Vaccination> all = query.list();
        sess.close();
        return all;
    }
}
