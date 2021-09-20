/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author Aime
 */
@Entity
public class Student implements Serializable {

    @Id
    private String clientid;
    private String clientname;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Faculty faculty;

    public Student() {
    }

    public Student(String clientid, String clientname, Date dob, Gender gender, Faculty faculty) {
        this.clientid = clientid;
        this.clientname = clientname;
        this.dob = dob;
        this.gender = gender;
        this.faculty = faculty;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

}
