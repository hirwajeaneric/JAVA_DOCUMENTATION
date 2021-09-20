/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccination.Services;

import java.util.List;
import vaccination.Model.Vaccine;

/**
 *
 * @author hirwa
 */
public interface IvaccineService {
    
    public Vaccine save(Vaccine vx);
    
    public List<Vaccine> findAll (Vaccine vx);
    
    public List<Vaccine> findThemAll ();

   
}
