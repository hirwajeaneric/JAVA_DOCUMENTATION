/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Model.Student;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Aime
 */
public interface IstudentService extends Remote {

    public void save(Student cl) throws RemoteException;

    public void update(Student cl) throws RemoteException;

    public void delete(Student cl) throws RemoteException;
//    public void femalelist(Client cl) throws RemoteException;

}
