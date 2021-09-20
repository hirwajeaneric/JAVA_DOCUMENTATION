/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Controller.StudentController;
import Model.Student;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Aime
 */
public class StudentService extends UnicastRemoteObject implements IstudentService {

    public StudentService() throws RemoteException {
    }

    @Override
    public void save(Student cl) throws RemoteException {
        StudentController dao = new StudentController();
        dao.save(cl);
    }

    @Override
    public void update(Student cl) throws RemoteException {
        StudentController dao = new StudentController();
        dao.update(cl);
    }

    @Override
    public void delete(Student cl) throws RemoteException {
        StudentController dao = new StudentController();
        dao.delete(cl);
    }

}
