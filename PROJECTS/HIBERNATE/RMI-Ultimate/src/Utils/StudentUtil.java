/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Service.IstudentService;

/**
 *
 * @author Aime
 */
public class StudentUtil {
    public static IstudentService getClientService() {
        try {
            return (IstudentService) getRegistry().lookup("studentService");
        } catch (NotBoundException | RemoteException ex) {
            //return a null
            return null;
        }
    }

    private static Registry getRegistry() {
        try {
            return LocateRegistry.getRegistry("localhost", 62121);
        } catch (RemoteException ex) {
            //return a null
            return null;
        }
    }
    
}
