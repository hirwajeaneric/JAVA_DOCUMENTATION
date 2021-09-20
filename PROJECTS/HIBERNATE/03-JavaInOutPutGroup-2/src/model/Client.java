/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author hirwa
 */
@Entity
public class Client {
    @Id
    private String regno;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String emailaddress;
    private String clientcategory;
    private String photo;

    public Client() {
    
    }
    
    public Client(String regno) {
        this.regno = regno;
    }

    public Client(String regno, String firstname, String lastname, String phonenumber, String emailaddress, String clientcategory, String photo) {
        this.regno = regno;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.emailaddress = emailaddress;
        this.clientcategory = clientcategory;
        this.photo = photo;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getClientcategory() {
        return clientcategory;
    }

    public void setClientcategory(String clientcategory) {
        this.clientcategory = clientcategory;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
}
