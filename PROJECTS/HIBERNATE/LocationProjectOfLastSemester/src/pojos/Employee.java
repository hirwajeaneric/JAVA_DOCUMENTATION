package pojos;
// Generated Mar 9, 2021 12:13:02 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Employee generated by hbm2java
 */
public class Employee  implements java.io.Serializable {


     private int employeeid;
     private String employeenames;
     private Date dateofbirth;
     private String residence;

    public Employee() {
    }

    public Employee(int employeeid, String employeenames, Date dateofbirth, String residence) {
       this.employeeid = employeeid;
       this.employeenames = employeenames;
       this.dateofbirth = dateofbirth;
       this.residence = residence;
    }
   
    public int getEmployeeid() {
        return this.employeeid;
    }
    
    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }
    public String getEmployeenames() {
        return this.employeenames;
    }
    
    public void setEmployeenames(String employeenames) {
        this.employeenames = employeenames;
    }
    public Date getDateofbirth() {
        return this.dateofbirth;
    }
    
    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
    public String getResidence() {
        return this.residence;
    }
    
    public void setResidence(String residence) {
        this.residence = residence;
    }




}


