/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package association.collectionmappingpracticalexamples;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.address;
import model.mobile;
import model.user;
import model.vehicle;

/**
 *
 * @author hirwa
 */
public class HibernateMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        user user=new user();
        user user2=new user();
        user.setUsername("John");
        user2.setUsername("Doe");

        address address= new address();
        address.setStreet("sector 15");
        address.setCity("noida");
        address address2=new address();
        address2.setCity("muzaffarpur");
        address2.setStreet("sahebganj");


        vehicle veh=new vehicle();
        veh.setName("car");
        vehicle vehicle=new vehicle();
        vehicle.setName("jeep");
        vehicle vehicle2= new vehicle();
        vehicle2.setName("bike");
        vehicle vehicle3= new vehicle();
        vehicle3.setName("bus");
        vehicle vehicle4=new vehicle();
        vehicle4.setName("cycle");
        vehicle vehicle5= new vehicle();
        vehicle5.setName("truck");

        mobile mobile =new mobile();
        mobile.setBrand("sony");
        mobile.setModel("xperia z3");
        mobile mobile2 = new mobile();
        mobile2.setBrand("redmi");
        mobile2.setModel("note 5 pro");
        mobile mobile3 = new mobile();
        mobile3.setBrand("nokia");
        mobile3.setModel("7 plus");

        user.setAddress(address);
        user2.setAddress(address2);
        address.setUser(user);
        address2.setUser(user2);

        user.getMobile().add(mobile);
        user.getMobile().add(mobile2);
        mobile.setUser(user);
        mobile2.setUser(user);
        user2.getMobile().add(mobile3);
        mobile3.setUser(user2);

        user.getVehicle().add(veh);
        user.getVehicle().add(vehicle);
        user.getVehicle().add(vehicle2);
        veh.getUser().add(user);
        vehicle.getUser().add(user);
        vehicle2.getUser().add(user);
        user2.getVehicle().add(vehicle3);
        user2.getVehicle().add(vehicle4);
        user2.getVehicle().add(vehicle5);
        vehicle3.getUser().add(user2);
        vehicle4.getUser().add(user2);
        vehicle5.getUser().add(user2);


        SessionFactory sf=new Configuration().configure().buildSessionFactory();
        Session session=sf.openSession();
        session.beginTransaction();
        session.save(user);
        session.save(user2);
        session.getTransaction().commit();
        session.close();
    }
}
