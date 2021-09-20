/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author The Crush
 */ 
@Entity
public class Vaccination implements Serializable {
    @Id
    private String citizenId;
    private String fullName;
    private String phone;
    private String status;
    private LocalDate vaccinationDate;
    private VaccineBrand brand;

    public Vaccination() {
    }

    public Vaccination(String citizenId, String fullName, String phone, String status, LocalDate vaccinationDate, VaccineBrand brand) {
        this.citizenId = citizenId;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
        this.vaccinationDate = vaccinationDate;
        this.brand = brand;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public VaccineBrand getBrand() {
        return brand;
    }

    public void setBrand(VaccineBrand brand) {
        this.brand = brand;
    }
    
}
