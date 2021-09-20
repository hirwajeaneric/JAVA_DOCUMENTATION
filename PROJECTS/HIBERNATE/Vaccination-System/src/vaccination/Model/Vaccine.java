/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccination.Model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 *
 * @author hirwa
 */
@Entity
public class Vaccine implements Serializable {
    @Id
    private String citizenId;
    private String fullnames;
    private String phonenumber;
    private String vaccinationstatus;
    private VaccineBrands vaccinebrand;
    private LocalDate vaccinationdate;

    public Vaccine() {
    }

    public Vaccine(String citizenId, String fullnames, String phonenumber, String vaccinationstatus, VaccineBrands vaccinebrand, LocalDate vaccinationdate) {
        this.citizenId = citizenId;
        this.fullnames = fullnames;
        this.phonenumber = phonenumber;
        this.vaccinationstatus = vaccinationstatus;
        this.vaccinebrand = vaccinebrand;
        this.vaccinationdate = vaccinationdate;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getFullnames() {
        return fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getVaccinationstatus() {
        return vaccinationstatus;
    }

    public void setVaccinationstatus(String vaccinationstatus) {
        this.vaccinationstatus = vaccinationstatus;
    }

    public VaccineBrands getVaccinebrand() {
        return vaccinebrand;
    }

    public void setVaccinebrand(VaccineBrands vaccinebrand) {
        this.vaccinebrand = vaccinebrand;
    }

    public LocalDate getVaccinationdate() {
        return vaccinationdate;
    }

    public void setVaccinationdate(LocalDate vaccinationdate) {
        this.vaccinationdate = vaccinationdate;
    }

    

    

    
    
    
}
